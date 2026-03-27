class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int N = triangle.length;
        // 위 또는 왼쪽에서 가져오기
        int[][] DP = new int[N][N];
        DP[0][0] = triangle[0][0];
        for(int i = 1; i < N; i++){
            int[] now = triangle[i];
            for(int j = 0; j < now.length; j++){
                if(j != 0){
                    DP[i][j] = DP[i-1][j-1];    
                }
                DP[i][j] = Math.max(DP[i][j], DP[i-1][j]);
                DP[i][j] += now[j];
                if(i == N-1){
                    answer = Math.max(answer, DP[i][j]);
                }
            }
        }
        
        return answer;
    }
}