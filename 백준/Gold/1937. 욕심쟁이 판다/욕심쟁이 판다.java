import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] bamboo;
    static int[][] DP;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        bamboo = new int[N][N];
        DP = new int[N][N];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                bamboo[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(DP[i][j] == 0){
                    find(i,j);
                }
            }
        }
        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                max = Math.max(max, DP[i][j]);
            }
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
    static int find(int x, int y){
        // 메모이제이션
        if(DP[x][y] != 0){
            return DP[x][y];
        }

        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
            // 현재 위치 보다 많은 경우
            if(bamboo[nextX][nextY] > bamboo[x][y]){
                DP[x][y] = Math.max(DP[x][y], find(nextX, nextY) + 1);
            }
        }
        if(DP[x][y] == 0){
            DP[x][y] = 1;
        }
        return DP[x][y];
    }
}
