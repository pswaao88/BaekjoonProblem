import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] number = new int[n+1];
        int[] DP = new int[n+1];
        for(int i = 1; i <= n; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        DP[1] = number[1];
        for(int i = 2; i <= n; i++){
            if(number[i] < 0){
                DP[i] = number[i];
                DP[i] = Math.max(DP[i], DP[i-1] + number[i]);
            }else{
                if(DP[i-1] >= 0) {
                    DP[i] = DP[i-1] + number[i];
                }else {
                    DP[i] = number[i];
                }
            }
        }
        int max = -1001;
        for(int i = 1; i <= n; i++){
            max = Math.max(max, DP[i]);
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
