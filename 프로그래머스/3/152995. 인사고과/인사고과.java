import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int n = scores.length;
        int wanhoSum = scores[0][0] + scores[0][1];
        int[] wanho = scores[0];
        Arrays.sort(scores, (i1, i2) -> {
            if(i1[0] == i2[0]){
                return i1[1] - i2[1];
            }
            return i2[0] - i1[0];
        });
        
        
        int maxB = scores[0][1];
        boolean[] yes = new boolean[n]; 
        yes[0] = true;
        for(int i = 1; i < n; i++){
            int[] now = scores[i];
            // 같으면 인센티브 O
            if(maxB <= now[1]){
                yes[i] = true;
            }else{
                if(wanho == now){
                    return -1;
                }
            }
            
            maxB = Math.max(maxB, now[1]);
        }
        int[] rank = new int[200001];
        for(int i = 0; i < n; i++){
            if(yes[i]){
                int nowTotal = scores[i][0] + scores[i][1];
                rank[nowTotal]++;
            }
        }
        int nowRank = 1;
        int answer = -1;
        for(int i = 200000; i >= 0; i--){
            // 있을 경우
            if(rank[i] > 0){
                if(i == wanhoSum){
                    answer = nowRank;
                    break;
                }
                nowRank += rank[i];
            }
        }
        return answer;
    }
}