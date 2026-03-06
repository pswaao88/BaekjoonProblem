class Solution {
    public long solution(int n, int[] times) {
        int m = times.length;
        
        long max = 0;
        for(int i = 0; i < m; i++){
            max = Math.max(max, times[i]);
        }
        // lo를 F
        // hi를 T
        long lo = 0;
        long hi = n * max + 1;
        while(lo + 1 < hi){
            long mid = lo + (hi - lo) / 2;
            long count = 0;
            for(int i = 0; i < m; i++){
                count += mid / times[i];
            }
            // 시간 늘리면 count 늘어남 T쪽으로 진행
            // count가 크거나 같으면 T이므로
            if(count >= n){
                hi = mid;
            }else{
                lo = mid;
            }
        }
        
        return hi;
    }
}