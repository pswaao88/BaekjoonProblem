import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int K = Integer.parseInt(br.readLine());
            int[] page = new int[K+1];
            int[] sum = new int[K+1];
            int[][] DP = new int[K+1][K+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= K; i++){
                page[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i-1] + page[i];
            }
            // 페이지 수
            for(int i = 2; i <= K; i++){
                for(int j = 1; j <= K - i + 1; j++){
                    int left = j;
                    int right = j + i - 1;
                    DP[left][right] = Integer.MAX_VALUE;

                    for(int k = 0; k < right - left; k++){
                        DP[left][right] = Math.min(DP[left][right], DP[left][left + k] + DP[left + k + 1][right]);
                    }
                    DP[left][right] += sum[right] - sum[left - 1];
                }
            }
            sb.append(DP[1][K]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
