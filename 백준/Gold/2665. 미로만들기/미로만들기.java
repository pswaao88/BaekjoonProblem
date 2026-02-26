import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] room;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());

        room = new int[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < n; j++){
                room[i][j] = s.charAt(j) - '0';
            }
        }

        find();
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static void find(){
        PriorityQueue<Coordinate> pq = new PriorityQueue<>((c1, c2) -> c1.change - c2.change);
        pq.add(new Coordinate(0, 0, 0));
        visited[0][0]= true;
        
        while(!pq.isEmpty()){
            Coordinate now = pq.remove();
            int nowX = now.x;
            int nowY = now.y;
            int nowChange = now.change;
            for(int i = 0; i < 4; i++){
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextX][nextY]) continue;
                // 흰방
                if(room[nextX][nextY] == 1){
                    pq.add(new Coordinate(nextX, nextY, nowChange));
                    visited[nextX][nextY] = true;
                    if(nextX == n-1 && nextY == n-1){
                        result = nowChange;
                        return;
                    }
                }else{
                    pq.add(new Coordinate(nextX, nextY, nowChange + 1));
                    visited[nextX][nextY] = true;
                    if(nextX == n-1 && nextY == n-1){
                        result = nowChange + 1;
                        return;
                    }
                }
            }
        }
    }

}
class Coordinate{
    int x;
    int y;
    int change;
    Coordinate(int x, int y, int change){
        this.x = x;
        this.y = y;
        this.change = change;
    }
}
