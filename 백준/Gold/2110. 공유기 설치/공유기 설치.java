import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] home = new int[N];
        for(int i = 0; i < N; i++){
            home[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(home);

        int lo = 1;
        int hi = home[N-1] + 1;
        while(lo + 1 < hi){
            // 가장 인접한 공유기 거리
            int mid = lo + (hi - lo) / 2;
            int cCount = 1;
            int now = home[0];
            for(int i = 1; i < N; i++){
                if(now + mid <= home[i]){
                    cCount++;
                    now = home[i];
                }
            }
            if(cCount >= C){
                lo = mid;
            }else{
                hi = mid;
            }
        }
        bw.write(Integer.toString(lo));
        bw.flush();
    }
}
