import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        long[] line = new long[K];
        for(int i = 0; i < K; i++){
            line[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(line);
        long lo = 1;
        long hi = line[K-1] + 1;
        while(lo + 1 < hi){
            long mid = lo + (hi - lo) / 2;
            long count = 0;
            for(int i = 0; i < K; i++){
                count += line[i] / mid;
            }
            if(count >= N){
                lo = mid;
            }else if(count < N){
                hi = mid;
            }
        }
        bw.write(Long.toString(lo));
        bw.flush();
    }
}
