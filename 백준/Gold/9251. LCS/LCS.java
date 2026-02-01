import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] s1 = br.readLine().toCharArray();
        char[] s2 = br.readLine().toCharArray();
        int[][] DP = new int[s1.length + 1][s2.length + 1];

        for(int i = 1; i <= s1.length; i++){
            for(int j = 1; j <= s2.length; j++){
                if(s1[i-1] == s2[j-1]){
                    DP[i][j] = DP[i-1][j-1] + 1;
                }else{
                    DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
                }
            }
        }
        bw.write(Integer.toString(DP[s1.length][s2.length]));
        bw.flush();
    }
}
