import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];
        for(int i = 0; i <= N; i++){
            parent[i] = i;
        }
        PriorityQueue<Line> pq = new PriorityQueue<>((l1, l2) -> l1.cost - l2.cost);
        StringTokenizer st;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Line(a, b, c));
        }
        int result = 0;
        for(int i = 0; i < M; i++){
            Line now = pq.remove();
            if(sameUnion(now.left, now.right)) continue;
            result += now.cost;
            union(now.left, now.right);
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static boolean sameUnion(int left, int right){
        return getParent(left) == getParent(right);
    }

    static int getParent(int now){
        if(parent[now] == now) return parent[now];
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
class Line{
    int left;
    int right;
    int cost;
    Line(int left, int right, int cost){
        this.left = left;
        this.right = right;
        this.cost = cost;
    }
}
