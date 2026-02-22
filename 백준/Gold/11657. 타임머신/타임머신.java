import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node> graph = new ArrayList<>();
    static long[] cost;
    static int N, M;
    static long MAX = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cost = new long[N+1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.add(new Node(a, b, c));
        }
        StringBuilder sb = new StringBuilder();

        if(find()){
            for(int i = 2; i <= N; i++){
                if(cost[i] != MAX){
                    sb.append(cost[i]);
                }else{
                    sb.append(-1);
                }
                sb.append("\n");
            }
        }else{
            sb.append(-1);
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static boolean find(){
        Arrays.fill(cost, MAX);
        cost[1] = 0;
        // 총 N - 1번반복
        for(int i = 0; i < N - 1; i++){
            for(int j = 0; j < M; j++) {
                Node now = graph.get(j);
                // 갱신 가능시
                if(cost[now.start] != MAX && cost[now.end] > cost[now.start] + now.value){
                    cost[now.end] = cost[now.start] + now.value;
                }
            }
        }
        long[] cycle = new long[N+1];
        for(int i = 1; i <= N; i++){
            cycle[i] = cost[i];
        }
        // 음수사이클 판별(MAX값처리)
        for(int j = 0; j < M; j++) {
            Node now = graph.get(j);
            if(cost[now.start] != MAX && cost[now.end] > cost[now.start] + now.value){
                cost[now.end] = cost[now.start] + now.value;
            }
        }
        for(int i = 1; i <= N; i++){
            // 음수 사이클 발생
            if(cycle[i] != cost[i]){
                return false;
            }
        }
        return true;
    }
}
class Node{
    int start;
    int end;
    int value;
    Node(int start, int end, int value){
        this.start = start;
        this.end = end;
        this.value = value;
    }
}
