class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 0;
        int end = 100001;
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(resolve(mid, diffs, times) > limit){
                start = mid;
            }else{
                end = mid;
            }
        }
        return end;
    }
    long resolve(int level, int[] diffs, int[] time){
        long total = 0;
        for(int i = 0; i < diffs.length; i++){
            // 이전 값 없음
            int diff = diffs[i];
            int time_cur = time[i];
            int time_prev;
            if(i == 0){
                time_prev = 0;
            }else{
                time_prev = time[i-1];
            }    
            if(diff <= level){
                total += time_cur;
            }else{       
                total += (time_cur + time_prev) * (diff-level);
                total += time_cur;
            }
        }
        return total;
    }
}