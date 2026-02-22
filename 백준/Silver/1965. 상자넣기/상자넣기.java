import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] box = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            box[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        int[] DP = new int[n];
        for(int i = 0; i < n; i++){
            DP[i] = 1;
            for(int j = 0; j < i; j++){
                if(box[j] < box[i]){
                    DP[i] = Math.max(DP[i], DP[j] + 1);
                }
            }
            max = Math.max(max, DP[i]);
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
