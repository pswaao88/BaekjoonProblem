import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(op == 0){
                union(a, b);
            }else{
                if(sameUnion(a, b)){
                    sb.append("YES\n");
                }else{
                    sb.append("NO\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();

    }
    static boolean sameUnion(int left, int right){
        return getParent(left) == getParent(right);
    }
    static int getParent(int now){
        if(now == parent[now]) return now;
        return parent[now] = getParent(parent[now]);
    }
    static void union(int left, int right){
        int leftRoot = getParent(left);
        int rightRoot = getParent(right);

        if(leftRoot < rightRoot){
            parent[rightRoot] = leftRoot;
            return;
        }
        parent[leftRoot] = rightRoot;
    }
}
