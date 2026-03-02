class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {-1, -1};
        int left = 0;
        int sum = 0;
        int n = sequence.length;
        for(int right = 0; right < n; right++){
            sum += sequence[right];
            // 작아지거나 left가 right보다 작을때까지 빼기
            while(sum > k && left < right){
                sum -= sequence[left++];
            }
            if(sum == k){
                if(answer[0] == -1 && answer[1] == -1){
                    answer[0] = left;
                    answer[1] = right;
                }else{
                    int nowSize = right - left + 1;
                    int beforeSize = answer[1] - answer[0] + 1;
                    
                    if(nowSize < beforeSize){
                        answer[0] = left;
                        answer[1] = right;
                    }
                }
            }
            
        }
        return answer;
    }
}