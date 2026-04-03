import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static char[][] board;
    static int[][][] result;
    static String target;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                board[i][j] = s.charAt(j);
            }
        }
        target = br.readLine();

        result = new int[N][M][target.length()];
        bw.write(Integer.toString(find()));
        bw.flush();
    }
    static int find(){
        int count = 0;

        // 초기화
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                if(board[row][col] == target.charAt(0)){
                    result[row][col][0]++;
                }
            }
        }

        // n번째 글자
        for(int i = 1; i < target.length(); i++){
            char now = target.charAt(i);
            char before = target.charAt(i-1);
            for(int row = 0; row < N; row++){
                for(int col = 0; col < M; col++){
                    // 같으면 상하좌우 k칸 검사해서 더하기
                    if(board[row][col] == now){
                        for(int j = 0; j < 4; j++){
                            for(int k = 1; k <= K; k++){
                                int nextR = row + (dx[j] * k);
                                int nextC = col + (dy[j] * k);
                                if(nextR < 0 || nextR >= N || nextC < 0 || nextC >= M || board[nextR][nextC] != before) continue;

                                result[row][col][i] += result[nextR][nextC][i-1];

                            }
                        }
                    }
                }
            }
        }

        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                count += result[row][col][target.length() - 1];
            }
        }
        return count;
    }
}
class Coordinate{
    int x;
    int y;
    int count;
    Coordinate(int x, int y, int count){
        this.x = x;
        this.y = y;
        this.count = count;
    }
}
