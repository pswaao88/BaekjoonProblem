import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] W = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 어차피 사이클이기 때문에 0번에서 시작해도 됨
        int[][] DP = new int[(1 << N)][N];
        for(int nowPath = 0; nowPath < (1 << N); nowPath++){
            for(int now = 0; now < N; now++){
                int nowBinary = (1 << now);
                // 현재까지 위치 & 현재위치 값이 0이 아니면 올바른 path이므로 계산
                if((nowPath & nowBinary) != 0){

                    DP[nowPath][now] = 987654321;
                    int beforePath = (nowPath & (~nowBinary));
                    // 시작점 처리 (beforePath가 0이면 시작점 처리)
                    if(beforePath == 0) {
                        if(now == 0) DP[nowPath][now] = 0; // 시작점 비용은 0
                        else DP[nowPath][now] = 987654321;       // 0번 아닌데 beforePath가 0일 순 없음
                        continue;
                    }
                    for(int before = 0; before < N; before++){
                        int beforeBinary = (1 << before);
                        if((beforePath & beforeBinary) != 0 && W[before][now] != 0){
                            DP[nowPath][now] = Math.min(DP[nowPath][now], DP[beforePath][before] + W[before][now]);
                        }
                    }
                }
            }
        }
        int min = 987654321;
        for(int i = 1; i < N; i++){
            if(DP[(1 << N) - 1][i] != 0 && W[i][0] != 0){
                min = Math.min(min, DP[(1 << N) - 1][i] + W[i][0]);
            }
        }
        bw.write(Integer.toString(min));
        bw.flush();
    }
}
