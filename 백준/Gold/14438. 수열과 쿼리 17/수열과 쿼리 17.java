import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        tree = new int[findTreeSize(N)];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            update(Integer.parseInt(st.nextToken()), findIndex(0, N-1, i, 1));
        }
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            if(a == 1){
                update(j, findIndex(0, N-1, i-1, 1));
            }else{
                sb.append(findMin(0, N-1, i-1, j-1, 1));
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
    static int findTreeSize(int i){
        int now = 1;
        while(now < i){
            now *= 2;
        }
        return 2 * now;
    }
    static int findIndex(int treeStart, int treeEnd, int goal, int nowIndex){
        if(goal < treeStart || treeEnd < goal){
            return Integer.MAX_VALUE;
        }
        if(treeStart == treeEnd){
            return nowIndex;
        }
        int mid = (treeStart + treeEnd) / 2;
        return Math.min(findIndex(treeStart, mid, goal, nowIndex * 2), findIndex(mid + 1, treeEnd, goal, nowIndex * 2 + 1));
    }
    static int findOtherChild(int index){
        int parent = index / 2;
        if(index % 2 == 0){
            return parent * 2 + 1;
        }
        return parent * 2;
    }
    static void update(int v, int index){
        int now = index;
        tree[now] = v;
        while(now > 1){
            int parent = now / 2;
            int otherChild = findOtherChild(now);
            tree[parent] = Math.min(tree[now], tree[otherChild]);
            now /= 2;
        }
    }
    static int findMin(int treeStart, int treeEnd, int i, int j, int nowIndex){
        if(j < treeStart || treeEnd < i){
            return Integer.MAX_VALUE;
        }
        if(i <= treeStart && treeEnd <= j){
            return tree[nowIndex];
        }
        int mid = (treeStart + treeEnd) / 2;
        return Math.min(findMin(treeStart, mid, i, j, nowIndex * 2), findMin(mid + 1, treeEnd, i, j, nowIndex * 2 + 1));
    }
}
