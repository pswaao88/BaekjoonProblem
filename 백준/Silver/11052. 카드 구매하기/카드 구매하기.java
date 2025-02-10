import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] card = new int[N+1];
        int[] DP = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++){
            card[i] = Integer.parseInt(st.nextToken());
        }

        DP[1] = card[1];
        for(int i = 2; i <= N; i++){
            DP[i] = card[i];
            for(int j = 1; j <= i; j++){
                DP[i] = Math.max(DP[i], DP[i-j] + card[j]);
            }
        }
        bw.write(Integer.toString(DP[N]));
        bw.flush();
        bw.close();
        br.close();

    }
}
