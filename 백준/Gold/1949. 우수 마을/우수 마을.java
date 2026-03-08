import java.io.*;
import java.util.*;

public class Main {
    static int[][] DP;
    static int[] human;
    static boolean[] visit;
    static ArrayList<Integer> order = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // DP[i][0] 은 i가 우수마을일때 최대값
        // DP[i][1] 은 i가 우수마을이 아닐때 최대값
        DP = new int[N+1][2];
        human = new int[N+1];
        visit = new boolean[N+1];

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= N; i++){
            int now = Integer.parseInt(st.nextToken());
            human[i] = now;
        }
        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        find(1);


        bw.write(Integer.toString(Math.max(DP[1][0], DP[1][1])));
        bw.flush();

    }
    static void find(int root){
        // bfs로 방문 순서 찾기
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        order.add(root);
        visit[root] = true;
        int[] parent = new int[N+1];
        parent[root] = root;
        while(!q.isEmpty()){
            int now = q.remove();
            for(int i = 0; i < graph.get(now).size(); i++){
                int next = graph.get(now).get(i);
                if(visit[next]) continue;
                q.add(next);
                order.add(next);
                visit[next] = true;
                parent[next] = now;
            }
        }

        int size = order.size();
        // 리프 노드 부터 처리
        for(int i = size - 1; i >= 0; i--){
            int now = order.get(i);
            int parentNumber = parent[now];
            DP[now][0] = human[now];
            for(int j = 0; j < graph.get(now).size(); j++){
                int child = graph.get(now).get(j);
                if(child == parentNumber) continue;
                // now가 우수 마을일 경우
                DP[now][0] += DP[child][1];
                // now가 우수 마을이 아닐 경우
                DP[now][1] += Math.max(DP[child][0], DP[child][1]);
            }
        }
    }
}
