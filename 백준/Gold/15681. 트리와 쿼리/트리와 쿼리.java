import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] DP;
    static boolean[] visit;
    static int N, R, Q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        DP = new int[N+1];
        visit = new boolean[N+1];
        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            graph.get(U).add(V);
            graph.get(V).add(U);
        }
        find(R);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++){
            int q = Integer.parseInt(br.readLine());
            sb.append(DP[q]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static int find(int now){
        visit[now] = true;
        DP[now] = 1;
        for(int i = 0; i < graph.get(now).size(); i++){
            int next = graph.get(now).get(i);
            if(visit[next]) continue;
            DP[now] += find(next);
        }
        return DP[now];
    }
}
