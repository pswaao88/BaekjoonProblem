import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static final int MAX = 987654321;
    static ArrayList<ArrayList<Path>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= V; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Path(b, c));
        }
        int result = fw();
        if(result == MAX){
            bw.write("-1");
        }else{
            bw.write(Integer.toString(result));
        }
        bw.flush();
    }
    // 플로이드 워셜 후 한번더 구하기
    static int fw(){
        int[][] result = new int[V+1][V+1];
        for(int i = 1; i <= V; i++){
            Arrays.fill(result[i], MAX);
        }
        for(int i = 1; i <= V; i++){
            result[i][i] = 0;
            for(int j = 0; j < graph.get(i).size(); j++){
                Path next = graph.get(i).get(j);
                result[i][next.node] = next.value;
            }
        }
        for(int k = 1; k <= V; k++){
            for(int i = 1; i <= V; i++){
                for(int j = 1; j <= V; j++){
                    if(result[i][j] > result[i][k] + result[k][j]){
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }
        int min = MAX;
        // 최소값 찾기
        for(int i = 1; i <= V; i++){
            result[i][i] = MAX;
            for(int j = 1; j <= V; j++){
                if(i == j) continue;
                if(result[i][j] != MAX && result[j][i] != MAX){
                    result[i][i] = Math.min(result[i][i], result[i][j] + result[j][i]);
                }
            }
            min = Math.min(min, result[i][i]);
        }
        return min;
    }
}
class Path{
    int node;
    int value;
    Path(int node, int value){
        this.node = node;
        this.value = value;
    }
}
