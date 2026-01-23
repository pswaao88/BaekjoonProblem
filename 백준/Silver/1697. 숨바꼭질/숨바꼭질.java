import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited = new boolean[100001];
    static int N,K;
    static int[] dx = {-1, 1, 2};
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int result = bfs(N);
        bw.write(Integer.toString(result));
        bw.flush();

    }
    static int bfs(int x){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, 0});
        visited[x] = true;
        while(!q.isEmpty()){
            int[] now = q.remove();
            int nowX = now[0];
            int nowTime = now[1];
            if(nowX == K) return nowTime;
            for(int i = 0; i < 3; i++){
                int nextX;
                if(i == 2){
                    nextX = nowX * dx[i];
                }else{
                    nextX = nowX + dx[i];
                }
                if(nextX < 0 || nextX > 100000 || visited[nextX]) continue;
                visited[nextX] = true;
                q.add(new int[] {nextX, nowTime + 1});
            }
        }
        return -1;
    }
}
