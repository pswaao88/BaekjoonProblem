import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] DP = new int[N+1][3]; // i에서 있을 때

        // base case 아무것도 없는 경우
        DP[1][0] = 1;
        DP[1][1] = 1;
        DP[1][2] = 1;

        for(int i = 2; i <= N; i++){
            // 아무것도 없는 경우는 i-1과 dependency 발생
            DP[i][0] = (DP[i-1][0] + DP[i-1][1] + DP[i-1][2]) % 9901;
            // 1번째에 있는 경우
            DP[i][1] = (DP[i-1][0] + DP[i-1][2]) % 9901;
            // 2번째에 있는 경우
            DP[i][2] = (DP[i-1][0] + DP[i-1][1]) % 9901;

        }
        bw.write(Integer.toString((DP[N][0] + DP[N][1] + DP[N][2]) % 9901));
        bw.flush();
        bw.close();
        br.close();

    }
}
