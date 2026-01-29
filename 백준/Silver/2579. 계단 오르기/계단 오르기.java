import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] stair = new int[N+1];
        int[] DP = new int[N+1];
        for(int i = 1; i <= N; i++){
            stair[i] = Integer.parseInt(br.readLine());
        }
        DP[1] = stair[1];
        if(N >= 2) DP[2] = DP[1] + stair[2];
        for(int i = 3; i <= N; i++){
            DP[i] = Math.max(stair[i-1] + DP[i-3], DP[i-2]) + stair[i];
        }
        bw.write(Integer.toString(DP[N]));
        bw.flush();
    }
}
