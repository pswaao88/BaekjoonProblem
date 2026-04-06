import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // n은 5000, m은 10000
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = (int)(Math.round(Double.parseDouble(st.nextToken()) * 100));
            if(n == 0 && m == 0) break;
            int[] DP = new int[m+1];
            Candy[] candies = new Candy[n];
            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = (int)(Math.round(Double.parseDouble(st.nextToken()) * 100));
                candies[i] = new Candy(a, b);
            }

            for(int i = 1; i <= m; i++){
                for(int j = 0; j < n; j++){
                    if(i - candies[j].p < 0) continue;
                    DP[i] = Math.max(DP[i], DP[i - candies[j].p] + candies[j].c);
                }
            }
            sb.append(DP[m]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
class Candy{
    int c;
    int p;
    Candy(int c, int p){
        this.c = c;
        this.p = p;
    }
}
