import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Product_2[] products = new Product_2[N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            products[i] = new Product_2(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[] DP = new int[K+1];
        for(int i = 1; i <= N; i++){
            Product_2 now = products[i];
            for(int j = K; j >= 1; j--){
                if(now.W > j) continue;
                DP[j] = Math.max(DP[j], DP[j-now.W] + now.V);
            }
        }
        bw.write(Integer.toString(DP[K]));
        bw.flush();
    }
}
class Product_2{
    int W;
    int V;
    Product_2(int W, int V){
        this.W = W;
        this.V = V;
    }
}
