import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.weight, n2.weight));
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }
        parent = new int[V+1];
        for(int i = 0; i <= V; i++){
            parent[i] = i;
        }

        int result = 0;
        for(int i = 0; i < E; i++){
            Edge now = pq.remove();
            int left = now.leftNode;
            int right = now.rightNode;

            if(sameUnion(left, right)) continue;
            union(left, right);
            result += now.weight;
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static boolean sameUnion(int left, int right){
        return getParent(left) == getParent(right);
    }
    static int getParent(int now){
        if(parent[now] == now) return now;
        return parent[now] = getParent(parent[now]);
    }
    static void union(int left, int right){
        int leftRoot = getParent(left);
        int rightRoot = getParent(right);

        if(leftRoot == rightRoot) return;
        if(leftRoot <= rightRoot){
            parent[rightRoot] = leftRoot;
            return;
        }
        parent[leftRoot] = rightRoot;
    }
}
class Edge{
    int leftNode;
    int rightNode;
    int weight;
    Edge(int leftNode, int rightNode, int weight){
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.weight = weight;
    }
}
