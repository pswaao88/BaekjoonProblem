import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int K = Integer.parseInt(br.readLine());
        BA[] DP = new BA[K+1];
        //시작
        DP[0] = new BA(1, 0);
        for(int i = 1; i <= K; i++){
            BA before = DP[i-1];
            int nowA = 0;
            int nowB = 0;

            nowB += before.A;
            
            nowA += before.B;
            nowB += before.B;
            DP[i] = new BA(nowA, nowB);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(DP[K].A).append(" ").append(DP[K].B);
        bw.write(sb.toString());
        bw.flush();
    }
}
class BA{
    int A;
    int B;
    BA(int A, int B){
        this.A = A;
        this.B = B;
    }
}
