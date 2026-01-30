import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] amount = new int[n+1];
        int[] wine = new int[n+1];
        for(int i = 1; i <= n; i++){
            amount[i] = Integer.parseInt(br.readLine());
        }
        int max = 0;
        wine[1] = amount[1];
        for(int i = 2; i <= n; i++){
            if(i == 2){
                wine[2] = wine[1] + amount[2];
            }else{
                int wine1 = wine[i-2] + amount[i];
                int wine2 = wine[i-3] + amount[i-1] + amount[i];
                int wine3 = wine[i-1];
                wine[i] = Math.max(Math.max(wine1, wine2), wine3);
            }

        }
        for(int i = 1; i <= n; i++){
            max = Math.max(max, wine[i]);
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
