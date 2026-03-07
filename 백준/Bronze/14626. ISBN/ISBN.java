import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] s = br.readLine().toCharArray();
        int total = 0;
        int hiddenWeight = 0;
        // 짝수 1 홀수 3
        for(int i = 0; i < s.length; i++){
            if(s[i] == '*'){
                if(i == s.length - 1){
                    hiddenWeight = 1;
                }else{
                    hiddenWeight = (i % 2 == 0) ? 1 : 3;
                }
            }else{
                int weight;
                if(i == s.length - 1){
                    weight = 1;
                }else{
                    weight = (i % 2 == 0) ? 1 : 3;
                }
                total += (s[i] - '0') * weight;
            }
        }
        int result = 0;
        for(int i = 0; i <= 9; i++){
            if((total + (i * hiddenWeight)) % 10 == 0){
                result = i;
                break;
            }
        }

        bw.write(Integer.toString(result));
        bw.flush();
    }
}
