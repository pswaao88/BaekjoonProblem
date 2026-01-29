import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] DP = new int[11];
        DP[0] = 1;
        DP[1] = 1;
        DP[2] = 2;
        for(int i = 3; i <= 10; i++){
            DP[i] = DP[i-1] + DP[i-2] + DP[i-3];
        }
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int now = Integer.parseInt(br.readLine());
            sb.append(DP[now]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();

    }
}
