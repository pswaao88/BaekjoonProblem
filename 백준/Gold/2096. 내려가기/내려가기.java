import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] score = new int[N+1][3];
        int[][] min = new int[N+1][3];
        int[][] max = new int[N+1][3];

        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
                min[i][j] = 987654321;
            }
        }
        // base case
        for(int i = 0; i < 3; i++){
            min[1][i] = score[1][i];
            max[1][i] = score[1][i];
        }

        for(int i = 2; i <= N; i++){
            for(int j = 0; j < 3; j++){
                // 왼쪽
                if(j == 0){
                    min[i][j] = Math.min(min[i][j], min[i-1][j] + score[i][j]);
                    min[i][j] = Math.min(min[i][j], min[i-1][j + 1] + score[i][j]);

                    max[i][j] = Math.max(max[i][j], max[i-1][j] + score[i][j]);
                    max[i][j] = Math.max(max[i][j], max[i-1][j + 1] + score[i][j]);
                }else if(j == 1){ // 중간
                    min[i][j] = Math.min(min[i][j], min[i-1][j - 1] + score[i][j]);
                    min[i][j] = Math.min(min[i][j], min[i-1][j] + score[i][j]);
                    min[i][j] = Math.min(min[i][j], min[i-1][j + 1] + score[i][j]);

                    max[i][j] = Math.max(max[i][j], max[i-1][j - 1] + score[i][j]);
                    max[i][j] = Math.max(max[i][j], max[i-1][j] + score[i][j]);
                    max[i][j] = Math.max(max[i][j], max[i-1][j + 1] + score[i][j]);
                }else{ // 오른쪽
                    min[i][j] = Math.min(min[i][j], min[i-1][j - 1] + score[i][j]);
                    min[i][j] = Math.min(min[i][j], min[i-1][j] + score[i][j]);

                    max[i][j] = Math.max(max[i][j], max[i-1][j - 1] + score[i][j]);
                    max[i][j] = Math.max(max[i][j], max[i-1][j] + score[i][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int minResult = 987654321;
        int maxResult = 0;
        for(int i = 0; i < 3; i++){
            minResult = Math.min(minResult, min[N][i]);
            maxResult = Math.max(maxResult, max[N][i]);
        }
        sb.append(maxResult).append(" ").append(minResult);
        bw.write(sb.toString());
        bw.flush();
    }
}
