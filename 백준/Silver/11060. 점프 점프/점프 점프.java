import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i  = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] DP = new int[N+1];
        Arrays.fill(DP, 987654321);
        DP[1] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while(!q.isEmpty()){
            int now = q.remove();
            for(int next = now + 1; next <= now + A[now]; next++){
                if(next > N) break;
                if(DP[next] > DP[now] + 1){
                    DP[next] = DP[now] + 1;
                    q.add(next);
                }
            }
        }
        if(DP[N] == 987654321){
            bw.write("-1");
        }else{
            bw.write(Integer.toString(DP[N]));
        }
        bw.flush();
    }
}
