import java.io.*;
import java.util.*;

public class Main {
    static Dice dice;
    static int[][] map;
    static int nowX, nowY, nextX, nextY, N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        nowX = x;
        nowY = y;
        dice = new Dice(0, 0, 0, 0, 0, 0);

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int d = Integer.parseInt(st.nextToken());
            if(isOk(d)){
                nowX = nextX;
                nowY = nextY;
                // 굴리기
                roll(d);
                // 바닥 로직
                if(map[nowX][nowY] == 0){
                    map[nowX][nowY] = dice.bottom;
                }else{
                    dice.bottom = map[nowX][nowY];
                    map[nowX][nowY] = 0;
                }
                sb.append(dice.top).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
    public static boolean isOk(int direction){
        if(direction == 1){ // 동
            nextX = nowX;
            nextY = nowY + 1;
        }else if(direction == 2){ // 서
            nextX = nowX;
            nextY = nowY - 1;
        }else if(direction == 3){ // 북
            nextX = nowX - 1;
            nextY = nowY;
        }else{ // 남
            nextX = nowX + 1;
            nextY = nowY;
        }

        return (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M);
    }
    public static void roll(int direction){
        int tmpTop = dice.top;
        int tmpBottom = dice.bottom;
        int tmpLeft = dice.left;
        int tmpRight = dice.right;
        int tmpFront = dice.front;
        int tmpBack = dice.back;

        // 동쪽
        if(direction == 1){
            dice.top = tmpLeft;
            dice.left = tmpBottom;
            dice.bottom = tmpRight;
            dice.right = tmpTop;
            return;
        }
        // 서쪽
        if(direction == 2){
            dice.top = tmpRight;
            dice.right = tmpBottom;
            dice.bottom = tmpLeft;
            dice.left = tmpTop;
            return;
        }
        // 북쪽
        if(direction == 3){
            dice.top = tmpBack;
            dice.back = tmpBottom;
            dice.bottom = tmpFront;
            dice.front = tmpTop;
            return;
        }
        // 남쪽
        dice.top = tmpFront;
        dice.front = tmpBottom;
        dice.bottom = tmpBack;
        dice.back = tmpTop;

    }
}
class Dice{
    int top;
    int bottom;
    int left;
    int right;
    int front;
    int back;
    Dice(int top, int bottom, int left, int right, int front, int back){
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
        this.front = front;
        this.back = back;
    }
}
