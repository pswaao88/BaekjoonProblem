import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M, count = 0;
    static HashMap<Character, Integer> index = new HashMap<>();
    static char[][] map;
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        index.put('U', 0);
        index.put('D', 1);
        index.put('L', 2);
        index.put('R', 3);

        map = new char[N][M];
        visited = new int[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j);
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j] == 0){
                    dfs(i, j);
                }
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
    }
    static void dfs(int x, int y){
        visited[x][y] = 1;
        int direction = index.get(map[x][y]);
        int nextX = x + dx[direction];
        int nextY = y + dy[direction];
        if(visited[nextX][nextY] == 0){
            dfs(nextX, nextY);
        }else if(visited[nextX][nextY] == 1){
            count++;
        }
        visited[x][y] = 2;
    }
}

