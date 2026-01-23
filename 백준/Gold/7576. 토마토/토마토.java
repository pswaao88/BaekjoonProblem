import java.io.*;
import java.util.*;

public class Main {
    static int[][] tomato;
    static boolean[][] visited;
    static int N, M, riped, total;
    static ArrayList<int[]> start = new ArrayList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        tomato = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if(tomato[i][j] == -1) continue;
                if(tomato[i][j] == 1){
                    riped++;
                    start.add(new int[] {i, j});
                }
                total++;
            }
        }

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }
    static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < start.size(); i++){
            int[] now = start.get(i);
            q.add(new int[] {now[0], now[1], 0});
            visited[now[0]][now[1]] = true;
        }
        int day = 0;
        while(!q.isEmpty()){
            if(riped == total) return day;
            int[] now = q.remove();
            int nowX = now[0];
            int nowY = now[1];
            int nowTime = now[2];

            for(int i = 0; i < 4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[nextX][nextY] || tomato[nextX][nextY] == -1) continue;
                riped++;
                visited[nextX][nextY] = true;
                q.add(new int[] {nextX, nextY, nowTime + 1});
                day = Math.max(day, nowTime + 1);
            }
        }
        return -1;
    }
}
