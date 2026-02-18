import java.io.*;
import java.util.*;

public class Main {
    static boolean[][] ladders;
    static final int MAX = 987654321;
    static int N, M, H, min = MAX;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladders = new boolean[H+2][N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // 사다리 연결
            ladders[a][b] = true;
        }
        for(int i = 0; i <= 3; i++){
            find(0, 1, 1, i);
            if(min != MAX){
                bw.write(Integer.toString(min));
                bw.flush();
                return;
            }
        }
        bw.write(Integer.toString(-1));
        bw.flush();
    }
    static void find(int count, int startN, int startH, int target){
        if(count == target){
            if(isRight()){
                min = count;
            }
            return;
        }

        for(int i = startH; i <= H; i++){
            if(i == startH){
                for(int j = startN; j < N; j++){
                    // 사다리를 놓을 수 있을 때 재귀
                    if(isOk(i, j)){
                        ladders[i][j] = true;
                        find(count + 1, j + 1, i, target);
                        ladders[i][j] = false;
                    }
                }
            }else{
                for(int j = 1; j < N; j++){
                    // 사다리를 놓을 수 있을 때 재귀
                    if(isOk(i, j)){
                        ladders[i][j] = true;
                        find(count + 1, j + 1, i, target);
                        ladders[i][j] = false;
                    }
                }
            }

        }
    }
    static boolean isOk(int depth, int left){
        // left, left + 1 연결 x
        if(!ladders[depth][left]){
            if(left - 1 >= 1 && ladders[depth][left - 1]){
                return false;
            }
            if(left + 1 < N && ladders[depth][left + 1]){
                return false;
            }
            return true;
        }
        return false;
    }
    static boolean isRight(){
        int[] now = new int[N+1];
        for(int i = 1; i <= N; i++){
            now[i] = i;
        }
        for(int i = 1; i <= H; i++){
            for(int j = 1; j < N; j++){
                if(ladders[i][j]){
                    int tmp = now[j];
                    now[j] = now[j+1];
                    now[j+1] = tmp;
                }
            }
        }
        for(int i = 1; i <= N; i++){
            if(now[i] != i){
                return false;
            }
        }
        return true;
    }

}
