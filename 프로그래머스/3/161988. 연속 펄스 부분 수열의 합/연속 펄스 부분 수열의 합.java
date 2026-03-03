class Solution {

    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;
        long[] sum = new long[n+1];
        for(int i = 1; i <= n; i++){
            if(i % 2 == 0){
                sum[i] = sum[i-1] - sequence[i-1];    
            }else{
                sum[i] = sum[i-1] + sequence[i-1];
            }
        }
        long max = -10000000000L;
        long min = 0;
        for(int i = 0; i <= n; i++){
            max = Math.max(max, sum[i]);
            min = Math.min(min, sum[i]);        
        }
        return max - min;
        
    }
}