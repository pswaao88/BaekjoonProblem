import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] cards = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int[] DP = new int[N+1];
        for(int i = 1; i <= N; i++){
            // 기본 초기화
            DP[i] = cards[i];
            for(int j = 1; j <= i - 1; j++){
                DP[i] = Math.min(DP[i], DP[i - j] + cards[j]);
            }
        }
        bw.write(Integer.toString(DP[N]));
        bw.flush();

    }
}
