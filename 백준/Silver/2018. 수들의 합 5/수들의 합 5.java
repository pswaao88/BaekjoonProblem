import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int left = 1; // left 부터 시작
        int right = 1; // right - 1 까지 더한것
        int sum = 0;
        int count = 0;

        // 3가지 경우 N이랑 같을때, N보다 클때, N보다 작을때
        while(left <= N){
            if(sum > N){
                sum -= left;
                left++;
            }else if(sum < N && right <= N){
                sum += right;
                right++;
            }else{
                count++;
                sum += right;
                right++;
                sum -= left;
                left++;
            }
        }

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
