import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N+1];
        int[] parent = new int[N+1];
        Arrays.fill(DP, 987654321);
        DP[0] = 0;
        DP[1] = 0;

        for(int i = 2; i <= N; i++){
            if(i % 3 == 0 && DP[i] > DP[i / 3] + 1){
                DP[i] = DP[i / 3] + 1;
                parent[i] = i / 3;
            }
            if(i % 2 == 0 && DP[i] > DP[i / 2] + 1){
                DP[i] = DP[i / 2] + 1;
                parent[i] = i / 2;
            }
            if(DP[i] > DP[i-1] + 1){
                DP[i] = DP[i - 1] + 1;
                parent[i] = i - 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(DP[N]).append("\n");
        int now = N;
        while(now >= 1){
            sb.append(now).append(" ");
            now = parent[now];
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
