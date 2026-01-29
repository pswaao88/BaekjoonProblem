import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H, min = 0, total = 0, nowTomato = 0;
    static int[][][] tomato;
    static boolean[][][] visited;
    static ArrayList<TomatoCoordinate> start = new ArrayList<>();
    static int[] dx = {0, 1, 0 ,-1 ,0, 0};
    static int[] dy = {1, 0, -1 ,0 ,0, 0};
    static int[] dz = {0, 0, 0 , 0 ,1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[N][M][H];
        visited = new boolean[N][M][H];
        for(int i = 0; i < H; i++){
            for(int j = 0; j < N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < M; k++){
                    tomato[j][k][i] = Integer.parseInt(st.nextToken());
                    if(tomato[j][k][i] != -1){
                        total++;
                        if(tomato[j][k][i] == 1){
                            nowTomato++;
                            start.add(new TomatoCoordinate(j, k, i, 0));
                            visited[j][k][i] = true;
                        }
                    }
                }
            }
        }
        bfs();

        if(total == nowTomato){
            bw.write(Integer.toString(min));
        }else{
            bw.write("-1");
        }
        bw.flush();
    }
    static void bfs(){
        Queue<TomatoCoordinate> q = new LinkedList<>(start);

        while(!q.isEmpty()){
            TomatoCoordinate now = q.remove();
            min = Math.max(min, now.day);
            for(int i = 0; i < 6; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                int nextZ = now.z + dz[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || nextZ < 0 || nextZ >= H) continue;
                if(visited[nextX][nextY][nextZ] || tomato[nextX][nextY][nextZ] == -1) continue;
                visited[nextX][nextY][nextZ] = true;
                q.add(new TomatoCoordinate(nextX, nextY, nextZ, now.day + 1));
                nowTomato++;
            }
        }
    }
}
class TomatoCoordinate{
    int x;
    int y;
    int z;
    int day;
    TomatoCoordinate(int x, int y, int z, int day){
        this.x = x;
        this.y = y;
        this.z = z;
        this.day = day;
    }
}
