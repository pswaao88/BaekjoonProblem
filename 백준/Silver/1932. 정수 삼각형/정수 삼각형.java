import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] tri = new int[n][n];
        int[][] DP = new int[n][n];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++){
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0;
        DP[0][0] = tri[0][0];
        for(int i = 0; i < n; i++){
            if(i != 0){
                for(int j = 0; j <= i; j++){
                    DP[i][j] = DP[i-1][j];
                    if(j != 0) {
                        DP[i][j] = Math.max(DP[i][j], DP[i-1][j-1]);
                    }
                    DP[i][j] += tri[i][j];
                }
            }
            if(i == n-1){
                for(int j = 0; j < n; j++){
                    max = Math.max(max, DP[i][j]);
                }
            }
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
