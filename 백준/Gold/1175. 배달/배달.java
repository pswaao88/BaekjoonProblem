import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][][][] visited;
    static int N, M;
    static int startX, startY;
    static int c1X, c1Y, c2X, c2Y;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M][4][4];
        boolean first = true;
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'S'){
                    startX = i;
                    startY = j;
                }else if(map[i][j] == 'C'){
                    if(first){
                        c1X = i;
                        c1Y = j;
                        first = false;
                    }else{
                        c2X = i;
                        c2Y = j;
                    }
                }
            }
        }
        int min = bfs(startX, startY);
        bw.write(Integer.toString(min));
        bw.flush();

    }
    static int bfs(int x, int y){
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(x, y, -1, 0, 0));
        while(!q.isEmpty()){
            Coordinate now = q.remove();
            if(now.visit == 3){
                return now.time;
            }
            for(int i = 0; i < 4; i++){
                if(now.before == i) continue;

                int nextX =  now.x + dx[i];
                int nextY =  now.y + dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == '#') continue;

                // 다음 방문 체크
                int nextVisit = now.visit;
                if(nextX == c1X && nextY == c1Y && (nextVisit & 1) == 0) nextVisit = nextVisit | 1;
                else if(nextX == c2X && nextY == c2Y && (nextVisit & 2) == 0) nextVisit = nextVisit | 2;

                if(!visited[nextX][nextY][i][nextVisit]){
                    visited[nextX][nextY][i][nextVisit] = true;
                    q.add(new Coordinate(nextX, nextY, i, nextVisit, now.time + 1));
                }

            }
        }
        return -1;
    }
}
class Coordinate{
    int x;
    int y;
    int before;
    int visit;
    int time;
    Coordinate(int x, int y, int before, int visit, int time){
        this.x = x;
        this.y = y;
        this.before = before;
        this.visit = visit;
        this.time = time;
    }
}
