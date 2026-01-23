import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int N;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

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
        for(int i = 1; i <= N; i++){
            Collections.sort(graph.get(i));
        }
        visited = new boolean[N+1];
        dfs(V);
        sb.append("\n");
        visited = new boolean[N+1];
        bfs(V);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void dfs(int x){
        if(visited[x]) return;
        visited[x] = true;
        sb.append(x).append(" ");
        for(int i = 0; i < graph.get(x).size(); i++){
            int next = graph.get(x).get(i);
            dfs(next);
        }
    }

    static void bfs(int x){
        Queue<Integer> q = new LinkedList<>();
        visited[x] = true;
        q.add(x);
        while(!q.isEmpty()){
            int now = q.remove();
            sb.append(now).append(" ");
            for(int i = 0; i < graph.get(now).size(); i++){
                int next = graph.get(now).get(i);
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }
    }
}
