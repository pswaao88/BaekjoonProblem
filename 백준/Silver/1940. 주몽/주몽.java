import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        if(M > 200000 || N == 1){
            bw.write("0");
        }else{
            int[] count = new int[N];
            int result = 0;
            for(int i = 0; i < N; i++){
                count[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(count);
            int left = 0;
            int right = N-1;
            while(left < right){
                int now = count[left] + count[right];
                if(now > M){
                    right--;
                }else if(now < M){
                    left++;
                }else{
                    result++;
                    right--;
                    left++;
                }
            }
            bw.write(Integer.toString(result));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
