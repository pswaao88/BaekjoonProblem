import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> weights = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int now = Integer.parseInt(st.nextToken());
            weights.add(now);
        }
        boolean[] DP = new boolean[40001];
        DP[0] = true;
        for(int i = 0; i < N; i++){
            int now = weights.get(i);
            boolean[] tmp = new boolean[400001];
            for(int j = 0; j <= 40000; j++){
                tmp[j] = DP[j];
            }

            for(int j = 0; j <= 40000; j++){
                if(DP[j]){
                    int first = j + now;
                    if(first >= 0 && first <= 40000){
                        tmp[first] = true;
                    }
                    int second = Math.abs(now - j);
                    if(second <= 40000){
                        tmp[second] = true;
                    }
                }
            }
            DP = tmp;
        }
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int now = Integer.parseInt(st.nextToken());
            if(DP[now]){
                sb.append("Y");
            }else{
                sb.append("N");
            }
            sb.append(" ");
        }
        bw.write(sb.toString());
        bw.flush();

    }
}
