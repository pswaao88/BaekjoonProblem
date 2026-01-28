import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] tree = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            tree[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(tree);

        int lo = 0;
        int hi = tree[N-1];
        while(lo + 1 < hi){
            int mid = lo + (hi - lo) / 2;
            long sum = 0;
            for(int i = 0; i < N; i++){
                sum += Math.max(0, tree[i] - mid);
            }
            if(sum == M){
                lo = mid;
            }else if(sum > M){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        bw.write(Integer.toString(lo));
        bw.flush();
    }
}
