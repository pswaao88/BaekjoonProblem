import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        // 증설 횟수
        int answer = 0;
        ArrayList<Integer> timer = new ArrayList<>();
        // 현재 가용 인원 = 현재 서버의 수 * 인원 
        int totalPlayer = m;
        for(int i = 0; i < players.length; i++){
            // 가용 서버 시간 갱신
            for(int j = 0; j < timer.size(); j++){
                timer.set(j, timer.get(j) + 1);
                if(timer.get(j) > k){
                    totalPlayer -= m;
                    timer.remove(j);
                    j--;
                }
            }
            // 가용인원을 넘긴다면 서버 증설
            while(players[i] >= totalPlayer){
                totalPlayer += m;
                timer.add(1);
                answer++;
            }

        }
        return answer;
    }
}