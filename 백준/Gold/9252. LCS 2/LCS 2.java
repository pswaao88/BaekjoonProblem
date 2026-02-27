import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int[][] DP = new int[s1.length() + 1][s2.length() + 1];
        for(int i = 1; i <= s1.length(); i++){
            char s1Char = s1.charAt(i-1);
            for(int j = 1; j <= s2.length(); j++){
                DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);
                char s2Char = s2.charAt(j-1);
                if(s1Char == s2Char){
                    DP[i][j] = Math.max(DP[i][j], DP[i-1][j-1] + 1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(DP[s1.length()][s2.length()]).append("\n");
        StringBuilder LCS = new StringBuilder();
        int x = s1.length();
        int y = s2.length();
        while(x > 0 && y > 0){
            if(s1.charAt(x-1) == s2.charAt(y-1)){
                LCS.append(s1.charAt(x-1));
                x--;
                y--;
            }else if(DP[x][y] == DP[x-1][y]){
                x--;
            }else if(DP[x][y] == DP[x][y-1]){
                y--;
            }
        }
        sb.append(LCS.reverse());
        bw.write(sb.toString());
        bw.flush();
    }
}
