class Solution {
    int N;
    int total = 0;
    int[] order;
    public int solution(int n) {
        int answer = 0;
        N = n;
        order = new int[n];
        find(0, 0);
        return total;
    }
    void find(int visit, int depth){
        if(depth >= N){
            total++;
            return;
        }
        for(int i = 0; i < N; i++){
            // 이미 처리된 곳 무시
            if((visit &(1 << i)) != 0) continue;
            if(isOk(i, depth)){
                int nextVisit = visit | (1 << i);
                order[depth] = i;
                find(nextVisit, depth + 1);
            }
        }
    }
    boolean isOk(int x, int depth){
        for(int row = 0; row <= depth - 1; row++){
            // 이전 값 위치
            int col = order[row];
            if(Math.abs(depth - row) == Math.abs(x - col)){
                return false;
            }
            
        }
        return true;
    }
}