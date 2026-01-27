import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] coin = new int[N];
        for(int i = 0; i < N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        int count = 0;
        for(int i = N-1; i >= 0; i--){
            if(K == 0) break;
            if(K / coin[i] > 0){
                int now = K / coin[i];
                count += now;
                K -= now * coin[i];
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
    }
}
