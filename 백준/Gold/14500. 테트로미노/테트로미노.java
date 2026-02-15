import java.io.*;
import java.util.*;

public class Main {
    static int[][] paper;
    static int N, M, max = 0;
    // 오 아 왼 위
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N  = Integer.parseInt(st.nextToken());
        M  = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visited[i][j] = true;
                find(i, j, paper[i][j], 1);
                visited[i][j] = false;
                findWASD(i, j);
            }
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
    static void find(int x, int y, int value, int depth){
        // 테트로미노일때  계산
        if(depth >= 4){
            max = Math.max(max, value);
            return;
        }
        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visited[nextX][nextY]) continue;
            visited[nextX][nextY] = true;
            find(nextX, nextY, value + paper[nextX][nextY], depth + 1);
            visited[nextX][nextY] = false;
        }
    }
    static boolean isOk(int x, int y){
        if(x < 0 || x >= N || y < 0 || y >= M){
            return false;
        }
        return true;
    }
    static void findWASD(int x, int y){
        int topX = x + dx[3];
        int topY = y + dy[3];;
        int rightX = x + dx[0];
        int rightY = y + dy[0];
        int leftX = x + dx[2];
        int leftY = y + dy[2];;
        int downX = x + dx[1];;
        int downY = y + dy[1];;
        // ㅗ
        if(isOk(topX, topY) && isOk(leftX, leftY) && isOk(rightX, rightY)){
            max = Math.max(max, paper[topX][topY] + paper[leftX][leftY] + paper[rightX][rightY] + paper[x][y]);
        }
        // ㅏ
        if(isOk(topX, topY) && isOk(rightX, rightY) && isOk(downX, downY)){
            max = Math.max(max, paper[topX][topY] + paper[rightX][rightY] + paper[downX][downY] + paper[x][y]);
        }
        // ㅜ
        if(isOk(rightX, rightY) && isOk(downX, downY) && isOk(leftX, leftY)){
            max = Math.max(max, paper[rightX][rightY] + paper[downX][downY] + paper[leftX][leftY] + paper[x][y]);
        }
        // ㅓ
        if(isOk(downX, downY) && isOk(leftX, leftY) && isOk(topX, topY)){
            max = Math.max(max, paper[downX][downY] + paper[leftX][leftY] + paper[topX][topY] + paper[x][y]);
        }

    }

}

