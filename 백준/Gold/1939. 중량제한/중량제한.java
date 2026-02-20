import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Bridge>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        result = new int[N+1];

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Bridge(b, c));
            graph.get(b).add(new Bridge(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        find(start);
        bw.write(Integer.toString(result[end]));
        bw.flush();
    }
    static void find(int start){
        Arrays.fill(result, -1);
        result[start] = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n2.min, n1.min));
        pq.add(new Node(start, result[start]));
        while(!pq.isEmpty()){
            Node now = pq.remove();
            if(visited[now.now]) continue;
            visited[now.now] = true;
            for(int i = 0; i < graph.get(now.now).size(); i++){
                Bridge next = graph.get(now.now).get(i);
                if(visited[next.node]) continue;
                int nextWeight = Math.min(now.min, next.value);
                if(result[next.node] < nextWeight){
                    result[next.node] = nextWeight;
                    pq.add(new Node(next.node, result[next.node]));
                }
            }
        }
    }
}
class Node{
    int now;
    int min;
    Node(int now, int min){
        this.now = now;
        this.min = min;
    }
}
class Bridge{
    int node;
    int value;
    Bridge(int node, int value){
        this.node = node;
        this.value = value;
    }
}
