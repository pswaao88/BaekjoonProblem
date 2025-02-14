import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] runner = new int[N];
        int[][] sortedRunner = new int[N][2];
        tree = new int[findTreeSize(N)];

        for(int i = 0; i < N; i++){
            runner[i] = Integer.parseInt(br.readLine());
            sortedRunner[i][0] = runner[i];
            sortedRunner[i][1] = i;
        }

        Arrays.sort(sortedRunner, new Comparator<int[]>(){
           @Override
           public int compare(int[] o1, int[] o2){
               return o1[0] - o2[0];
           }
        });

        for(int i = 0; i < N; i++){
            int[] nowRunner = sortedRunner[i];
            int runnerIndex = nowRunner[1];
            runner[runnerIndex] = runnerIndex - find(0, N - 1, 0, runnerIndex - 1, 1) + 1;
            update(0, N-1, runnerIndex, 1);
        }
        for(int i : runner){
            bw.write(Integer.toString(i));
            bw.newLine();
        }
        bw.flush();
    }
    static int findTreeSize(int size){
        int i = 1;
        while(size > i){
            i *= 2;
        }
        return i * 2;
    }
    static void update(int treeStart, int treeEnd, int goal, int nowIndex){
        if(goal < treeStart || treeEnd < goal){
            return;
        }
        tree[nowIndex]++;
        if(treeStart == treeEnd){
            return;
        }
        int mid = (treeStart + treeEnd) / 2;
        update(treeStart, mid, goal, nowIndex * 2);
        update(mid + 1, treeEnd, goal, nowIndex * 2 + 1);
    }
    static int find(int treeStart, int treeEnd, int start, int end, int nowIndex){
        if( treeEnd < start || end < treeStart){
            return 0;
        }
        if(start <= treeStart && treeEnd <= end){
            return tree[nowIndex];
        }
        int mid = (treeStart + treeEnd) / 2;
        return find(treeStart, mid, start, end, nowIndex * 2) + find(mid + 1, treeEnd, start, end, nowIndex * 2 + 1);
    }
}
