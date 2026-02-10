import java.io.*;
import java.util.*;

public class Main {
    // 인디그리 계산용 그래프
    static ArrayList<ArrayList<Integer>> buildings = new ArrayList<>();
    static int[] indegree;
    static int[] time;
    static int[] totalTime;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i <= N; i++){
            buildings.add(new ArrayList<>());
        }
        time = new int[N+1];
        totalTime = new int[N+1];
        indegree = new int[N+1];

        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int beforeBuilding;
            while((beforeBuilding = Integer.parseInt(st.nextToken())) != -1){
                buildings.get(beforeBuilding).add(i);
                indegree[i]++;
            }
        }
        findTime();
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(totalTime[i]).append("\n");
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
            // 시간 갱신
            int now = q.remove();
            totalTime[now] += time[now];

            // now와 연결된 indegree 감소
            for(int i = 0; i < buildings.get(now).size(); i++){
                int next = buildings.get(now).get(i);
                totalTime[next] = Math.max(totalTime[next], totalTime[now]);
                indegree[next]--;
                if(indegree[buildings.get(now).get(i)] == 0){
                    q.add(next);
                }
            }

        }
    }
}
