import java.io.*;
import java.util.*;

public class Main {
    static long[] tree;
    static int[] number;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        number = new int[N+1];
        tree = new long[N+1];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 0){
                if(b <= c){
                    sb.append(findSum(c) - findSum(b-1));
                }else{
                    sb.append(findSum(b) - findSum(c-1));
                }
                sb.append("\n");
            }else{
                update(b, c);
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void update(int index, int value){
        int now = index;
        while(now <= N){
            int cnt = now & -now;
            tree[now] -= number[index];
            tree[now] += value;
            now += cnt;
        }
        number[index] = value;

    }
    static long findSum(int index){
        int now = index;
        long result = 0;
        while(now >= 1){
            int cnt = now & -now;
            result += tree[now];
            now -= cnt;
        }
        return result;
    }
}
