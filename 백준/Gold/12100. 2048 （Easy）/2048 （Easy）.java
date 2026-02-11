import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static int max = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        game(0);
        bw.write(Integer.toString(max));
        bw.flush();
    }
    static void game(int depth){
        if(depth >= 5){
            findMax();
            return;
        }
        // 전 상태 기억
        int[][] before = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                before[i][j] = board[i][j];
            }
        }
        for(int i = 0; i < 4; i++){
            if(i == 0){// 위
                tilt(0);
            }else if(i == 1){// 오른쪽
                tilt(1);
            }else if(i == 2){// 아래
                tilt(2);
            }else{// 왼쪽
                tilt(3);
            }
            game(depth+1);
            // 원상복구
            for(int x = 0; x < N; x++){
                for(int y = 0; y < N; y++){
                    board[x][y] = before[x][y];
                }
            }
        }
    }

    static void tilt(int direction){
        if(direction == 0){// 위로 기울이기 아래서 부터 stack에 쌓기
            for(int y = 0; y < N; y++){
                Stack<Integer> stack = new Stack();
                for(int x = N-1; x >= 0; x--){
                    if(board[x][y] != 0){
                        stack.push(board[x][y]);
                    }
                }
                int now = -1;
                int before = -1;
                int indexX = 0;
                while(!stack.isEmpty()){
                    if(now == -1){
                        now = stack.pop();
                        if(stack.isEmpty()){
                            board[indexX][y] = now;
                            indexX++;
                            break;
                        }
                    }else{
                        before = now;
                        now = stack.pop();
                        if(now != before){
                            board[indexX][y] = before;
                            indexX++;
                            if(stack.isEmpty()){
                                board[indexX][y] = now;
                                indexX++;
                                break;
                            }
                        }else{
                            board[indexX][y] = now + before;
                            now = -1;
                            before = 1;
                            indexX++;
                        }

                    }
                }
                for(int x = indexX; x < N; x++){
                    board[x][y] = 0;
                }
            }
            return;
        }
        if(direction == 1){
            for(int x = 0; x < N; x++){
                Stack<Integer> stack = new Stack();
                for(int y = 0; y < N; y++){
                    if(board[x][y] != 0){
                        stack.push(board[x][y]);
                    }
                }
                int now = -1;
                int before = -1;
                int indexY = N-1;
                while(!stack.isEmpty()){
                    if(now == -1){
                        now = stack.pop();
                        if(stack.isEmpty()){
                            board[x][indexY] = now;
                            indexY--;
                            break;
                        }
                    }else{
                        before = now;
                        now = stack.pop();
                        if(now != before){
                            board[x][indexY] = before;
                            indexY--;
                            if(stack.isEmpty()){
                                board[x][indexY] = now;
                                indexY--;
                                break;
                            }
                        }else{
                            board[x][indexY] = now + before;
                            now = -1;
                            before = 1;
                            indexY--;
                        }

                    }
                }
                for(int y = indexY; y >= 0; y--){
                    board[x][y] = 0;
                }
            }
            return;
        }
        if(direction == 2){
            for(int y = 0; y < N; y++){
                Stack<Integer> stack = new Stack();
                for(int x = 0; x < N; x++){
                    if(board[x][y] != 0){
                        stack.push(board[x][y]);
                    }
                }
                int now = -1;
                int before = -1;
                int indexX = N-1;
                while(!stack.isEmpty()){
                    if(now == -1){
                        now = stack.pop();
                        if(stack.isEmpty()){
                            board[indexX][y] = now;
                            indexX--;
                            break;
                        }
                    }else{
                        before = now;
                        now = stack.pop();
                        if(now != before){
                            board[indexX][y] = before;
                            indexX--;
                            if(stack.isEmpty()){
                                board[indexX][y] = now;
                                indexX--;
                                break;
                            }
                        }else{
                            board[indexX][y] = now + before;
                            now = -1;
                            before = 1;
                            indexX--;
                        }

                    }
                }
                for(int x = indexX; x >= 0; x--){
                    board[x][y] = 0;
                }
            }
            return;
        }
        for(int x = 0; x < N; x++){
            Stack<Integer> stack = new Stack();
            for(int y = N-1; y >= 0; y--){
                if(board[x][y] != 0){
                    stack.push(board[x][y]);
                }
            }
            int now = -1;
            int before = -1;
            int indexY = 0;
            while(!stack.isEmpty()){
                if(now == -1){
                    now = stack.pop();
                    if(stack.isEmpty()){
                        board[x][indexY] = now;
                        indexY++;
                        break;
                    }
                }else{
                    before = now;
                    now = stack.pop();
                    if(now != before){
                        board[x][indexY] = before;
                        indexY++;
                        if(stack.isEmpty()){
                            board[x][indexY] = now;
                            indexY++;
                            break;
                        }
                    }else{
                        board[x][indexY] = now + before;
                        now = -1;
                        before = 1;
                        indexY++;
                    }
                }
            }
            for(int y = indexY; y < N; y++){
                board[x][y] = 0;
            }
        }
    }

    static void findMax(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                max = Math.max(max, board[i][j]);
            }
        }
    }
}
