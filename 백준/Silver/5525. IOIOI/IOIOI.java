import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();
        if(N >= 500000){
            bw.write(Integer.toString(0));
        }else{
            HashMap<String, Integer> map = new HashMap<>();
            for(int i = 0; i < M - (2 * N + 1) + 1; i++){
                String now = S.substring(i, i + 2 * N + 1);
                map.put(now, map.getOrDefault(now, 0) + 1);
            }
            bw.write(Integer.toString(map.getOrDefault(makeIOI(N), 0)));
        }
        bw.flush();
        bw.close();
        br.close();
    }
    private static String makeIOI(int number){
        StringBuilder sb = new StringBuilder(number * 2 + 1);
        for(int i = 0; i < number * 2 + 1; i++){
            if(i % 2 == 0){
                sb.append("I");
            }else{
                sb.append("O");
            }
        }
        return sb.toString();
    }

}
