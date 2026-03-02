import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2-i1);
        for(int i = 0; i < works.length; i++){
            pq.add(works[i]);
        }
        int T = n;
        while(T-->0){
            int now = pq.remove();
            now--;
            if(now > 0){
                pq.add(now);    
            }
            if(pq.isEmpty()){
                return 0;
            }
        }
        int size = pq.size();
        for(int i = 0; i < size; i++){
            int now = pq.remove();
            answer += (now * now);
        }
        return answer;
    }
}