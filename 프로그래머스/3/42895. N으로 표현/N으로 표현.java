import java.util.*;
import java.io.*;

class Solution {
    public int solution(int N, int number) {

        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= 8; i++){
            list.add(new HashSet<>());
        }

        // base case
        int nowNumber = N;
        for(int i = 1; i <= 8; i++){
            list.get(i).add(nowNumber);
            nowNumber = nowNumber * 10 + N;
        }
        
        for(int i = 1; i <= 8; i++){
            HashSet<Integer> now = list.get(i);
            // j개를 쓴 경우와 비교
            for(int j = 0; j < i; j++){
                for(int before : list.get(j)){
                    for(int next : list.get(i-j)){
                        now.add(before + next);
                        now.add(before - next);
                        now.add(before * next);
                        if(next != 0){
                            now.add(before / next);    
                        }
                    }
                }
            }
            if(now.contains(number)){
                return i;
            }
        }
        
        return -1;
    }

}
class Nnumber{
    int number;
    int count;
    Nnumber(int number, int count){
        this.number = number;
        this.count = count;
    }
}