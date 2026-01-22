import java.io.*;
import java.util.*;

public class Main {
    static int[] littleCharG, bigCharG, nowLittleCharG, nowBigCharG;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int g = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        char[] W = br.readLine().toCharArray();
        char[] S = br.readLine().toCharArray();
        littleCharG = new int[26];
        bigCharG = new int[26];

        for(int i = 0; i < g; i++){
            if('a' <= W[i] && W[i] <= 'z'){
                littleCharG[W[i] - 'a']++;
            }else{
                bigCharG[W[i] - 'A']++;
            }
        }

        nowLittleCharG = new int[26];
        nowBigCharG = new int[26];

        for(int i = 0; i < g-1; i++){
            if('a' <= S[i] && S[i] <= 'z'){
                nowLittleCharG[S[i] - 'a']++;
            }else{
                nowBigCharG[S[i] - 'A']++;
            }
        }

        int count = 0;
        for(int i = g-1; i < s; i++){
            if('a' <= S[i] && S[i] <= 'z'){
                nowLittleCharG[S[i] - 'a']++;
            }else{
                nowBigCharG[S[i] - 'A']++;
            }
            if (check()) count++;
            if('a' <= S[i-g+1] && S[i-g+1] <= 'z'){
                nowLittleCharG[S[i-g+1] - 'a']--;
            }else{
                nowBigCharG[S[i-g+1] - 'A']--;
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean check(){
        for(int i = 0; i < 26; i++){
            if(littleCharG[i] != nowLittleCharG[i] || bigCharG[i] != nowBigCharG[i]){
                return false;
            }
        }
        return true;
    }
}
