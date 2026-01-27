import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        int[] sum = new int[N];
        for(int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(array);
        sum[0] = array[0];
        int result = sum[0];
        for(int i = 1; i < N; i++){
            sum[i] = array[i] + sum[i-1];
            result += sum[i];
        }

        bw.write(Integer.toString(result));
        bw.flush();
    }

}
