import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int min = 0;
        for(int i = 0; i < L; i++){
            min += i;
        }
        StringBuilder sb = new StringBuilder();
        while(L <= 101){
            // 커지면 만들수가 없으므로 끝
            if(L == 101 || min > N){
                sb.append("-1");
                break;
            }
            // 가능함
            if((N - min) % L == 0) {
                int plus = (N - min) / L;
                for(int i = 0; i < L; i++){
                    sb.append(i + plus).append(" ");
                }
                break;
            }

            L++;
            min += L - 1;
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
