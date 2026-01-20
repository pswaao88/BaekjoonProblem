import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        char[] S = br.readLine().toCharArray();
        int count = 0;
        int OIcount = 0;
        for(int i = 1; i < M - 1; i++){
            if(S[i] =='O' && S[i-1] == 'I' && S[i+1] == 'I'){
                OIcount++;
                if(OIcount == N){
                    count++;
                    // 정답일 경우 다음 경우도 만족 할 수 도 있기 때문에 --
                    OIcount--;
                }
                i++;
            }else{
                OIcount = 0;
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
