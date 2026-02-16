import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        int[][] rowMap = new int[N][N];
        int[][] colMap = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0 ; i < N; i++){
            st =  new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                rowMap[i][j] = Integer.parseInt(st.nextToken());
                colMap[j][i] = rowMap[i][j];
            }
        }
        int result = 0;
        for(int i = 0; i < N; i++){
            if(find(rowMap[i])){
                result++;
            }
            if(find(colMap[i])){
                result++;
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();

    }
    static boolean find(int [] target){
        boolean[] visited = new boolean[N];
        // 왼 -> 오
        for(int i = 0; i < N-1; i++){
            int now = target[i];
            int next = target[i+1];
            if(now == next) continue;
            // 2이상 차이나면 불가능
            int diff = now - next;
            if(Math.abs(diff) > 1){
                return false;
            }
            if(diff == 1){
                // 내리막길
                for(int j = i + 1; j <= i + L; j++){
                    if(j >= N || visited[j] || next != target[j]){
                        return false;
                    }
                    visited[j] = true;
                }
            }else {
                // 오르막길
                for(int j = i; j >= i - L + 1; j--){
                    if(j < 0 || visited[j] || now != target[j]){
                        return false;
                    }
                    visited[j] = true;
                }
            }
        }
        return true;
    }
}
