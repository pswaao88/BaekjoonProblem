import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] DP = new int[n+1];
        DP[1] = 1;
        for(int i = 2; i <= n; i++){
            if(i == 2) DP[i] = 2;
            else{
                DP[i] += DP[i-1] % 10007;
                DP[i] += DP[i-2] % 10007;
            }
            DP[i] = DP[i] % 10007;
        }
        bw.write(Integer.toString(DP[n]));
        bw.flush();
    }
}
