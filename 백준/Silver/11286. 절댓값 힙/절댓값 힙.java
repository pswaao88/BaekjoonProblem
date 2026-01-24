import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (o1, o2) -> {
                int a1 = Math.abs(o1);
                int a2 = Math.abs(o2);
                if(a1 == a2){
                    return o1 - o2;
                }
                return a1 - a2;
            }
        );
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            int x = Integer.parseInt(br.readLine());
            if(x == 0){
                if(pq.isEmpty()){
                    sb.append(0);
                }else{
                    sb.append(pq.remove());
                }
                sb.append("\n");
            }else{
                pq.add(x);
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
