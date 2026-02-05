import java.io.*;
import java.util.*;

public class Main {
    static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    static int[] number;
    static int[] op;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        number = new int[N];
        op = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            int count = Integer.parseInt(st.nextToken());
            op[i] = count;
        }
        find(0,0);
        bw.write(Integer.toString(max));
        bw.newLine();
        bw.write(Integer.toString(min));
        bw.flush();
    }
    static void find(int value, int depth){
        if(depth == N){
            min = Math.min(min, value);
            max = Math.max(max, value);
            return;
        }

        if(depth == 0){
            find(number[0], 1);
        }else{
            for(int i = 0; i < 4; i++){
                if(op[i] > 0){
                    if(i == 0){
                        op[i]--;
                        find(value + number[depth], depth + 1);
                        op[i]++;
                    }else if(i == 1){
                        op[i]--;
                        find(value - number[depth], depth + 1);
                        op[i]++;
                    }else if(i == 2){
                        op[i]--;
                        find(value * number[depth], depth + 1);
                        op[i]++;
                    }else{
                        op[i]--;
                        find(value / number[depth], depth + 1);
                        op[i]++;
                    }
                }
            }
        }

    }
}
