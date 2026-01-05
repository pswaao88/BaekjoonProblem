import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] atm = new int[N];
        for(int i = 0; i < N; i++){
            atm[i] = Integer.parseInt(st.nextToken());
        }
        // 삽입 정렬
        int boundary = 1;
        for(int i = 0; i < N; i++){
            if(N == 1) break;
            int now = atm[boundary];
            int index = boundary;
            for(int j = 0; j < boundary; j++){
                if(now < atm[j]){
                    index = j;
                    break;
                }
            }
            // 한칸씩 뒤로 밀기
            for(int j = boundary; j > index; j--){
                atm[j] = atm[j-1];
            }
            atm[index] = now;
            if(boundary == N-1) break;
            boundary++;
        }
        int result = 0;
        int sum = 0;
        for(int i = 0; i < N; i++){
            sum += atm[i];
            result += sum;
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
