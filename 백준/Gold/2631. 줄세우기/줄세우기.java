import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] child = new int[N];
        for(int i = 0; i < N; i++){
            child[i] = Integer.parseInt(br.readLine());
        }
        int[] LIS = new int[N];
        int max = 0;
        for(int i = 0; i < N; i++){
            LIS[i] = 1;
            for(int j = 0; j < i; j++){
                if(child[j] < child[i]){
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1);
                }
            }
            max = Math.max(max, LIS[i]);
        }
        bw.write(Integer.toString(N - max));
        bw.flush();
    }
}
