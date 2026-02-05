import java.io.*;
import java.util.*;

public class Main {
    static final int APPLE = 1;
    static final int SNAKE = 2;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    static int N, direction = 0;
    static Deque<Coordinate> deq = new LinkedList<>();
    static Queue<Change> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a-1][b-1] = APPLE;
        }
        int L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            char b = st.nextToken().charAt(0);
            q.add(new Change(a, b));
        }
        map[0][0] = SNAKE;
        deq.addLast(new Coordinate(0, 0));
        int result = game(0, 0, 0);
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static int game(int x, int y, int time){
        checkChange(time);
        // 다음 진행할 곳
        int nextX = x + dx[direction];
        int nextY = y + dy[direction];

        // 벽에 부딪힘
        if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) return time + 1;
        // 몸과 부딪힘
        if(map[nextX][nextY] == SNAKE) return time + 1;
        // 사과인경우
        if(map[nextX][nextY] == APPLE){
            // 머리 추가
            deq.addLast(new Coordinate(nextX, nextY));
            // 사과 대신 뱀
            map[nextX][nextY] = SNAKE;
            // 방향전환 검사

            return game(nextX, nextY, time + 1);
        }else{
            // 머리 추가
            deq.addLast(new Coordinate(nextX, nextY));
            map[nextX][nextY] = SNAKE;
            // 꼬리 제거
            Coordinate tail = deq.removeFirst();
            map[tail.x][tail.y] = 0;
            // 방향전환 검사
            return game(nextX, nextY, time + 1);
        }
    }
    static void checkChange(int time){
        if(q.isEmpty()) return;
        if(q.peek().X == time){
            Change now = q.remove();
            if(now.C == 'L'){
                direction--;
                if(direction < 0) direction = 3;
            }else{
                direction++;
                if(direction > 3) direction = 0;
            }
        }
    }
}
class Coordinate{
    int x;
    int y;
    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Change{
    int X;
    char C;
    Change(int X, char C){
        this.X = X;
        this.C = C;
    }
}
