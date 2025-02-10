import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N+1];
        Arrays.fill(DP, 987654321);
        DP[0] = 0;
        DP[1] = 1;
        for(int i = 2; i <= N; i++){
            for(int j = 1; j <= (int)Math.sqrt(i); j++){
                DP[i] = Math.min(DP[i], DP[i - (j * j)] + 1);
            }
        }
        bw.write(Integer.toString(DP[N]));
        bw.flush();
        bw.close();
        br.close();

    }
}
