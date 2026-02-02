import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<NodeP1238>> graph = new ArrayList<>();
    static int[] result;
    static boolean[] visited;
    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new NodeP1238(b, c));
        }
        int[] total = new int[N+1];
        find(X);
        for(int i = 1; i <= N; i++){
            total[i] = result[i];
        }
        for(int i = 1; i <= N; i++){
            find(i);
            total[i] += result[X];
        }
        int max = 0;
        for(int i = 1; i <= N; i++){
            max = Math.max(max, total[i]);
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
    static void find(int start){
        result = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(result, 987654321);
        PriorityQueue<NodeP1238> pq = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        pq.add(new NodeP1238(start, 0));
        result[start] = 0;
        while(!pq.isEmpty()){
            NodeP1238 now = pq.remove();
            if(visited[now.nowNode]) continue;
            visited[now.nowNode] = true;
            for(int i = 0; i < graph.get(now.nowNode).size(); i++){
                NodeP1238 next = graph.get(now.nowNode).get(i);
                if(visited[next.nowNode]) continue;
                if(result[next.nowNode] > result[now.nowNode] + next.weight){
                    result[next.nowNode] = result[now.nowNode] + next.weight;
                    pq.add(new NodeP1238(next.nowNode, result[next.nowNode]));
                }
            }
        }
    }

}
class NodeP1238{
    int nowNode;
    int weight;
    NodeP1238(int nowNode, int weight){
        this.nowNode = nowNode;
        this.weight = weight;
    }
}
