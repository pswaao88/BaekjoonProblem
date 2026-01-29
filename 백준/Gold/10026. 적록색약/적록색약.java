import java.io.*;
import java.util.*;

public class Main {
    static char[][] noRGB;
    static char[][] yesRGB;
    static boolean[][] visited;
    static int N;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        noRGB = new char[N][N];
        yesRGB = new char[N][N];

        for(int i = 0; i < N; i++){
            char[] s = br.readLine().toCharArray();
            for(int j = 0; j < N; j++){
                char now = s[j];
                noRGB[i][j] = now;
                if(now == 'G'){
                    yesRGB[i][j] = 'R';
                }else{
                    yesRGB[i][j] = now;
                }
            }
        }
        int noCount = 0;
        int yesCount = 0;

        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    bfs(i, j, noRGB[i][j], false);
                    noCount++;
                }
            }
        }
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(!visited[i][j]){
                    bfs(i, j, yesRGB[i][j], true);
                    yesCount++;
                }
            }
        }
        bw.write(Integer.toString(noCount));
        bw.write(" ");
        bw.write(Integer.toString(yesCount));
        bw.flush();
    }
    static void bfs(int x, int y, char target, boolean yes){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] now = q.remove();
            for(int i = 0; i < 4; i++){
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) continue;
                if(yes){
                    if(yesRGB[nextX][nextY] == target){
                        visited[nextX][nextY] = true;
                        q.add(new int[] {nextX, nextY});
                    }
                }else{
                    if(noRGB[nextX][nextY] == target){
                        visited[nextX][nextY] = true;
                        q.add(new int[] {nextX, nextY});
                    }
                }
            }
        }
    }
}
