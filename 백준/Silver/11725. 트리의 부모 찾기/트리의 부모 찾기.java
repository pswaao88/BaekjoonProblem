import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] parent;
    static boolean[] visited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        visited = new boolean[N+1];

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        StringTokenizer st;
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        dfs(1);
        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++){
            sb.append(parent[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static void dfs(int now){
        visited[now] = true;
        for(int i = 0; i < graph.get(now).size(); i++){
            int next = graph.get(now).get(i);
            if(visited[next]) continue;
            parent[next] = now;
            dfs(next);
        }
    }
}
