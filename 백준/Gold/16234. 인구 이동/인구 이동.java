import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] country;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        country = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        while(true){
            visited = new boolean[N][N];
            int count = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(!visited[i][j]){
                        if(bfs(i, j)){
                            count++;
                        }
                    }
                }
            }
            if(count == 0) break;
            result++;
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static boolean bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y});
        visited[x][y] = true;
        int total = country[x][y];
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[] {x, y});

        while(!q.isEmpty()){
            int[] now = q.remove();
            for(int i = 0; i < 4; i++){
                int nextX = now[0] + dx[i];
                int nextY = now[1] + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N || visited[nextX][nextY]) continue;
                int diff = Math.abs(country[now[0]][now[1]] - country[nextX][nextY]);
                if(L > diff || diff > R) continue;

                q.add(new int[] {nextX, nextY});
                list.add(new int[] {nextX, nextY});
                visited[nextX][nextY] = true;
                total += country[nextX][nextY];
            }
        }
        int avg = 0;
        for(int i = 0; i < list.size(); i++){
            int[] now = list.get(i);
            avg += country[now[0]][now[1]];
        }
        avg = avg / list.size();
        for(int i = 0; i < list.size(); i++){
            int[] now = list.get(i);
            country[now[0]][now[1]] = avg;
        }
        return list.size() > 1;
    }
}
