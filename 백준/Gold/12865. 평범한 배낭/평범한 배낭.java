import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Product[] products = new Product[N+1];
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            products[i] = new Product(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int[][] DP = new int[N+1][K+1];
        for(int i = 1; i <= N; i++){
            Product now = products[i];
            int nowW = now.W;
            int nowV = now.V;
            for(int j = 1; j <= K; j++){
                if(nowW <= j){
                    DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-nowW] + nowV);
                }else{
                    DP[i][j] = DP[i-1][j];
                }

            }
        }
        bw.write(Integer.toString(DP[N][K]));
        bw.flush();
    }
}
class Product{
    int W;
    int V;
    Product(int W, int V){
        this.W = W;
        this.V = V;
    }
}
