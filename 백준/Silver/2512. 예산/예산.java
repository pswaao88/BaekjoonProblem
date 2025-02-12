import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] budget = new int[N];
        int lo = 0;
        int hi = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            budget[i] = Integer.parseInt(st.nextToken());
            hi = Math.max(hi, budget[i]);
        }
        hi++;
        int M = Integer.parseInt(br.readLine());

        while(lo + 1 < hi){
            int mid = (lo + hi) / 2;
            int now = 0;
            for(int i = 0; i < N; i++){
                if(mid < budget[i]){
                    now += mid;
                }else{
                    now += budget[i];
                }
            }
            // 클때
            if(now > M){
                hi = mid;
            }else{// 작거나 같을 때
                lo = mid;
            }
        }
        bw.write(Integer.toString(lo));
        bw.flush();
        bw.close();
        br.close();


    }
}
