class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int x = 1; x <= r2; x++) {
            // 큰 원에서의 최대 y (내림)
            long y2 = (long) Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(x, 2)));
            
            // 작은 원에서의 최소 y (올림)
            // x가 r1보다 크면 작은 원 밖이므로 0부터 시작
            long y1 = 0;
            if (x < r1) {
                y1 = (long) Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(x, 2)));
            }
            
            answer += (y2 - y1 + 1);
        }

        return answer * 4;
    }
}