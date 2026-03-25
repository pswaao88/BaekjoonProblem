import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static ArrayList<ArrayList<Bridge>> graph = new ArrayList<>();
    static boolean[] visit;
    static int[] value;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        visit = new boolean[N+1];
        value = new int[N+1];
        Arrays.fill(value, -1);

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            graph.get(h1).add(new Bridge(h2, k));
            graph.get(h2).add(new Bridge(h1, k));
        }
        find();
        if(value[E] == -1){
            bw.write("0");
        }else{
            bw.write(Integer.toString(value[E]));
        }
        bw.flush();
    }
    static void find(){
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n2.value - n1.value);
        pq.add(new Node(S, 0));
        value[S] = 0;
        while(!pq.isEmpty()){
            Node now = pq.remove();
            if(visit[now.now]) continue;
            visit[now.now] = true;
            for(int i = 0; i < graph.get(now.now).size(); i++){
                Bridge next = graph.get(now.now).get(i);
                if(visit[next.node]) continue;
                // 시작일 경우 처리
                if(now.now == S){
                    value[next.node] = next.weight;
                    pq.add(new Node(next.node, next.weight));
                }else{
                    // 시작이 아닐 경우 비교후 max 값 갱신
                    int nextValue = Math.min(now.value, next.weight);
                    if(value[next.node] < nextValue){
                        value[next.node] = nextValue;
                        pq.add(new Node(next.node, value[next.node]));
                    }
                }
            }
        }
    }
}
class Node{
    int now;
    int value;
    Node(int now, int value){
        this.now = now;
        this.value = value;
    }
}
class Bridge{
    int node;
    int weight;
    Bridge(int node, int weight){
        this.node = node;
        this.weight = weight;
    }
}
