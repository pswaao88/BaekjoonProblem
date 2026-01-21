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
            ArrayList<String> list = new ArrayList<>();
            for(int i = 0; i < n; i++){
                list.add(br.readLine());
            }
            list.sort((o1, o2) -> o2.length() - o1.length());
            HashSet<String> set = new HashSet<>();
            boolean yes = true;
            for(int i = 0; i < n; i++){
                String now = list.get(i);
                if(set.contains(now)){
                    yes = false;
                    break;
                }

                for(int j = 1; j <= now.length(); j++){
                    String next = now.substring(0, j);
                    set.add(next);
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
