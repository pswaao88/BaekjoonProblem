import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Crystal> crystals = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            crystals.add(new Crystal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(crystals, (c1, c2) -> {
           if(c1.M == c2.M){
               return Integer.compare(c2.V, c1.V);
           }
           return Integer.compare(c1.M, c2.M);
        });

        int[] bags = new int[K];
        for(int i = 0; i < K; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);

        long value = 0;
        int nowIndex = 0;
        // i번째 보다 작은 보석들만 들어갈 pq
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for(int i = 0; i < K; i++){
            int nowCarry = bags[i];
            while(nowIndex < N && crystals.get(nowIndex).M <= nowCarry){
                pq.add(crystals.get(nowIndex).V);
                nowIndex++;
            }
            if(!pq.isEmpty()){
                value += pq.remove();
            }
        }
        bw.write(Long.toString(value));
        bw.flush();
    }
}
class Crystal{
    int M;
    int V;
    Crystal(int M, int V){
        this.M = M;
        this.V = V;
    }
}
