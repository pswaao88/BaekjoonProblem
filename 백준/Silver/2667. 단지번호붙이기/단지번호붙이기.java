import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            char[] s = br.readLine().toCharArray();
            for(int j = 0; j < N; j++){
                map[i][j] = s[j] -'0';
            }
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> list = new ArrayList<>();
        int result = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(map[i][j] == 1 && !visited[i][j]){
                    result++;
                    visited[i][j] = true;
                    list.add(dfs(i,j));
                }
            }
        }
        Collections.sort(list);
        sb.append(result).append("\n");
        for(int x : list){
            sb.append(x).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static int dfs(int x, int y){
        int count = 1;
        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY] || map[nextX][nextY] == 0) continue;
            visited[nextX][nextY] = true;
            count += dfs(nextX, nextY);
        }
        return count;
    }
}
