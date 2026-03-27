import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {0, 0};
            PriorityQueue<Integer> max = new PriorityQueue<>((i1, i2) -> {
                return i2 - i1;
            });
        PriorityQueue<Integer> min = new PriorityQueue<>();
        int count = 0;
        StringTokenizer st;
        for(int i = 0; i < operations.length; i++){
            String now = operations[i];
            st = new StringTokenizer(now);
            String recommend = st.nextToken();
            String number = st.nextToken();
            if(recommend.equals("I")){
                min.add(Integer.parseInt(number));
                max.add(Integer.parseInt(number));
                count++;
            }else{
                if(count == 0) continue;
                
                if(number.equals("1")){
                    min.remove(max.remove());
                    count--;
                }else{
                    max.remove(min.remove());
                    count--;
                }
                if(count == 0){
                    min.clear();
                    max.clear();
                }
            }
        }
        if(count == 0){
            return answer;
        }
        answer[0] = max.remove();
        answer[1] = min.remove();
        return answer;
    }
}