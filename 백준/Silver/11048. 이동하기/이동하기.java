import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] candy = new int[N+1][M+1];
        int[][] DP = new int[N+1][M+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++){
                candy[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= M; i++){
            DP[1][i] = DP[1][i-1] + candy[1][i];
        }
        for(int i = 1; i <= N; i++){
            DP[i][1] = DP[i-1][1] + candy[i][1];
        }
        for(int i = 2; i <= N; i++){
            for(int j = 2; j <= M; j++){
                DP[i][j] = Math.max(Math.max(DP[i-1][j-1], DP[i-1][j]),DP[i][j-1]) + candy[i][j];
            }
        }
        bw.write(Integer.toString(DP[N][M]));
        bw.flush();
        bw.close();
        br.close();
    }
}
