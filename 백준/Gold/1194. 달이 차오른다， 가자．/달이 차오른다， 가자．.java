import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int N, M, startX, startY;
    static int[][][] visit;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // DP[x][y][열쇠 보유 비트마스킹] 에서의 최소값 -1이면 방문 x
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == '0'){
                    startX = i;
                    startY = j;
                    map[i][j] = '.';
                }
            }
        }
        visit = new int[N][M][(1 << 6)];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                Arrays.fill(visit[i][j], -1);
            }
        }
        bw.write(Integer.toString(find()));
        bw.flush();
    }
    static int find(){
        PriorityQueue<Coordinate> pq = new PriorityQueue<>((c1, c2) -> {
            return c1.value - c2.value;
        });

        pq.add(new Coordinate(startX, startY, 0, 0));
        visit[startX][startY][0] = 0;

        while(!pq.isEmpty()){
            Coordinate now = pq.remove();
            for(int i = 0; i < 4; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                int nextVisit = now.visit;
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || map[nextX][nextY] == '#') continue;
                if(map[nextX][nextY] == '.'){
                    if(visit[nextX][nextY][nextVisit] == -1 || visit[nextX][nextY][nextVisit] > now.value + 1){
                        visit[nextX][nextY][nextVisit] = visit[now.x][now.y][now.visit] + 1;
                        pq.add(new Coordinate(nextX, nextY, nextVisit, visit[nextX][nextY][nextVisit]));
                    }
                }else if('a' <= map[nextX][nextY] && map[nextX][nextY] <= 'f'){ // 열쇠 갱신 후 최소값 갱신
                    int index = map[nextX][nextY] - 'a';
                    nextVisit = nextVisit | (1 << index);
                    if(visit[nextX][nextY][nextVisit] == -1 || visit[nextX][nextY][nextVisit] > now.value + 1){
                        visit[nextX][nextY][nextVisit] = now.value + 1;
                        pq.add(new Coordinate(nextX, nextY, nextVisit, visit[nextX][nextY][nextVisit]));
                    }
                }else if('A' <= map[nextX][nextY] && map[nextX][nextY] <= 'F'){ // 열쇠로 열리면 검사
                    int index = map[nextX][nextY] - 'A';
                    // 열쇠가 있음
                    if((nextVisit & (1 << index)) != 0){
                        if(visit[nextX][nextY][nextVisit] == -1 || visit[nextX][nextY][nextVisit] > now.value + 1){
                            visit[nextX][nextY][nextVisit] = now.value + 1;
                            pq.add(new Coordinate(nextX, nextY, nextVisit, visit[nextX][nextY][nextVisit]));
                        }
                    }
                }else if(map[nextX][nextY] == '1'){ // 탈출
                    return now.value + 1;
                }
            }
        }
        return -1;
    }
}
class Coordinate{
    int x;
    int y;
    int visit;
    int value;
    Coordinate(int x, int y, int visit, int value){
        this.x = x;
        this.y = y;
        this.visit = visit;
        this.value = value;
    }
}
