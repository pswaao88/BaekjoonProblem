import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Work[] works = new Work[N];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            works[i] = new Work(t, s);
        }
        Arrays.sort(works, (w1, w2) -> {
            if(w1.S == w2.S){
                return w1.T - w2.T;
            }
            return w1.S - w2.S;
        });
        int[] sum = new int[N+1];
        for(int i = 0; i < N; i++){
            sum[i+1] = sum[i] + works[i].T;
        }

        // lo 가 T
        // hi 가 F
        int lo = -1;
        int hi = 1000001;
        while(lo + 1 < hi){
            int mid = lo + (hi - lo) / 2;
            boolean yes = true;
            for(int i = 1; i <= N; i++){
                Work now = works[i-1];
                if(sum[i] + mid > now.S){
                    yes = false;
                    break;
                }
            }
            if(yes){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        bw.write(Integer.toString(lo));
        bw.flush();
    }
}
class Work{
    int T;
    int S;
    Work(int T, int S){
        this.T = T;
        this.S = S;
    }
}
