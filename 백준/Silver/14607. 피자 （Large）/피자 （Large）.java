import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        long[] DP = new long[10000001];
        for(int i = 2; i <= 10000000; i++){
            int left = i / 2;
            int right = i % 2 == 0 ? i / 2 : i / 2 + 1;
            DP[i] = ((long) left * (long) right) + DP[left] + DP[right];
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        long result = 0;
        while(!q.isEmpty()){
            int now = q.remove();
            if(now <= 10000000){
                result += DP[now];
            }else{
                int left = now / 2;
                int right = now % 2 == 0 ? now / 2 : now / 2 + 1;
                result += (long) left * (long) right;
                q.add(left);
                q.add(right);
            }

        }
        bw.write(Long.toString(result));
        bw.flush();
    }
}
