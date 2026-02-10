import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> building;
    static int[] time;
    static int[] totalTime;
    static int[] indegree;
    static int N, K, W;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(T-->0){

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            time = new int[N+1];
            totalTime = new int[N+1];
            indegree = new int[N+1];
            building = new ArrayList<>();
            for(int i = 0; i <= N; i++){
                building.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= N; i++){
                time[i] = Integer.parseInt(st.nextToken());
            }
            // 초기화
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                // parent
                int a = Integer.parseInt(st.nextToken());
                // child
                int b = Integer.parseInt(st.nextToken());
                building.get(a).add(b);
                indegree[b]++;
            }
            findTime();
            W = Integer.parseInt(br.readLine());
            sb.append(totalTime[W]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static void findTime(){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0){
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int now = q.remove();
            totalTime[now] += time[now];
            for(int i = 0; i < building.get(now).size(); i++){
                int next = building.get(now).get(i);
                totalTime[next] = Math.max(totalTime[next], totalTime[now]);
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                }
            }
        }
    }
}
