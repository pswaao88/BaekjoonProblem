import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] visited;
    static int[][] map;
    static int N, M;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            char[] s = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                map[i][j] = s[j] - '0';
            }
        }
        bw.write(Integer.toString(bfs(0, 0)));
        bw.flush();

    }
    private static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[] {x, y, 1});
        while(!q.isEmpty()){
            int[] now = q.remove();
            int nowX = now[0];
            int nowY = now[1];
            int nowDistance = now[2];
            for(int i = 0; i < 4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == 0 || visited[nextX][nextY]) continue;
                if(nextX == N-1 && nextY == M-1) return nowDistance + 1;
                visited[nextX][nextY] = true;
                q.add(new int[] {nextX, nextY, nowDistance + 1});
            }
        }
        return -1;
    }
}
