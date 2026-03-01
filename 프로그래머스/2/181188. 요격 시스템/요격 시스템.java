import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        Arrays.sort(targets, (i1, i2) -> i1[1] - i2[1]);
        // now는 now + a의 실수를 의미하므로 now가 3이면 개구간 3이 걸쳐있으면 요격 가능
        // ex) [3,4] 가능, [3,7] 가능, [2, 9] 가능 [1, 3] 불가능
        int now = targets[0][1] - 1;
        for(int i = 1; i < targets.length; i++){
            int[] m = targets[i];
            int start = m[0];
            int end = m[1];
            // 구간에 걸쳐 요격 가능
            if(start <= now & now < end) continue;
            // 구간에 안걸친 경우
            now = targets[i][1] - 1;
            answer++;
            
        }
        return answer;
    }
}