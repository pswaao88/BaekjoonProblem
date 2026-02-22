import java.io.*;
import java.util.*;

class Solution {
    int[] answer = new int[2];
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    boolean[] visit = new boolean[1000001];
    int[] degree = new int[1000001];
    ArrayList<Integer> tree;
    public int[] solution(int[] nodes, int[][] edges) {
        for(int i = 0; i <= 1000000; i++){
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++){
            int a = edges[i][0];
            int b = edges[i][1];
            graph.get(a).add(b);
            graph.get(b).add(a);
            degree[a]++;
            degree[b]++;
        }
        // root는 degree의 수가 child 수
        // root가 아니면 degree - 1이 child의 수
        // 홀짝 트리의 조건 rootNode가 짝수면 degree도 짝수 / rootNode가 홀수면 degree도 홀수
        // 나머지 노드들은 node가 짝수면 degree - 1짝수, node가 홀수면 degree - 1이 홀수 이므로 반대 상황인 역홀짝
        // 홀짝트리이기 위해서는 홀짝 노드가 1개 여야만함
        // 역홀짝 트리는 역홀짝이 1개여야함
        // 0개면 root가 없어 모순
        // 2개 이상이면 홀짝 트리가 안됨
        for(int i = 0; i < nodes.length; i++){
            int now = nodes[i];
            if(visit[now]) continue;
            tree = new ArrayList<>();
            bfs(now);
            // 홀짝 노드 수
            int node = 0;
            // 역홀짝 노드 수
            int reverseNode = 0;
            
            for(int j = 0; j < tree.size(); j++){
                int nodeNumber = tree.get(j);
                if((nodeNumber % 2 == 0 && degree[nodeNumber] % 2 == 0) || (nodeNumber % 2 != 0 && degree[nodeNumber] % 2 != 0)){
                    node++; 
                }else{
                    reverseNode++;
                }
            }
            if(node == 1){
                answer[0]++;
            }
            if(reverseNode == 1){
                answer[1]++;
            }
        }
        return answer;
    }
    void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        tree.add(start);
        visit[start] = true;
        while(!q.isEmpty()){
            int now = q.remove();
            for(int i = 0; i < graph.get(now).size(); i++){
                int next = graph.get(now).get(i);
                if(!visit[next]){
                    tree.add(next);
                    visit[next] = true;
                    q.add(next);
                }
            }
        }
    }
    
}