class Solution {
    public int solution(int[][] triangle) {
        int answer = 1;
        int[][] DP = new int[triangle.length][triangle.length];
        DP[0][0] = triangle[0][0];
        for(int i = 1; i < triangle.length; i++){
            for(int j = 0; j <= i; j++){
                if(j == 0){
                    DP[i][j] = DP[i-1][j] + triangle[i][j];
                }else{
                    DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-1]);
                    DP[i][j] += triangle[i][j];   
                }
            }
        }
        
        for(int i = 0; i < triangle.length; i++){
            System.out.println(DP[triangle.length - 1][i]);
            answer = Math.max(answer, DP[triangle.length - 1][i]);
        }
        return answer;
    }
}