import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Coordinate[] coordinates = new Coordinate[n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            double a = Double.parseDouble(st.nextToken());
            double b = Double.parseDouble(st.nextToken());
            coordinates[i] = new Coordinate(i, a, b);
        }
        PriorityQueue<Star> pq = new PriorityQueue<>((s1, s2) -> Double.compare(s1.diff, s2.diff));
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                double diff = calDiff(coordinates[i], coordinates[j]);
                pq.add(new Star(i, j, diff));
            }
        }
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = i;
        }
        double result = 0;
        int size = pq.size();
        for(int i = 0; i < size; i++){
            Star now = pq.remove();
            if(check(now.left, now.right)) continue;
            result += now.diff;
            union(now.left, now.right);
        }
        result = Math.round(result * 100) / 100.0;
        bw.write(Double.toString(result));
        bw.flush();

    }
    static double calDiff(Coordinate left, Coordinate right){
        return Math.sqrt(Math.pow(left.x - right.x, 2) + Math.pow(left.y - right.y, 2));
    }
    static boolean check(int left, int right){
        return getParent(left) == getParent(right);
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

    static int getParent(int now){
        if(parent[now] == now) return parent[now];
        return parent[now] = getParent(parent[now]);
    }

}
class Star{
    int left;
    int right;
    double diff;
    Star(int left, int right, double diff){
        this.left = left;
        this.right = right;
        this.diff = diff;
    }
}
class Coordinate{
    int index;
    double x;
    double y;
    Coordinate(int index, double x, double y){
        this.index = index;
        this.x = x;
        this.y = y;
    }
}
