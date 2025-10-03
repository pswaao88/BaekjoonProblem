import java.io.*;
import java.util.*;

public class Main {
    static int size = 500;
    static int[][] map = new int[size+1][size+1];
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());


        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int X1 = Integer.parseInt(st.nextToken());
            int Y1 = Integer.parseInt(st.nextToken());
            int X2 = Integer.parseInt(st.nextToken());
            int Y2 = Integer.parseInt(st.nextToken());
            fillNumber(1, X1, Y1, X2, Y2);
        }
        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int X1 = Integer.parseInt(st.nextToken());
            int Y1 = Integer.parseInt(st.nextToken());
            int X2 = Integer.parseInt(st.nextToken());
            int Y2 = Integer.parseInt(st.nextToken());
            fillNumber(2, X1, Y1, X2, Y2);
        }
        bfs();
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
    static void fillNumber(int value, int X1, int Y1, int X2, int Y2){
        int startX = X1;
        int endX = X2;

        int startY = Y1;
        int endY = Y2;
        if(X1 > X2){
            startX = X2;
            endX = X1;
        }
        if(Y1 > Y2){
            startY = Y2;
            endY = Y1;
        }
        for(int i = startX; i <= endX; i++){
            for(int j = startY; j <= endY; j++){
                map[i][j] = value;
            }
        }
    }
    static void bfs(){
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {return o1[2] - o2[2];});
        boolean[][] visited = new boolean[size+1][size+1];
        visited[0][0] = true;
        pq.add(new int[] {0, 0, 0});
        while(!pq.isEmpty()){
            int[] now = pq.remove();
            int nowX = now[0];
            int nowY = now[1];
            int nowHealthy = now[2];

            if(nowX == size && nowY == size){
                result = nowHealthy;
                return;
            }

            for(int i = 0; i < 4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(nextX < 0 || nextX > size || nextY < 0 || nextY > size) continue;
                if(visited[nextX][nextY] || map[nextX][nextY] == 2) continue;

                if(map[nextX][nextY] == 1){
                    pq.add(new int[] {nextX, nextY, nowHealthy + 1});
                }else{
                    pq.add(new int[] {nextX, nextY, nowHealthy});
                }
                visited[nextX][nextY] = true;

            }
        }
    }
}
