import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;
        while(left <= right){
            if(sum > M){
                sum -= A[left];
                left++;
            }else if(sum < M){
                if(right == N) break;
                sum += A[right];
                right++;
            }else{
                count++;
                sum -= A[left];
                left++;
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
    }
}
