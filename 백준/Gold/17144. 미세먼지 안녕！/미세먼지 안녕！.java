import java.io.*;
import java.util.*;

public class Main {
    static int[][] room;
    static int R,C,T;
    static int cleanX1, cleanX2, cleanY;
    static Queue<Coordinate> dust = new LinkedList<>();
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        room = new int[R][C];
        boolean cleanFirst = true;

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
                if(room[i][j] == -1){
                    if(cleanFirst){
                        cleanX1 = i;
                        cleanY = j;
                        cleanFirst = false;
                    }else{
                        cleanX2 = i;
                    }
                }
            }
        }
        for(int i = 0; i < T; i++){
            spread();
            clean();
            cleanReverse();
        }
        int result = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(room[i][j] <= 0) continue;
                result += room[i][j];
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static void spread(){
        // 먼지 정보 가져오기
        dust.clear();
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(room[i][j] > 0){
                    dust.add(new Coordinate(i, j, room[i][j]));
                }
            }
        }
        // 먼지 전부 확산할때까지 반복
        while(!dust.isEmpty()){
            Coordinate now = dust.remove();
            // 확살한 먼지 양
            int minusDust = now.value / 5;
            for(int i = 0; i < 4; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if(nextX < 0 || nextX >= R || nextY < 0 || nextY >= C || room[nextX][nextY] == -1) continue;
                room[nextX][nextY] += minusDust;
                room[now.x][now.y] -= minusDust;
            }
        }
    }
    // 시계반대방향
    static void cleanReverse(){
        // 위에서 아래로
        for(int x = cleanX1 - 1; x >= 1; x--){
            room[x][cleanY] = room[x-1][cleanY];
        }
        // 오른쪽에서 왼쪽으로(맨위)
        for(int y = 0; y <= C-2; y++){
            room[0][y] = room[0][y+1];
        }
        // 아래에서 위로
        for(int x = 0; x <= cleanX1; x++){
            room[x][C-1] = room[x + 1][C-1];
        }
        // 왼쪽에서 오른쪽으로
        for(int y = C-1; y >= 1; y--){
            room[cleanX1][y] = room[cleanX1][y-1];
        }
        room[cleanX1][cleanY+1] = 0;
    }
    // 시계방향
    static void clean(){
        // 아래에서 위로
        for(int x = cleanX2 + 1; x <= R-2; x++){
            room[x][cleanY] = room[x + 1][cleanY];
        }
        // 오른쪽에서 왼쪽으로
        for(int y = 0; y <= C - 2; y++){
            room[R-1][y] = room[R-1][y+1];
        }
        // 위에서 아래로
        for(int x = R-1; x >= cleanX2 + 1; x--){
            room[x][C-1] = room[x - 1][C-1];
        }
        // 왼쪽에서 오른쪽으로
        for(int y = C - 1; y >= cleanY + 1; y--){
            room[cleanX2][y] = room[cleanX2][y - 1];
        }
        room[cleanX2][cleanY+1] = 0;
    }
}
class Coordinate{
    int x;
    int y;
    int value;
    Coordinate(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
}
