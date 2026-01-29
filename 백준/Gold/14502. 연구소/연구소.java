import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> start = new ArrayList<>();
    static ArrayList<int[]> empty = new ArrayList<>();
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    start.add(new int[] {i, j});
                }else if(map[i][j] == 0){
                    empty.add((new int[] {i, j}));
                }
            }
        }
        int max = 0;
        for(int i = 0; i < empty.size(); i++){
            for(int j = i + 1; j < empty.size(); j++){
                for(int k = j + 1; k < empty.size(); k++){
                    int[] first = empty.get(i);
                    int[] second = empty.get(j);
                    int[] third = empty.get(k);
                    map[first[0]][first[1]] = 1;
                    map[second[0]][second[1]] = 1;
                    map[third[0]][third[1]] = 1;
                    max = Math.max(max, bfs());
                    map[first[0]][first[1]] = 0;
                    map[second[0]][second[1]] = 0;
                    map[third[0]][third[1]] = 0;
                }
            }
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
    static int bfs(){
        int[][] bfsMap = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                bfsMap[i][j] = map[i][j];
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < start.size(); i++){
            int[] now = start.get(i);
            q.add(now);
            visited[now[0]][now[1]] = true;
        }
        while(!q.isEmpty()){
            int[] now = q.remove();
            int x = now[0];
            int y = now[1];
            for(int i = 0; i < 4; i++){
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[nextX][nextY] || bfsMap[nextX][nextY] != 0) continue;
                bfsMap[nextX][nextY] = 2;
                visited[nextX][nextY] = true;
                q.add(new int[] {nextX, nextY});
            }
        }
        return find(bfsMap);
    }
    static int find(int[][] bfsMap){
        int count = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(bfsMap[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }
}
