import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static int[][] DP;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        visited = new boolean[N+1];
        // DP[i][0] i가 얼리어답터가 아닐때 DP[i][1] i가 얼리어답터일때
        DP = new int[N+1][2];

        StringTokenizer st;
        for(int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        find();
        bw.write(Integer.toString(Math.min(DP[1][0], DP[1][1])));
        bw.flush();
    }
    static void find(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        int[] order = new int[N];
        int index = 0;
        while(!q.isEmpty()){
            int now = q.remove();
            visited[now] = true;
            order[index] = now;
            index++;
            for(int i = 0; i < graph.get(now).size(); i++){
                int next = graph.get(now).get(i);
                if(visited[next]) continue;
                q.add(next);
            }
        }
        // 역순으로 리프부터 진행
        for(int i = N-1; i >= 0; i--){
            int now = order[i];
            DP[now][0] = 0;
            DP[now][1] = 1;
            for(int j = 0; j < graph.get(now).size(); j++){
                int child = graph.get(now).get(j);
                DP[now][0] += DP[child][1];
                DP[now][1] += Math.min(DP[child][0], DP[child][1]);
            }
        }
    }
}
