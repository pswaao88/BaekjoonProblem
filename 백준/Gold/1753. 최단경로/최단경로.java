import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[] result;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        visited = new boolean[V+1];
        result = new int[V+1];
        Arrays.fill(result, Integer.MAX_VALUE);
        for(int i = 0; i <= V; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[] {b, c});
        }
        find(K);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            if(result[i] == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            }else{
                sb.append(result[i]).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static void find(int start){
        result[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {
            if(i1[1] == i2[1]){
                return i1[0] - i2[0];
            }
            return Integer.compare(i1[1], i2[1]);
        });

        pq.add(new int[] {start, 0});
        while(!pq.isEmpty()){
            int[] now = pq.remove();
            int nowNode = now[0];
            int nowValue = now[1];
            if(visited[nowNode]) continue;
            for(int i = 0; i < graph.get(nowNode).size(); i++){
                int[] nextNodeInfo = graph.get(nowNode).get(i);
                int nextNode = nextNodeInfo[0];
                int nextValue = nextNodeInfo[1];
                if(visited[nextNode]) continue;
                if(result[nextNode] > result[nowNode] + nextValue){
                    result[nextNode] = result[nowNode] + nextValue;
                    pq.add(new int[] {nextNode, result[nextNode]});
                }
            }
            visited[nowNode] = true;
        }
    }
}
