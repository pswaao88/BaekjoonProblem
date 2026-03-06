import java.util.*;

class Solution {
    boolean[] visit;
    int[] distance;
    final int MAX = 100000;
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    public int solution(int n, int[][] edge) {
        int answer = 0;
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < edge.length; i++){
            int[] now = edge[i];
            graph.get(now[0]).add(now[1]);
            graph.get(now[1]).add(now[0]);
        }
        visit = new boolean[n+1];
        distance = new int[n+1];
        find();
        int maxDistance = 0;
        for(int i = 1; i <= n; i++){
            maxDistance = Math.max(maxDistance, distance[i]);
        }
        for(int i = 1; i <= n; i++){
            if(maxDistance == distance[i]){
                answer++;
            }
        }
        return answer;
    }
    void find(){
        Arrays.fill(distance, MAX);
        distance[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.value - n2.value;
        });
        pq.add(new Node(1, 0));
        while(!pq.isEmpty()){
            Node now = pq.remove();
            if(visit[now.now]) continue;
            visit[now.now] = true;
            for(int i = 0; i < graph.get(now.now).size(); i++){
                int next = graph.get(now.now).get(i);
                if(visit[next]) continue;
                if(distance[next] > now.value + 1){
                    distance[next] = now.value + 1;
                    pq.add(new Node(next, distance[next]));
                }
            }
        }
    }
}
class Node{
    int now;
    int value;
    Node(int now, int value){
        this.now = now;
        this.value = value;
    }
}