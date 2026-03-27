import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count = 0;
    static int[][] before, after;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        before = new int[N][M];
        after = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                before[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                after[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean find = false;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(before[i][j] != after[i][j]){
                    bfs(i, j, before[i][j], after[i][j]);
                    find = true;
                    break;
                }
            }
            if(find) break;
        }

        boolean yes = true;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(before[i][j] != after[i][j]){
                    yes = false;
                    break;
                }
            }
        }

        if(yes){
            bw.write("YES");
        }else{
            bw.write("NO");
        }
        bw.flush();
    }
    static void bfs(int x, int y, int value, int target){
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(x, y));
        visited[x][y] = true;
        before[x][y] = target;
        while(!q.isEmpty()){
            Coordinate now = q.remove();
            for(int i = 0; i < 4; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[nextX][nextY] || value != before[nextX][nextY]) continue;
                visited[nextX][nextY] = true;
                before[nextX][nextY] = target;
                q.add(new Coordinate(nextX, nextY));
            }
        }
    }


}
class Coordinate{
    int x;
    int y;
    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
