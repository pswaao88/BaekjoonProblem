import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i  < N; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }
        long result = 0;
        while(pq.size() > 2){
            int a = pq.remove();
            int b = pq.remove();
            result += (a+b);
            pq.add(a+b);
        }
        if(pq.size() == 2){
            result += pq.remove();
            result += pq.remove();
        }
        bw.write(Long.toString(result));
        bw.flush();
    }
}
