import java.io.*;
import java.util.*;

class Solution {
    int[][] map;
    boolean[][] visit;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int N, M;
    int[] result;
    public int solution(int[][] land) {
        int answer = 0;
        N = land.length;
        M = land[0].length;
        map = land;
        result = new int[M];
        visit = new boolean[N][M];
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visit[i][j] && map[i][j] == 1){
                    bfs(i, j);
                }
            }
        }
        for(int i = 0; i < M; i++){
            answer = Math.max(answer, result[i]);
        }
        return answer;
    }
    void bfs(int x, int y){
        int total = 1;
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(x, y));
        visit[x][y] = true;
        
        boolean[] column = new boolean[M];
        column[y] = true;
        while(!q.isEmpty()){
            Coordinate now = q.remove();
            
            for(int i = 0; i < 4; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                if(nextX >= N || nextX < 0 || nextY < 0 || nextY >= M || visit[nextX][nextY] || map[nextX][nextY] != 1) continue;
                q.add(new Coordinate(nextX, nextY));
                visit[nextX][nextY] = true;
                column[nextY] = true;
                total++;
            }
        }
        for(int i = 0; i < M; i++){
            if(column[i]){
                result[i] += total;        
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