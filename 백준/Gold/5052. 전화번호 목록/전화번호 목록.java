import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(T-->0){
            int n = Integer.parseInt(br.readLine());
            String[] number = new String[n];
            for(int i = 0; i < n; i++){
                number[i] = br.readLine();
            }
            Arrays.sort(number);
            boolean yes = true;
            for(int i = 1; i < n; i++){
                char[] number1 = number[i-1].toCharArray();
                char[] number2 = number[i].toCharArray();
                int count = 0;
                for(int j = 0; j < number1.length; j++){
                    if(number1[j] != number2[j]) break;
                    count++;
                }
                if(count == number1.length){
                    yes = false;
                    break;
                }
            }
            if(yes){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
