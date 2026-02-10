import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<ArrayList<Integer>> pd = new ArrayList<>();
    static int[] indegree;
    static ArrayList<Integer> singer = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N+1];
        for(int i = 0; i <= N; i++){
            pd.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int now = Integer.parseInt(st.nextToken());
            for(int j = 0; j < size - 1; j++){
                int next = Integer.parseInt(st.nextToken());
                pd.get(now).add(next);
                indegree[next]++;
                now = next;
            }
        }
        findSinger();
        StringBuilder sb = new StringBuilder();
        if(singer.size() != N){
            sb.append(0);
        }else{
            for(int i = 0; i < N; i++){
                sb.append(singer.get(i)).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static void findSinger(){
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++){
            if(indegree[i] == 0) {
                q.add(i);
            }
        }
        while(!q.isEmpty()){
            int now = q.remove();
            singer.add(now);
            for(int i = 0; i < pd.get(now).size(); i++){
                int next = pd.get(now).get(i);
                indegree[next]--;
                if(indegree[next] == 0){
                    q.add(next);
                }
            }
        }
    }
}
