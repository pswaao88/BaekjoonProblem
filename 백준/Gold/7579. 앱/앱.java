import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] memory = new int[N];
        for(int i = 0; i < N; i++){
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] cost = new int[N];
        for(int i = 0; i < N; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }
        // DP[i] => 비용이 i일떄 최대 값
        int[] DP = new int[10001];
        for(int i = 0; i < N; i++){
            int nowCost = cost[i];
            int nowMemory = memory[i];
            for(int j = 10000; j >= 0; j--){
                int nextCost = j + nowCost;
                // 범위안에 있을 경우만
                if(nextCost > 10000) continue;
                DP[nextCost] = Math.max(DP[nextCost], DP[j] + nowMemory);
            }
        }
        int min = 0;
        for(int i = 0; i <= 10000; i++){
            if(DP[i] >= M){
                min = i;
                break;
            }
        }
        bw.write(Integer.toString(min));
        bw.flush();
    }
}
