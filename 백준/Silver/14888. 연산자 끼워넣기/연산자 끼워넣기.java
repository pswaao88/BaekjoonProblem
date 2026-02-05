import java.io.*;
import java.util.*;

public class Main {
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] number;
    static char[] op;
    static int N;
    static boolean[] visit;
    static char[] nowOper;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        number = new int[N];
        op = new char[N-1];

        nowOper = new char[N-1];
        visit = new boolean[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int index = 0;
        for(int i = 0; i < 4; i++){
            int count = Integer.parseInt(st.nextToken());
            for(int j = 0; j < count; j++){
                switch(i){
                    case 0:
                        op[index] = '+';
                        index++;
                        break;
                    case 1:
                        op[index] = '-';
                        index++;
                        break;
                    case 2:
                        op[index] = '*';
                        index++;
                        break;
                    case 3:
                        op[index] = '/';
                        index++;
                        break;
                }

            }
        }
        find(0);
        bw.write(Integer.toString(max));
        bw.newLine();
        bw.write(Integer.toString(min));
        bw.flush();
    }
    static void find(int depth){
        if(depth == N-1){
            //계산 nowOper에 연산자 순서대로 저장
            int sum = number[0];
            for(int i = 1; i < N; i++){
                switch(nowOper[i-1]){
                    case '+':
                        sum += number[i];
                        break;
                    case '-':
                        sum -= number[i];
                        break;
                    case '*':
                        sum *= number[i];
                        break;
                    case '/':
                        sum /= number[i];
                        break;
                }
            }
            min = Math.min(min, sum);
            max = Math.max(max, sum);

        }

        for(int i = 0; i < N-1; i++){
            if(visit[i]) continue;
            visit[i] = true;
            nowOper[depth] = op[i];
            find(depth + 1);
            visit[i] = false;
        }

    }
}
