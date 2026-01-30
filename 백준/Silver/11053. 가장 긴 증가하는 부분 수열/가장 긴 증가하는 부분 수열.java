import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] number = new int[N+1];
        int[] DP = new int[N+1];
        for(int i = 1; i <= N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1; i <= N; i++){
            DP[i] = 1;
            for(int j = 1; j < i; j++){
                if(number[j] >= number[i]) continue;
                DP[i] = Math.max(DP[j] + 1, DP[i]);
            }
        }
        int max = 0;
        for(int i = 1; i <= N; i++){
            max = Math.max(max, DP[i]);
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
