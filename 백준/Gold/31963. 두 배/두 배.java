import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        // 2의 지수 곱을 저장
        int[] k = new int[N];

        for(int i = 0; i < N-1; i++){
            int left = number[i];
            int right = number[i+1];
            k[i+1] = diff(left, right) + k[i];
            k[i+1] = Math.max(0, k[i+1]);
            result += k[i+1];
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static int diff(int a, int b){
        int cnt = 0;
        if(a > b) {
            while (b < a) {
                b *= 2; cnt++;
            }
        }else {
            while (a * 2 <= b) {
                a *= 2; cnt--;
            }
        }
        return cnt;
    }
}
