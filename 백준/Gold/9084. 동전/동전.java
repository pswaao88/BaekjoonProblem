import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            int[] coin = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                coin[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int[] DP = new int[M+1];
            // 동전 1개 쓰는거 대비용
            // 목표가 3이고 동전이 3이면 DP[3] += DP[0];
            DP[0] = 1;
            for(int i = 0; i < N; i++){
                int nowCoin = coin[i];
                for(int j = 1; j <= M; j++){
                    if(j - nowCoin >= 0){
                        DP[j] += DP[j - nowCoin];
                    }
                }
            }
            sb.append(DP[M]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
