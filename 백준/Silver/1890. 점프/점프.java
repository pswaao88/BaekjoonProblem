import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] jump = new int[N+1][N+1];
        long[][] DP = new long[N+10][N+10];

        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                jump[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DP[1][1] = 1;
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(DP[i][j] == 0 || (i == N && j == N)) continue;
                // 오른쪽
                DP[i + jump[i][j]][j] += DP[i][j];
                // 아래
                DP[i][j + jump[i][j]] += DP[i][j];
            }
        }
        bw.write(Long.toString(DP[N][N]));
        bw.flush();
        bw.close();
        br.close();

    }
}
