import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<NodeP1504>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] result;
    static final int MAX_VALUE = 300000000;
    static int N, v1, v2;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new NodeP1504(b, c));
            graph.get(b).add(new NodeP1504(a, c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        // 2가지 찾기
        // 1. 1-v1, v1-v2, v2-N 의 합
        // 2. 1-v2, v2-v1, v1-N 의 합
        int result1 = 0;
        int result2 = 0;
        
        find(1);
        result1 += result[v1];
        result2 += result[v2];
        
        find(v1);
        result1 += result[v2];
        result2 += result[N];
        find(v2);
        result1 += result[N];
        result2 += result[v1];
        

        int minResult = Math.min(result1, result2);
        if(minResult >= MAX_VALUE){
            bw.write(Integer.toString(-1));
        }else{
            bw.write(Integer.toString(minResult));
        }
        bw.flush();
    }
    static void find(int start){
        visited = new boolean[N+1];
        result = new int[N+1];

        Arrays.fill(result, MAX_VALUE);
        result[start] = 0;
        PriorityQueue<NodeP1504> pq = new PriorityQueue<>((n1, n2) -> n1.weight - n2.weight);
        pq.add(new NodeP1504(start, 0));
        while(!pq.isEmpty()){
            NodeP1504 now = pq.remove();
            if(visited[now.nowNode]) continue;
            visited[now.nowNode] = true;
            for(int i = 0; i < graph.get(now.nowNode).size(); i++){
                NodeP1504 next = graph.get(now.nowNode).get(i);
                if(visited[next.nowNode]) continue;
                if(result[next.nowNode] > result[now.nowNode] + next.weight){
                    result[next.nowNode] = result[now.nowNode] + next.weight;
                    pq.add(new NodeP1504(next.nowNode, result[next.nowNode]));
                }
            }
        }
    }
}

class NodeP1504{
    int nowNode;
    int weight;
    NodeP1504(int nowNode, int weight){
        this.nowNode = nowNode;
        this.weight = weight;
    }
}
