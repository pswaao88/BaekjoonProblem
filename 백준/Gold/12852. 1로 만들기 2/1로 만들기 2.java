import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] DP = new int[N+1];
        Arrays.fill(DP, 987654321);
        HashMap<Integer, String> map = new HashMap<>();
        DP[0] = 0;
        DP[1] = 0;
        map.put(0, "");
        map.put(1, "1");

        StringBuilder sb = new StringBuilder();
        for(int i = 2; i <= N; i++){
            if(i % 3 == 0 && DP[i] > DP[i / 3] + 1){
                DP[i] = DP[i / 3] + 1;

                sb.delete(0, sb.length());
                String now = map.get(i / 3);
                sb.append(now);
                sb.insert(0, " ");
                sb.insert(0, i);
                map.put(i, sb.toString());
            }
            if(i % 2 == 0 && DP[i] > DP[i / 2] + 1){
                DP[i] = DP[i / 2] + 1;

                sb.delete(0, sb.length());
                String now = map.get(i / 2);
                sb.append(now);
                sb.insert(0, " ");
                sb.insert(0, i);
                map.put(i, sb.toString());
            }
            if(DP[i] > DP[i-1] + 1){
                DP[i] = DP[i - 1] + 1;

                sb.delete(0, sb.length());
                String now = map.get(i - 1);
                sb.append(now);
                sb.insert(0, " ");
                sb.insert(0, i);
                map.put(i, sb.toString());
            }
        }
        sb.delete(0, sb.length());
        sb.append(DP[N]).append("\n").append(map.get(N));

        bw.write(sb.toString());
        bw.flush();
    }
}
