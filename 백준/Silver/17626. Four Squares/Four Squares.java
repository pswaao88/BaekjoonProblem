import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] DP = new int[n+1];
        DP[1] = 1;
        for(int i = 2; i <= n; i++){
            DP[i] = 10;
            for(int j = 1; j <= (int)Math.sqrt(i); j++){
                int now = j * j;
                DP[i] = Math.min(DP[i], DP[i - now] + 1);
            }
        }
        bw.write(Integer.toString(DP[n]));
        bw.flush();
    }
}
