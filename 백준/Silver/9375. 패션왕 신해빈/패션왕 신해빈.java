import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T --> 0){
            int N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();
            StringTokenizer st;
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String type = st.nextToken();
                map.put(type, map.getOrDefault(type, 0) + 1);
            }
            int result = 1;
            for(Map.Entry<String, Integer> x : map.entrySet()){
                result *= (x.getValue() + 1);
            }
            sb.append(result - 1).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
