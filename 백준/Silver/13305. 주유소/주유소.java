import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] load = new int[N-1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N-1; i++){
            load[i] = Integer.parseInt(st.nextToken());
        }

        int[] cost = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            cost[i] = Integer.parseInt(st.nextToken());
        }

        long min = Integer.MAX_VALUE;
        long result = 0;
        for(int i = 0; i < N-1; i++){
            min = Math.min(min, cost[i]);
            result += min * load[i];
        }
        bw.write(Long.toString(result));
        bw.flush();
    }
}
