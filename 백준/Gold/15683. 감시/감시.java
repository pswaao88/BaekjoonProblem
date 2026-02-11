import java.io.*;
import java.util.*;

public class Main {
    static int[][] map, nowMap;
    static int N, M, min = 987654321;
    static ArrayList<Coordinate> cctv = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        nowMap = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                nowMap[i][j] = map[i][j];
                if(1 <= map[i][j] && map[i][j] <= 5){
                    cctv.add(new Coordinate(i, j, map[i][j]));
                }
            }
        }
        find(0);
        bw.write(Integer.toString(min));
        bw.flush();

    }
    static void returnBefore(int[][] before){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                nowMap[i][j] = before[i][j];
            }
        }
    }
    static void find(int depth){
        if(depth >= cctv.size()) {
            // 최소값 찾기
            int count = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(nowMap[i][j] == 0){
                        count++;
                    }
                }
            }
            min = Math.min(min, count);
            return;
        }
        int[][] before = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                before[i][j] = nowMap[i][j];
            }
        }
        Coordinate now = cctv.get(depth);
        if(now.type == 1){
            for(int i = 0; i < 4; i++){
                one(now.x, now.y, i);
                find(depth + 1);
                // 다시 원복
                returnBefore(before);
            }

        }else if(now.type == 2){
            for(int i = 0; i < 2; i++){
                two(now.x, now.y, i);
                find(depth + 1);
                returnBefore(before);
            }

        }else if(now.type == 3){
            for(int i = 0; i < 4; i++){
                three(now.x, now.y, i);
                find(depth + 1);
                returnBefore(before);
            }
        }else if(now.type == 4){
            for(int i = 0; i < 4; i++){
                four(now.x, now.y, i);
                find(depth + 1);
                returnBefore(before);
            }
        }else{
            five(now.x, now.y);
            find(depth + 1);
            returnBefore(before);
        }
    }

    static void fillUp(int x, int y){
        for(int i = x - 1; i >= 0; i--){
            if(nowMap[i][y] == 6) {
                break;
            }else if(nowMap[i][y] == 0){
                nowMap[i][y] = -1;
            }
        }
    }
    static void fillRight(int x, int y){
        for(int i = y + 1; i < M; i++){
            if(nowMap[x][i] == 6) {
                break;
            }else if(nowMap[x][i] == 0){
                nowMap[x][i] = -1;
            }
        }
    }
    static void fillDown(int x, int y){
        for(int i = x + 1; i < N; i++){
            if(nowMap[i][y] == 6){
                break;
            }else if(nowMap[i][y] == 0){
                nowMap[i][y] = -1;
            }
        }
    }
    static void fillLeft(int x, int y){
        for(int i = y - 1; i >= 0; i--){
            if(nowMap[x][i] == 6){
                break;
            }else if(nowMap[x][i] == 0){
                nowMap[x][i] = -1;
            }
        }
    }

    static void one(int x, int y, int direction){
        // direction
        // 0 1 2 3 => 위 오 아 왼
        if(direction == 0){
            fillUp(x, y);
        }else if(direction == 1){
            fillRight(x, y);
        }else if(direction == 2){
            fillDown(x, y);
        }else{
            fillLeft(x, y);
        }
    }
    static void two(int x, int y, int direction){
        // direction
        // 0 1 위아래 왼오
        if(direction == 0){
            fillUp(x, y);
            fillDown(x, y);
        }else{
            fillRight(x, y);
            fillLeft(x, y);
        }
    }
    static void three(int x, int y, int direction){
        // direction
        // 0 1 2 3 위오 오아 아왼 왼위
        if(direction == 0){
            fillUp(x, y);
            fillRight(x, y);
        }else if(direction == 1){
            fillRight(x, y);
            fillDown(x, y);
        }else if(direction == 2){
            fillDown(x, y);
            fillLeft(x, y);
        }else{
            fillLeft(x, y);
            fillUp(x, y);
        }
    }
    static void four(int x, int y, int direction){
        // direction
        // 0 1 2 3 왼위오 위오아 오아왼 아왼위
        if(direction == 0){
            fillLeft(x, y);
            fillUp(x, y);
            fillRight(x, y);
        }else if(direction == 1){
            fillUp(x, y);
            fillRight(x, y);
            fillDown(x, y);
        }else if(direction == 2){
            fillRight(x, y);
            fillDown(x, y);
            fillLeft(x, y);
        }else{
            fillDown(x, y);
            fillLeft(x, y);
            fillUp(x, y);
        }
    }
    static void five(int x, int y){
        // direction
        // 0 상하좌우
        fillUp(x, y);
        fillRight(x, y);
        fillDown(x, y);
        fillLeft(x, y);
    }
}
class Coordinate{
    int x;
    int y;
    int type;
    Coordinate(int x, int y, int type){
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
