import java.io.*;
import java.util.*;

public class Main {
    static long[] sum, number;
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        // 입력받은 처음 수 초기화
        number = new long[N+1];
        for(int i = 1; i <= N; i++){
            number[i] = Long.parseLong(br.readLine());
        }
        // 이진 바이너리 인덱스 트리 초기화
        sum = new long[N+1];
        for(int i = 1; i <= N; i++){
            int index = i;
            while(index <= N){
                int cnt = index & -index; // 값을 저장하고 있는 개수
                sum[index] += number[i];
                index += cnt;
            }
        }
        // 계산 진행
        for(int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            changeOrFindSum(a, b, c);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void changeOrFindSum(int a, int b, long c){
        if(a == 1){
            change(b, c);
            return;
        }
        sb.append(findSumBtoC(b, (int)c));
        sb.append("\n");
    }
    static void change(int b, long c){ // b번째를 c로 변경
        int index = b; // 변경될 index

        while(index <= N){ //
            int cnt = index & -index; // 값을 저장하고 있는 개수
            sum[index] -= number[b]; // 기존 값 빼주기
            sum[index] += c; // 바뀔 값 더하기
            index += cnt;
        }
        number[b] = c;
    }

    static long findSumBtoC(int b, int c){
        return findSum(c) - findSum(b-1);
    }

    static long findSum(int x){
        long result = 0L;
        int index = x;
        while(index > 0){
            int cnt = index & -index; // 값을 가지고 있는 개수
            result += sum[index];
            index -= cnt; // 결과를 다 더해줬다면 index를 왼쪽으로 이동 즉 개수만큼 빼줌
        }
        return result;
    }

}
