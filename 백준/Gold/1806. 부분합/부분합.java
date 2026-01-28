import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] number = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        int min = 987654321;
        int sum = 0;
        int left = 0;
        for(int right = 0; right < N; right++){
            sum += number[right];
            while(sum > S && left <= right){
                min = Math.min(min, right - left + 1);
                sum -= number[left];
                left++;
            }
            if(sum == S){
                min = Math.min(min, right - left + 1);
            }
        }
        if(min == 987654321) min = 0;
        bw.write(Integer.toString(min));
        bw.flush();
    }
}
