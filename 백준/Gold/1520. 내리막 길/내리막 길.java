import java.io.*;
import java.util.*;

public class Main {
    static int[][] DP, map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int M, N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M+1][N+1];
        DP = new int[M+1][N+1];
        for(int i = 0; i <= M; i++){
            Arrays.fill(DP[i], -1);
        }

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bw.write(Integer.toString(dfs(1,1)));
        bw.flush();
    }
    static int dfs(int x, int y){
        if(x == M && y == N) return 1;
        if(DP[x][y] != -1) return DP[x][y];
        DP[x][y] = 0;
        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX <= 0 || nextX > M || nextY <= 0 || nextY > N) continue;
            if(map[x][y] > map[nextX][nextY]){
                DP[x][y] += dfs(nextX, nextY);
            }
        }
        return DP[x][y];
    }
}
