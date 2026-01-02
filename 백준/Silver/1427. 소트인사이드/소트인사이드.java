import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        int[] number = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            number[i] = s.charAt(i) - '0';
        }
        // selection
        for(int i = 0; i < s.length(); i++){
            int max = number[i];
            int maxIndex = i;
            for(int j = i + 1; j < s.length(); j++){
                if(number[j] > max){
                    max = number[j];
                    maxIndex = j;
                }
            }
            int tmp = number[i];
            number[i] = max;
            number[maxIndex] = tmp;
        }
        for(int x : number){
            bw.write(Integer.toString(x));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
