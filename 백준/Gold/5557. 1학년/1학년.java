import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        int result = number[N-1];
        long[] DP = new long[21];
        DP[number[0]] = 1;

        // index 1 ~ N-2까지만 연산 대상
        for(int i = 1; i <= N - 2; i++){
            int now = number[i];
            long[] tmp = new long[21];
            for(int j = 0; j <= 20; j++){
                if(DP[j] < 1) continue;
                int nextOne = j - now;
                // 범위안에 있는경우
                if(isOk(nextOne)){
                    tmp[nextOne] += DP[j];
                }
                int nextTwo = j + now;
                if(isOk(nextTwo)){
                    tmp[nextTwo] += DP[j];
                }
            }
            DP = tmp;
        }
        bw.write(Long.toString(DP[result]));
        bw.flush();
    }
    static boolean isOk(int next){
        return (0 <= next && next <= 20);
    }
}
