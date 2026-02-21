import java.io.*;
import java.util.*;

class Solution {
    ArrayList<int[]> list = new ArrayList<>();
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        makeList(n, q, ans, 1, 0, 0);
        return answer;
    }
    boolean isOk(int[][] q, int[] ans, int visit){
        // 모든 숫자와 비교해서 정답이 맞는지 비교
        for(int i = 0; i < q.length; i++){
            int[] now = q[i];
            int nowResult = 0;
            for(int j = 0; j < 5; j++){
                if((visit & (1 << now[j])) != 0){
                    nowResult++;
                }
            }
            if(nowResult != ans[i]){
                return false;
            }
        }
        return true;
    }
    // 비트 마스킹으로 체크
    void makeList(int n, int[][] q, int[] ans, int start, int visit, int depth ){
        // 정답 5개 만들었을때
        if(depth >= 5){
            if(isOk(q, ans, visit)) answer++;
            return;
        }
        for(int i = start; i <= n; i++){
            int nowVisit = visit | (1 << i);
            makeList(n, q, ans, i + 1, nowVisit, depth + 1);
        }
    }
}