class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] map = new int[m+1][n+1];
        for(int i = 0; i < puddles.length; i++){
            int[] now = puddles[i];
            map[now[0]][now[1]] = 1;
        }
        int[][] DP = new int[m+1][n+1];
        DP[1][1] = 1;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(map[i][j] == 0){
                    DP[i][j] += (DP[i-1][j] + DP[i][j-1]) % 1000000007;
                }
            }
        }
        return DP[m][n];
    }
}