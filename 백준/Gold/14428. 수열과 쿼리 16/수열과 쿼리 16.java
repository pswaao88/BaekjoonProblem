import java.io.*;
import java.util.*;

public class Main {
    static int[] number;
    static int[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        number = new int[N];
        tree = new int[findTreeSize(N)];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
            update(i, number[i], findIndex(0, N-1, i, 1));
        }
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            if(a == 1){
                update(i-1, j, findIndex(0, N-1, i-1, 1));
            }else{
                sb.append(findMinIndex(0, N-1, i-1, j-1, 1)+1);
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
        return now * 2;
    }
    static int findIndex(int treeStart, int treeEnd, int goal, int nowIndex){
        if(goal < treeStart || treeEnd < goal){
            return -1;
        }
        if(treeStart == treeEnd){
            return nowIndex;
        }
        int mid = (treeStart + treeEnd) / 2;
        return Math.max(findIndex(treeStart, mid, goal, nowIndex * 2),findIndex(mid + 1, treeEnd, goal, nowIndex * 2 + 1)) ;
    }
    static int findOtherChild(int index){
        int parent = index / 2;
        if(index % 2 == 0){ // 짝수일 때 왼쪽 child
            return parent * 2 + 1;
        }
        return parent * 2;
    }

    static void update(int changeIndex, int value, int startIndex){
        number[changeIndex] = value;
        tree[startIndex] = changeIndex;
        int nowIndex = startIndex;
        while(nowIndex > 1){
            int otherChildIndex = findOtherChild(nowIndex);
            int parentIndex = nowIndex / 2;
            if(number[tree[nowIndex]] > number[tree[otherChildIndex]]){
                tree[parentIndex] = tree[otherChildIndex];
            }else if(number[tree[nowIndex]] < number[tree[otherChildIndex]]){
                tree[parentIndex] = tree[nowIndex];
            }else{
                if(tree[nowIndex] > tree[otherChildIndex]){
                    tree[parentIndex] = tree[otherChildIndex];
                }else{
                    tree[parentIndex] = tree[nowIndex];
                }
            }
            nowIndex /= 2;
        }
    }
    static int findMinIndex(int treeStart, int treeEnd, int i, int j, int nowIndex){
        if(j < treeStart || treeEnd < i){
            return 987654321;
        }
        if(i <= treeStart && treeEnd <= j){
            return tree[nowIndex];
        }
        int mid = (treeStart + treeEnd) / 2;
        int left = findMinIndex(treeStart, mid, i, j, nowIndex * 2);
        int right = findMinIndex(mid + 1, treeEnd, i, j, nowIndex * 2 + 1);
        if(left == 987654321){
            return right;
        }
        if(right == 987654321){
            return left;
        }

        if(number[left] > number[right]){
            return right;
        }
        return left;
    }


}
