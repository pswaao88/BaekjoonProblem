import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        int count = 0;
        Coin c1 = null, c2 = null;
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = s.charAt(j);
                if(board[i][j] == 'o'){
                    if(count == 0){
                        c1 = new Coin(i, j, false);
                        count++;
                    }else{
                        c2 = new Coin(i, j, false);
                    }
                }
            }
        }
        int result = find(c1, c2);
        System.out.println(result);

    }
    static int find(Coin c1, Coin c2){
        PriorityQueue<Wallet> pq = new PriorityQueue<>((w1, w2) -> w1.count - w2.count);
        pq.add(new Wallet(c1, c2, 0));
        while(!pq.isEmpty()){
            Wallet now = pq.remove();
            if(now.count > 10) return -1;
            Coin one = new Coin(now.c1.x, now.c1.y, now.c1.isOver);
            Coin two = new Coin(now.c2.x, now.c2.y, now.c2.isOver);

            for(int i = 0; i < 4; i++){
                Coin nextOne = new Coin(one.x, one.y, one.isOver);
                Coin nextTwo = new Coin(two.x, two.y, two.isOver);

                int nextOneX = one.x + dx[i];
                int nextOneY = one.y + dy[i];

                int nextTwoX = two.x + dx[i];
                int nextTwoY = two.y + dy[i];

                if(!one.isOver){
                    // 범위가 벗어났을 경우
                    if(nextOneX < 0 || nextOneX >= N || nextOneY < 0 || nextOneY >= M){
                        nextOne.isOver = true;
                    }else{// 벽인경우와 아닌 경우
                        if(board[nextOneX][nextOneY] != '#'){
                            nextOne.x = nextOneX;
                            nextOne.y = nextOneY;
                        }
                    }
                }

                if(!two.isOver){
                    if(nextTwoX < 0 || nextTwoX >= N || nextTwoY < 0 || nextTwoY >= M){
                        nextTwo.isOver = true;
                    }else{
                        if(board[nextTwoX][nextTwoY] != '#'){
                            nextTwo.x = nextTwoX;
                            nextTwo.y = nextTwoY;
                        }
                    }
                }
                if((!one.isOver && two.isOver) || (one.isOver && !two.isOver)){
                    return now.count;
                }
                pq.add(new Wallet(nextOne, nextTwo, now.count+1));
            }
        }
        return -1;
    }
}
class Wallet{
    Coin c1;
    Coin c2;
    int count;
    Wallet(Coin c1, Coin c2, int count){
        this.c1 = c1;
        this.c2 = c2;
        this.count = count;
    }
}
class Coin{
    int x;
    int y;
    boolean isOver;
    Coin(int x, int y, boolean isOver){
        this.x = x;
        this.y = y;
        this.isOver = isOver;
    }
}
