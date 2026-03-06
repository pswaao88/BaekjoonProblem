import java.util.*;

class Solution {
    boolean[] visit;
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                if(computers[i][j] == 1){
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }
        
        visit = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visit[i]){
                dfs(i);
                answer++;
            }
        }
        return answer;
    }
    void dfs(int start){
        visit[start] = true;
        for(int i = 0; i < graph.get(start).size(); i++){
            int next = graph.get(start).get(i);
            if(!visit[next]){
                dfs(next);
            }
        }
    }
}