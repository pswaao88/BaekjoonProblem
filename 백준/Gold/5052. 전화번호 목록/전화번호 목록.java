import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++){
            int n = Integer.parseInt(br.readLine());
            String[] sortedNumber = new String[n];
            for(int i = 0; i < n; i++){
                sortedNumber[i] = br.readLine();
            }
            Arrays.sort(sortedNumber);

            boolean yes = true;
            for(int i = 0; i < n-1; i++){
                String now = sortedNumber[i];
                String next = sortedNumber[i+1];
                if(now.length() > next.length()) continue;
                String prefix = next.substring(0, now.length());
                if(prefix.equals(now)){
                    yes = false;
                    break;
                }

            }
            if(yes) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
