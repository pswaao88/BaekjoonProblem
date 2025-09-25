import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] rectangular = new int[N][M];

        for(int i = 0; i < N; i++){
            String line = br.readLine();
            for(int j = 0; j < M; j++){
                rectangular[i][j] = line.charAt(j) - '0';
            }
        }
        int nowLength = 2;
        int maxLength = 1;
        while(nowLength <= Math.max(N,M)){
            boolean find = false;
            for(int i = 0; i <= N - nowLength; i++){
                for(int j = 0; j <= M - nowLength; j++){
                    int leftTop = rectangular[i][j];
                    int leftBottom = rectangular[i+nowLength-1][j];
                    int rightTop = rectangular[i][j+nowLength-1];
                    int rightBottom = rectangular[i+nowLength-1][j+nowLength-1];
                    if((leftTop == leftBottom) && (leftBottom == rightTop) && (rightTop == rightBottom)){
                        maxLength = nowLength;
                        find = true;
                        break;
                    }
                }
                if(find){
                    break;
                }
            }
            nowLength++;
        }
        bw.write(Integer.toString(maxLength * maxLength));
        bw.flush();
        bw.close();
        br.close();
    }
}
