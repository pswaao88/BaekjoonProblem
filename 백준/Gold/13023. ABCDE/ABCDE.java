import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int result = 0;
    static int count;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i = 0; i < N; i++){
            visited = new boolean[N];
            count = 0;
            dfs(i);
            if(result == 1) break;
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int x){
        visited[x] = true;
        count++;
        if(count == 5){
            result = 1;
            return;
        }
        for(int i = 0; i < graph.get(x).size(); i++){
            int next = graph.get(x).get(i);
            if(visited[next]) continue;
            dfs(next);
            visited[next] = false;
            count--;
        }
    }
}
