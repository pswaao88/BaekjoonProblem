import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int target = Integer.parseInt(st.nextToken());
            boolean yes = false;
            int lo = 0;
            int hi = N;
            while(lo + 1 < hi){
                int mid = lo + (hi-lo) / 2;
                if(A[mid] == target){
                    yes = true;
                    break;
                }else if(A[mid] < target){
                    lo = mid;
                }else {
                    hi = mid;
                }
            }
            if(A[lo] == target) yes = true;
            if(yes) sb.append("1");
            else sb.append("0");
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

}
