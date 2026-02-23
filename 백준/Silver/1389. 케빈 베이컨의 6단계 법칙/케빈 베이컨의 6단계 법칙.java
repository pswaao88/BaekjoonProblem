import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[][] result;
    static int N, M;
    static final int MAX = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        result = new int[N+1][N+1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        fw();
        int kevin  = 0;
        int min = MAX;
        for(int i = 1; i <= N; i++){
            int total = 0;
            for(int j = 1; j <= N; j++){
                if(i == j) continue;
                total += result[i][j];
            }
            if(min > total){
                kevin = i;
                min = total;
            }
        }
        bw.write(Integer.toString(kevin));
        bw.flush();
    }
    static void fw(){
        // 최대값으로 초기화
        for(int i = 1; i <= N; i++){
            Arrays.fill(result[i], MAX);
        }
        // 시작점 초기화
        for(int i = 1; i <= N; i++){
            result[i][i] = 0;
        }
        // i, j 최소값 초기화
        for(int i = 1; i <= N; i++){
            for(int j = 0; j < graph.get(i).size(); j++){
                int next = graph.get(i).get(j);
                result[i][next] = 1;
            }
        }
        // 플로이드 워셜
        // k를 거칠때
        for(int k = 1; k <= N; k++){

            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    // 갱신 가능
                    if(result[i][j] > result[i][k] + result[k][j]){
                        result[i][j] = result[i][k] + result[k][j];
                    }
                }
            }
        }
    }
}
