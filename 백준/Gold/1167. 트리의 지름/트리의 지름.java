import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<TreeNode>> graph = new ArrayList<>();
    static int maxStart = 0;
    static int max = 0;
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int V = Integer.parseInt(br.readLine());
        for(int i = 0; i <= V; i++){
            graph.add(new ArrayList<>());
        }
        StringTokenizer st;
        for(int i = 0; i < V; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            ArrayList<TreeNode> now = graph.get(v);
            int next;
            while((next = Integer.parseInt(st.nextToken())) != -1) {
                int weight = Integer.parseInt(st.nextToken());
                now.add(new TreeNode(next, weight));
            }
        }
        visit = new boolean[V+1];
        dfs(1, 0);
        visit = new boolean[V+1];
        dfs(maxStart, 0);
        bw.write(Integer.toString(max));
        bw.flush();

    }
    static void dfs(int x, int value){
        visit[x] = true;
        for(int i = 0; i < graph.get(x).size(); i++){
            TreeNode next = graph.get(x).get(i);
            if(visit[next.now]) continue;
            if(max < value + next.weight){
                max = value + next.weight;
                maxStart = next.now;
            }
            dfs(next.now, value + next.weight);
        }
    }
}
class TreeNode{
    int now;
    int weight;
    TreeNode(int now, int weight){
        this.now = now;
        this.weight = weight;
    }
}
