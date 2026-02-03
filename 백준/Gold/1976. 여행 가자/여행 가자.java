import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
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

        graph = new int[N+1][N+1];
        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                int now = Integer.parseInt(st.nextToken());
                if(i >= j) continue;
                if(now == 1) union(i, j);
            }
        }
        int[] travel = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            travel[i] = Integer.parseInt(st.nextToken());
        }
        boolean yes = true;
        for(int i = 0; i < M - 1; i++){
            int left = travel[i];
            int right = travel[i+1];
            if(!sameUnion(left, right)){
                yes = false;
                break;
            }
        }
        if(yes) bw.write("YES");
        else bw.write("NO");
        bw.flush();

    }
    static boolean sameUnion(int left, int right){
        return getParent(left) == getParent(right);
    }
    static int getParent(int now){
        if(now == parent[now] ) return now;
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
