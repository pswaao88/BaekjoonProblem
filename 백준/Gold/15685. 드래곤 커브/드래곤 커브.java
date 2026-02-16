import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map = new int[101][101];
    static int[] directions = new int[1 << 10];
    static int startDirection, x, y;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            startDirection = d;
            makeDirection(0, g);
        }
        bw.write(Integer.toString(find()));
        bw.flush();

    }
    static int find(){
        int result = 0;
        for(int i = 0; i <= 99; i++){
            for(int j = 0; j <= 99; j++){
                if((map[i][j] & map[i][j + 1] & map[i + 1][j] & map[i + 1][j + 1]) == 1){
                    result++;
                }
            }
        }
        return result;
    }
    static void makeDirection(int nowGeneration, int targetGeneration){
        if(nowGeneration > targetGeneration){
            makeDragon(targetGeneration);
            return;
        }
        if(nowGeneration == 0){
            directions[0] = startDirection;
            makeDirection(nowGeneration + 1, targetGeneration);
            return;
        }
        // 분석할 방향
        int count = (1 << nowGeneration) / 2;
        int index = count;
        for(int i = count - 1; i >= 0; i--, index++){
            directions[index] = (directions[i] + 1) % 4;
        }
        makeDirection(nowGeneration + 1, targetGeneration);
    }
    static void makeDragon(int generation){
        map[x][y] = 1;
        int count = (1 << generation);
        int nowX = x;
        int nowY = y;
        for(int i = 0; i < count; i++){
            int nextX = nowX + dx[directions[i]];
            int nextY = nowY + dy[directions[i]];
            map[nextX][nextY] = 1;
            nowX = nextX;
            nowY = nextY;
        }
    }
}
