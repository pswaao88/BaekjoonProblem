import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static final int WALLNOTVISITED = 2;
    static final int WALLVISITED = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }
        int result = bfs();
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static int bfs(){
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(0, 0, 1, false));
        map[0][0] = 2;
        while(!q.isEmpty()){
            Coordinate now = q.remove();
            int nowX = now.x;
            int nowY = now.y;
            int nowDistance = now.distance;
            if(nowX == N-1 && nowY == M-1) return nowDistance;
            for(int i = 0; i < 4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                // 안뚫은 상태
                if(!now.wallVisited){
                    if(map[nextX][nextY] == 0){
                        map[nextX][nextY] = 2;
                        q.add(new Coordinate(nextX, nextY, nowDistance + 1, false));
                    }else if(map[nextX][nextY] == 1){
                        q.add(new Coordinate(nextX, nextY, nowDistance + 1, true));
                    }else if(map[nextX][nextY] == 3){
                        map[nextX][nextY] = 2;
                        q.add(new Coordinate(nextX, nextY, nowDistance + 1, false));
                    }
                }else{
                    if(map[nextX][nextY] == 0){
                        map[nextX][nextY] = 3;
                        q.add(new Coordinate(nextX, nextY, nowDistance + 1, true));
                    }
                }
            }
        }
        return -1;
    }
}
class Coordinate{
    int x;
    int y;
    int distance;
    boolean wallVisited;
    Coordinate(int x, int y, int distance, boolean wallVisited){
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.wallVisited = wallVisited;
    }
}
