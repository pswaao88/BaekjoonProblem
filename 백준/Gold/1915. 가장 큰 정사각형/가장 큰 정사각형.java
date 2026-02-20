import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] number = new int[n][m];
        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < m; j++){
                number[i][j] = s.charAt(j) - '0';
            }
        }
        // DP[i][j]는 i, j를 가장 오른쪽 끝으로 가지는 가장 큰 정사각형 크기
        // 1 1 1
        // 1 1 1
        // 1 1 1  => DP[2][2] = 3;
        int[][] DP = new int[n][m];
        
        // base case
        int max = 0;
        for(int i = 0; i < n; i++){
            DP[i][0] = number[i][0];
            if(DP[i][0] == 1) max = 1;
        }
        for(int i = 0; i < m; i++){
            DP[0][i] = number[0][i];
            if(DP[0][i] == 1) max = 1;
        }

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(number[i][j] == 1){
                    DP[i][j] = 1;
                    if(number[i-1][j] == 1 && number[i-1][j-1] == 1 && number[i][j-1] == 1){
                        DP[i][j] = Math.min(DP[i-1][j], Math.min(DP[i-1][j-1], DP[i][j-1])) + 1;
                    }
                    max = Math.max(max, DP[i][j]);
                }
            }
        }

        bw.write(Integer.toString(max * max));
        bw.flush();
    }
}
