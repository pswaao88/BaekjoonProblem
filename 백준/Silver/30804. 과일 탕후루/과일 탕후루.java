import java.io.*;
import java.util.*;

public class Main {
    static int[] stick;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        stick = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            stick[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[] num = new int[9];
        int max = 0;
        int left = 0;
        for(int right = 0; right < N; right++){
            num[stick[right]]++;
            while(cal(num) > 2 && left <= right){
                num[stick[left]]--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
    static int cal(int[] num){
        int count = 0;
        for(int i = 0; i < num.length; i++){
            if(num[i] > 0){
                count++;
            }
        }
        return count;
    }


}
