import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] minArray = new int[N+1];
        Arrays.fill(minArray, 987654321);
        minArray[N] = 0;
        for(int i = N; i >= 1; i--){
            if(i % 3 == 0){
                minArray[i / 3] = Math.min(minArray[i / 3], minArray[i] + 1);
            }
            if(i % 2 == 0){
                minArray[i / 2] = Math.min(minArray[i / 2], minArray[i] + 1);
            }
            minArray[i - 1] = Math.min(minArray[i - 1], minArray[i] + 1);
        }
        bw.write(Integer.toString(minArray[1]));
        bw.flush();
    }
}
