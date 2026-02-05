import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Tree>> graph = new ArrayList<>();
    static int max = 0;
    static int maxNode = 1;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        StringTokenizer st;
        for(int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a =  Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            int c =  Integer.parseInt(st.nextToken());

            graph.get(a).add(new Tree(b, c));
            graph.get(b).add(new Tree(a, c));
        }
        visited = new boolean[n+1];
        dfs(1, 0);
        visited = new boolean[n+1];
        dfs(maxNode, 0);
        bw.write(Integer.toString(max));
        bw.flush();

    }
    static void dfs(int x, int value){
        visited[x] = true;
        for(int i = 0; i < graph.get(x).size(); i++){
            Tree next = graph.get(x).get(i);
            if(visited[next.now]) continue;
            if(max < value + next.weight){
                max = value + next.weight;
                maxNode = next.now;
            }
            dfs(next.now, value + next.weight);
        }
    }
}
class Tree{
    int now;
    int weight;
    Tree(int now, int weight){
        this.now = now;
        this.weight = weight;
    }
}
