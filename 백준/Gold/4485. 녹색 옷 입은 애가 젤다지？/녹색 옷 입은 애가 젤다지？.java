import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int[][] result;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int problem = 1;
        StringBuilder sb = new StringBuilder();
        while((N =  Integer.parseInt(br.readLine())) != 0){
            map = new int[N][N];
            result = new int[N][N];
            visited = new boolean[N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            sb.append("Problem ").append(problem).append(": ").append(result[N-1][N-1]).append("\n");
            problem++;
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static void bfs(){
        visited[0][0] = true;
        result[0][0] = map[0][0];
        PriorityQueue<Coordinate> pq = new PriorityQueue<>((c1, c2) -> c1.value - c2.value);
        pq.add(new Coordinate(0, 0, result[0][0]));
        while(!pq.isEmpty()){
            Coordinate now = pq.remove();
            for(int i = 0; i < 4; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) continue;
                result[nextX][nextY] = result[now.x][now.y] + map[nextX][nextY];
                pq.add(new Coordinate(nextX, nextY, result[nextX][nextY]));
                visited[nextX][nextY] = true;
            }
        }
    }
}
class Coordinate{
    int x;
    int y;
    int value;
    Coordinate(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
