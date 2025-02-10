import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        long[] DP = new long[101];
        // Base Case
        DP[1] = 1;
        DP[2] = 1;

        for(int i = 3; i <= 100; i++){
            DP[i] = DP[i-2] + DP[i-3];
        }

        for(int t = 0; t < T; t++){
            int N = Integer.parseInt(br.readLine());
            bw.write(Long.toString(DP[N]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
