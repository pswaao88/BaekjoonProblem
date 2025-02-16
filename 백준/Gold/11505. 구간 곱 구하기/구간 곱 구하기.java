import java.io.*;
import java.util.*;

public class Main {
    static long[] number, mux;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        mux = new long[findTreeSize(N)];
        Arrays.fill(mux, 1);
        for(int i = 0; i < N; i++){
            update(findIndex(0, N-1, i,1), Integer.parseInt(br.readLine()));
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 1){
                update(findIndex(0, N-1, b-1, 1), c);
            }else{
                sb.append(findMux(0, N-1, b-1, c-1, 1));
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static int findTreeSize(int size){
        int i = 1;
        while (i < size){
            i *= 2;
        }
        return i * 2;
    }
    static int findIndex(int treeStart, int treeEnd, int goal, int nowIndex){
        if(goal < treeStart || treeEnd < goal){
            return -1;
        }
        if(treeStart == treeEnd){
            return nowIndex;
        }
        int mid = (treeStart + treeEnd) / 2;
        return Math.max(findIndex(treeStart, mid, goal, nowIndex * 2), findIndex(mid + 1, treeEnd, goal, nowIndex * 2 + 1));
    }

    static boolean checkEven(int index){
        return index % 2 == 0;
    }
    static int findOtherChildIndex(int index){
        if(checkEven(index)){
            return (index / 2) * 2 + 1;
        }
        return (index / 2) * 2;
    }
    static void update(int startIndex, int value){
        int nowIndex = startIndex;
        mux[nowIndex] = value;
        while(nowIndex > 1){
            int parentIndex = nowIndex / 2;
            int otherChildIndex = findOtherChildIndex(nowIndex);
            mux[parentIndex] = (mux[nowIndex] * mux[otherChildIndex]) % 1000000007;
            nowIndex /= 2;
        }
    }
    static long findMux(int treeStart, int treeEnd, int b, int c, int nowIndex){
        if(c < treeStart || treeEnd < b){
            return 1;
        }
        if(b <= treeStart && treeEnd <= c){
            return mux[nowIndex];
        }
        int mid = (treeStart + treeEnd) / 2;
        return (findMux(treeStart, mid, b, c, nowIndex * 2) * findMux(mid + 1, treeEnd, b, c, nowIndex * 2 + 1)) % 1000000007;

    }
}
