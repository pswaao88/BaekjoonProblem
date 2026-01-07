import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter((System.out)));
        N = Integer.parseInt(br.readLine());
        int[] startPrime = {2, 3, 5, 7};
        for(int i = 0; i < startPrime.length; i++){
            findAmazingPrimeNumber(startPrime[i], 1);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean isPrime(int target){
        if(target == 2 || target == 3){
            return true;
        }
        for(int i = 2; i <= Math.sqrt(target); i++){
            if(target % i == 0){
                return false;
            }
        }
        return true;
    }
    static void findAmazingPrimeNumber(int x, int depth){
        if(depth == N){
            sb.append(x).append("\n");
            return;
        }
        for(int i = 1; i <= 9; i++){
            int next = x * 10 + i;
            if(isPrime(next)){
                findAmazingPrimeNumber(next,depth + 1);
            }
        }
    }

}
