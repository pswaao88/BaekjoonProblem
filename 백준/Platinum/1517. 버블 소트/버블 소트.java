import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;
    static long swapCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        tree = new int[findMax(N)];
        int[] arr = new int[N];
        int[][] sortedArr = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sortedArr[i][0] = arr[i];
            sortedArr[i][1] = i;
        }
        Arrays.sort(sortedArr, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });

        int next = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++){
            int[] now = sortedArr[i];
            swapCnt += find(0, N - 1, now[1] + 1, N - 1, 1);
            if(i == N - 1) continue; // 마지막은 업데이트 필요 x
            if(now[0] < next){
                update(0, N - 1, 1, now[1]);
            }
        }
        bw.write(Long.toString(swapCnt));
        bw.flush();
    }
    // 리프노드의 개수는 N개 이므로 풀 바이너리 트리로 봤을 때
    // N보다 큰 2의 거듭제곱중 가장 작은 (ex: N이 3이라면 4, N이 11이라면 16) 수를 2^i라고 했을 떄 A가 리프노드의 개수가 된다.
    // 따라서 노드의 개수를 모두 합치면 2^i + 2^i-1 + ... + 1 = 2^i+1 -1 이고 index를 1부터 시작한다면 2^i로 표시 가능
    static int findMax(int x){
        int i = 1;
        while(x > i){
            i *= 2;
        }
        return i * 2;
    }
    // int treeStart: 트리의 시작 int treeEnd: 트리의 마지막 N이라고하면 1~ N/2, N/2+1 ~ N
    // int nowIndex: 현재의 tree index
    // int goalIndex : 목표 index
    static void update(int treeStart, int treeEnd, int nowIndex, int goalIndex) {

        if(treeStart > goalIndex || treeEnd < goalIndex){
            return;
        }
        tree[nowIndex]++;
        if(treeStart == treeEnd){
            return;
        }
        int mid = (treeStart + treeEnd) / 2;
        int nextIndex = nowIndex * 2;
        // 왼쪽
        update(treeStart, mid, nextIndex, goalIndex);
        // 오른쪽
        update(mid + 1, treeEnd, nextIndex + 1, goalIndex);

    }
    // treeStart: 트리 시작 index , treeEnd: 트리 끝 index
    // start: 탐색 시작 index , end: 탐색 끝 index
    static int find(int treeStart, int treeEnd, int start, int end, int index) {
        if(treeEnd < start || treeStart > end || start > end){ // 범위를 벗어날 경우
            return 0;
        }
        if(start <= treeStart && treeEnd <= end){ // 탐색 범위안에 트리의 범위가 있는 경우
            return tree[index];
        }
        int mid = (treeStart + treeEnd) / 2;
        int nextIndex = index * 2;
        return find(treeStart, mid, start, end, nextIndex) + find(mid + 1, treeEnd, start, end, nextIndex + 1);
    }
}
