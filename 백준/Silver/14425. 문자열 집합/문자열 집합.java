import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> S = new HashSet<>();
        for(int i = 0; i < N; i++){
            S.add(br.readLine());
        }
        int count = 0;
        for(int i = 0; i < M; i++){
            String now = br.readLine();
            if(S.contains(now)){
                count++;
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
        br.close();
    }
}
