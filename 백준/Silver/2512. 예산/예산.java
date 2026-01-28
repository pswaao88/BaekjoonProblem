import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int givenBudget = Integer.parseInt(br.readLine());

        int[] budget = new int[N];
        for(int i = 0; i < N; i++){
            budget[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(budget);
        int lo = 1;
        int hi = budget[N-1] + 1;
        while(lo + 1 < hi){
            int mid = lo + (hi - lo) / 2;
            int sum = 0;
            for(int i = 0; i < N; i++){
                sum += Math.min(mid, budget[i]);
            }
            if(sum > givenBudget){
                hi = mid;
            }else if(sum < givenBudget){
                lo = mid;
            }else{
                lo = mid;
            }
        }
        bw.write(Integer.toString(lo));
        bw.flush();
    }
}
