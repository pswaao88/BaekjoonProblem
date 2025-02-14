import java.io.*;
import java.util.*;

public class Main {
    static int[] minTree, maxTree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int treeSize = findTreeSize(N);

        minTree = new int[treeSize];
        maxTree = new int[treeSize];
        Arrays.fill(minTree, Integer.MAX_VALUE);

        for(int i = 0; i < N; i++){
            int value = Integer.parseInt(br.readLine());
            updateMin(0, N-1, i, value, 1);
            updateMax(0, N-1, i, value, 1);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            // 입력받은 순서를 0부터 시작으로 계산했기 때문에 -1을 해줌
            // 왜 0부터 시작?
            // 5개가 있다고 하면 나눌 때 5 / 2 = 2로 변함
            // => 1~2 3~5로 왼쪽에 2개 오른쪽 3 오른쪽노드에 더 많이 들어감 => 왼쪽으로 몰기
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1 ;
            bw.write(Integer.toString(findMin(0, N-1, a, b, 1)));
            bw.write(" ");
            bw.write(Integer.toString(findMax(0,N - 1, a, b, 1)));
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
    // int treeStart, int treeEnd: 현재 탐색할 트리의 범위
    // ex) 0~7까지 포함된 노드, 2~4까지 포함된 노드
    // int goal ,
    // int nowIndex: tree에서의 index(1부터 트리 size-1까지)
    // int value: 갱신할 value 값
    static void updateMin(int treeStart, int treeEnd, int goal, int value ,int nowIndex){
        if(goal < treeStart || goal > treeEnd){
            return;
        }
        minTree[nowIndex] = Math.min(minTree[nowIndex], value);
        if(treeStart == treeEnd){
            return;
        }
        int mid = (treeStart + treeEnd) / 2;
        updateMin(treeStart, mid, goal, value,nowIndex * 2);
        updateMin(mid + 1, treeEnd, goal, value, nowIndex * 2 + 1);
    }
    static void updateMax(int treeStart, int treeEnd, int goal, int value, int nowIndex){
        if(goal < treeStart || treeEnd < goal){
            return;
        }
        maxTree[nowIndex] = Math.max(maxTree[nowIndex], value);
        if(treeStart == treeEnd){ // 마지막에 도달
            return;
        }
        int mid = (treeStart + treeEnd) / 2;
        updateMax(treeStart, mid, goal, value, nowIndex * 2);
        updateMax(mid + 1, treeEnd, goal, value, nowIndex * 2 + 1);
    }
    static int findMin(int treeStart, int treeEnd, int a, int b, int nowIndex){
        if(treeEnd < a || b < treeStart){
            return Integer.MAX_VALUE;
        }
        if(a <= treeStart && treeEnd <= b){ // 범위 안에 있을 때
            return minTree[nowIndex];
        }
        int mid = (treeStart + treeEnd) / 2;
        return Math.min(findMin(treeStart, mid, a, b, nowIndex * 2), findMin(mid + 1, treeEnd, a, b, nowIndex * 2 + 1));
    }
    static int findMax(int treeStart, int treeEnd, int a, int b, int nowIndex){
        if(b < treeStart || treeEnd < a){
            return 0;
        }
        if(a <= treeStart && treeEnd <= b){
            return maxTree[nowIndex];
        }
        int mid = (treeStart + treeEnd) / 2;
        return Math.max(findMax(treeStart, mid, a, b, nowIndex * 2), findMax(mid + 1, treeEnd, a, b, nowIndex * 2 + 1));
    }
}
