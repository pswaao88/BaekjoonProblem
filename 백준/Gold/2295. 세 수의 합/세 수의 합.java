import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(number);

        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int now = number[j] + number[i];
                set.add(now);
            }
        }
        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int now = number[j] - number[i];
                if(set.contains(now)){
                    max = Math.max(max, number[j]);
                }
            }
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
