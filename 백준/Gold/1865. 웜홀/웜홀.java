import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Road> graph;
    static int[] time;
    static int N, M, W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph = new ArrayList<>();
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            for(int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph.add(new Road(a, b, c));
                graph.add(new Road(b, a, c));
            }
            for(int i = 0; i < W; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph.add(new Road(a, b, -c));
            }
            if(find()){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static boolean find(){
        time = new int[N+1];
        for(int i = 0; i < N-1; i++){
            for(int j = 0; j < graph.size(); j++){
                Road now = graph.get(j);
                if(time[now.end] > time[now.start] + now.value){
                    time[now.end] = time[now.start] + now.value;
                }
            }
        }
        int[] cycle = new int[N+1];
        for(int i = 1; i <= N; i++){
            cycle[i] = time[i];
        }

        for(int j = 0; j < graph.size(); j++){
            Road now = graph.get(j);
            if(time[now.end] > time[now.start] + now.value){
                time[now.end] = time[now.start] + now.value;
            }
        }

        for(int i = 1; i <= N; i++){
            if(cycle[i] != time[i]) return true;
        }
        return false;
    }
}
class Road{
    int start;
    int end;
    int value;
    Road(int start, int end, int value){
        this.start = start;
        this.end = end;
        this.value = value;
    }
}
