import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static ArrayList<ArrayList<Integer>> graphBFS = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graphDFS = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            graphBFS.add(new ArrayList<>());
            graphDFS.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graphBFS.get(a).add(b);
            graphBFS.get(b).add(a);

            graphDFS.get(a).add(b);
            graphDFS.get(b).add(a);
        }

        for(int i = 0; i <= N; i++){
            Collections.sort(graphBFS.get(i));
        }
        for(int i = 0; i <= N; i++){
            Collections.sort(graphDFS.get(i), Collections.reverseOrder());
        }
        bw.write(dfs(V));
        bw.newLine();
        bw.write(bfs(V));
        bw.flush();
        bw.close();
        br.close();
    }
    static String bfs(int x){
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[N+1];

        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        visited[x] = true;

        while(!q.isEmpty()){
            int now = q.poll();
            sb.append(now).append(" ");
            for(int i = 0; i < graphBFS.get(now).size(); i++){
                int next = graphBFS.get(now).get(i);
                if(visited[next]) continue;
                visited[next] = true;
                q.add(next);
            }
        }
        return sb.toString().trim();
    }
    static String dfs(int x){
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[N+1];

        Stack<Integer> stack = new Stack<>();
        stack.push(x);

        while(!stack.isEmpty()){
            int now = stack.pop();
            if(visited[now]) continue;
            visited[now] = true;
            sb.append(now).append(" ");
            for(int i = 0; i < graphDFS.get(now).size(); i++){
                int next = graphDFS.get(now).get(i);
                stack.push(next);
            }
        }
        return sb.toString().trim();
    }
}
