import java.io.*;
import java.util.*;

public class Main {
    static int[] minTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        minTree = new int[makeTreeSize(N)];
        Arrays.fill(minTree, Integer.MAX_VALUE);
        // 초기화
        for(int i = 0; i < N; i++){
            int startIndex = 1;
            int value = Integer.parseInt(br.readLine());
            update(0, N-1, i, value, 1);
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            bw.write(Integer.toString(find(0, N-1, a, b, 1)));
            bw.newLine();
        }
        bw.flush();

    }
    static int find(int treeStart, int treeEnd, int a, int b, int nowIndex){
        if(b < treeStart || treeEnd < a){
            return Integer.MAX_VALUE;
        }
        if(a <= treeStart && treeEnd <= b){
            return minTree[nowIndex];
        }
        int mid = (treeStart + treeEnd) / 2;
        return Math.min(find(treeStart, mid, a, b, nowIndex * 2), find(mid + 1, treeEnd, a, b, nowIndex * 2 + 1));
    }
    static int makeTreeSize(int size){
        int i = 1;
        while(size > i){
            i *= 2;
        }
        return i * 2;
    }
    // int treeStart, int treeEnd:  트리 탐색 범위
    // int start: int end: 내가 찾을 탐색 범위
    // int index: tree에서의 index
    static void update(int treeStart, int treeEnd, int goal, int value , int nowIndex){
        if(treeStart > goal || goal > treeEnd){
            return;
        }
        minTree[nowIndex] = Math.min(minTree[nowIndex], value);
        if(treeStart == treeEnd){
            return;
        }
        int mid = (treeStart + treeEnd) / 2;
        update(treeStart, mid, goal, value, nowIndex * 2);
        update(mid + 1, treeEnd, goal, value, nowIndex * 2 + 1);
    }
}
