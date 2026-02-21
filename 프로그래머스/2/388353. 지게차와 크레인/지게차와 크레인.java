import java.io.*;
import java.util.*;

class Solution {
    int[][] container;
    boolean[][] visited;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    int row, col;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        row = storage.length+2;
        col = storage[0].length()+2;
        // 초기화 
        container = new int[row][col];
        for(int i = 0; i < row; i++){
            Arrays.fill(container[i], -1);    
        }
        
        for(int i = 1; i <= storage.length; i++){
            String s = storage[i-1];
            for(int j = 1; j <= s.length(); j++){
                container[i][j] = s.charAt(j-1) - 'A';
            }
        }
        
        for(int i = 0; i < requests.length; i++){
            String now = requests[i];
            // dfs로 테두리 제거
            if(now.length() == 1){
                visited = new boolean[row][col];
                dfs(0, 0, now.charAt(0) - 'A');                
            }else{ // 모든 알파벳 제거
                int nowAlpha = now.charAt(0) - 'A';
                for(int x = 0; x < row; x++){
                    for(int y = 0; y < col; y++){
                        if(nowAlpha == container[x][y]){
                            container[x][y] = -1;
                        }
                    }
                }
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(container[i][j] >= 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    void dfs(int x, int y, int target){
        visited[x][y] = true;
        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX < 0 || nextX >= row || nextY < 0 || nextY >= col || visited[nextX][nextY]) continue;
            if(container[nextX][nextY] == target){
                container[nextX][nextY] = -1;
                visited[nextX][nextY] = true;
            }else if(container[nextX][nextY] == -1){
                dfs(nextX, nextY, target);
            }
        }
    }
}