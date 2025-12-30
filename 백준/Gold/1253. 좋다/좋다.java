import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] number = new int[N];
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(number);

        int result = 0;
        for(int i = 0; i < N; i++){
            // 초기 변수 세팅
            int now = number[i];

            int left = 0;
            if(left == i) left ++;

            int right = N-1;
            if(right == i) right --;

            boolean good = false;
            // 탐색
            while(left < right){
                int sum = number[left] + number[right];
                if(sum < now){
                    left++;
                    if(left == i) left ++;
                }else if(sum > now){
                    right--;
                    if(right == i) right --;
                }else{
                    good = true;
                    break;
                }
            }
            if(good){
                result++;
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
