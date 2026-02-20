import java.io.*;
import java.util.*;

class Solution {
    public final int MAX = 150;
    public int solution(int[][] info, int n, int m) {
        int answer = MAX;
        // DP[i] = B의 흔적이 i일때 A의 최소값
        int[] DP = new int[m];
        // MAX값인 120 이상인 150으로 선언
        Arrays.fill(DP, MAX);
        DP[0] = 0;
        for(int i = 0; i < info.length; i++){
            // DP를 바꾸면 로직이 꼬이므로 복사된 now 배열을 통해 DP 값 갱신 후 DP에 복사
            int[] now = new int[m];
            Arrays.fill(now, MAX);

            // A, B의 값
            int nowA = info[i][0];
            int nowB = info[i][1];
            for(int j = 0; j < m; j++){
                // A가 훔칠때 B는 안움직임
                // 값 안넘으면 갱신
                if(DP[j] + nowA < n && DP[j] != MAX) {
                    now[j] = Math.min(now[j], DP[j] + nowA);
                }
                // B가 훔칠때
                if(j + nowB < m){
                    now[j + nowB] = Math.min(now[j + nowB], DP[j]);
                }
                
            }
            
            // 모든 경우의 수 고려
            
            DP = now;
        }
        for(int i = 0; i < m; i++){
            answer = Math.min(answer, DP[i]);
        }
        if(answer == MAX) answer = -1;
        return answer;
    }
}