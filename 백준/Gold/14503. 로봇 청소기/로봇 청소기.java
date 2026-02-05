import java.io.*;
import java.util.*;

public class Main {
    // 북:0 동:1 남:2 서:3
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] clean;
    static int N,M, r, c, d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        clean = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        dfs(r,c);
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(clean[i][j]) result++;
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static void dfs(int x, int y){
        // 현재 칸 청소처리 뒤로 다시 간경우일수도 있으므로
        if(!clean[x][y]){
            clean[x][y] = true;
        }

        if(!hasEmpty(x, y)){
            // 한칸 후진 d 0 <-> 2 / 1 <-> 3 이동만 적용해서 탐색
            int nextX;
            int nextY;
            if(d == 0){// 남쪽으로
                nextX = x + dx[2];
                nextY = y + dy[2];
            }else if(d == 1){ // 서쪽으로
                nextX = x + dx[3];
                nextY = y + dy[3];
            }else if(d == 2){ // 북쪽으로
                nextX = x + dx[0];
                nextY = y + dy[0];
            }else{ // 동쪽으로
                nextX = x + dx[1];
                nextY = y + dy[1];
            }
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == 1) return;
            dfs(nextX, nextY);
            return;
        }
        // 빈칸에 청소할 수 있는경우
        d--;
        if(d < 0) d = 3;
        int nextX = x + dx[d];
        int nextY = y + dy[d];
        if(nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && map[nextX][nextY] == 0 && !clean[nextX][nextY]){
            dfs(nextX, nextY);
        }else{
            dfs(x, y);
        }

    }

    static boolean hasEmpty(int x, int y){
        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
            if(map[nextX][nextY] == 0 && !clean[nextX][nextY]){
                return true;
            }
        }
        return false;
    }
}
