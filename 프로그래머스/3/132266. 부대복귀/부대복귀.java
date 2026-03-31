import java.io.*;
import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    int[] result;
    boolean[] visit;
    final int MAX = 987654321;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        //dest에서 다익스트라 해서 배열에 추가하기
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        result = new int[n+1];
        Arrays.fill(result, MAX);
        visit = new boolean[n+1];
        for(int i = 0; i < roads.length; i++){
            int[] now = roads[i];
            graph.get(now[0]).add(now[1]);
            graph.get(now[1]).add(now[0]);
        }
        find(destination);
        for(int i = 0; i < sources.length; i++){
            answer[i] = result[sources[i]];
            if(answer[i] == MAX) answer[i] = -1;
        }
        return answer;
    }
    void find(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.value - n2.value;
        });
        pq.add(new Node(start, 0));
        result[start] = 0;
        while(!pq.isEmpty()){
            Node now = pq.remove();
            // 방문했으면 필요 x
            if(visit[now.node]) continue;
            visit[now.node] = true;
            for(int i = 0; i < graph.get(now.node).size(); i++){
                int next = graph.get(now.node).get(i);
                if(visit[next]) continue;
                if(result[next] > result[now.node] + 1){
                    result[next] = result[now.node] + 1;
                    pq.add(new Node(next, result[next]));
                }
            }
        }
    }
}
class Node{
    int node;
    int value;
    Node(int node, int value){
        this.node = node;
        this.value = value;
    }
}