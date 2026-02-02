import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] result;
    static boolean[] visited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        result = new int[N+1];
        Arrays.fill(result, Integer.MAX_VALUE);
        visited = new boolean[N+1];

        StringTokenizer st;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        find(start);
        int end = Integer.parseInt(st.nextToken());

        bw.write(Integer.toString(result[end]));
        bw.flush();
    }
    static void find(int x){
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        result[x] = 0;
        pq.add(new Node(x, 0));
        while(!pq.isEmpty()){
            Node now = pq.remove();
            if(visited[now.node]) continue;
            visited[now.node] = true;
            for(int i = 0; i < graph.get(now.node).size(); i++){
                Node next = graph.get(now.node).get(i);
                if(visited[next.node]) continue;
                if(result[next.node] > next.weight + result[now.node]){
                    result[next.node] = result[now.node] + next.weight;
                    pq.add(new Node(next.node, result[next.node]));
                }
            }
        }
    }

}

class Node{
    int node;
    int weight;
    Node(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}
