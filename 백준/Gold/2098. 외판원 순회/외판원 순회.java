import java.io.*;
import java.util.*;

public class Main {
    static int[][] DP, W;
    static int N;
    static final int MAX = 987654321;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DP = new int[N][(1 << N)];
        for (int i = 0; i < N; i++) Arrays.fill(DP[i], -1);
        // 0까지 다온경우를 top-down

        int min = dfs(0, 1);;

        bw.write(Integer.toString(min));
        bw.flush();
    }
    static int dfs(int now, int visit){
        if(visit == (1<<N) - 1){
            if(W[now][0] != 0) return W[now][0];
            return MAX;
        }
        // 이미 방문한 곳
        if(DP[now][visit] != -1){
            return DP[now][visit];
        }
        // 방문안했으므로 MAX로 초기화
        DP[now][visit] = MAX;

        for(int next = 0; next < N; next++){
            // 길이 있고, 아직 방문하지 않은 도시라면
            if (W[now][next] != 0 && (visit & (1 << next)) == 0) {
                int result = dfs(next, visit | (1 << next)) + W[now][next];
                DP[now][visit] = Math.min(DP[now][visit], result);
            }
        }
        return DP[now][visit];
    }
}
