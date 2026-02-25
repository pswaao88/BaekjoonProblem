import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Integer> groups = new ArrayList<>();
        int start = 1;
        for(int i = 0; i < M; i++){
            int vip = Integer.parseInt(br.readLine());
            groups.add(vip - start);
            start = vip + 1;
        }
        if(start <= N){
            groups.add(N - start + 1);
        }
        //DP[i]까지 좌석이 있을때의 경우의수
        // i가 i에 오면 DP[i-1] 더학;
        // i가 i-1에 오면 i에 i-1이 와야 성립하므로 DP[i-2] 더하기
        // i+1의 경우
        int[] DP = new int[41];
        DP[0] = 1;
        DP[1] = 1;
        DP[2] = 2;
        for(int i = 3; i <= 40; i++){
            DP[i] = DP[i-1] + DP[i-2];
        }

        int result = 1;
        for(int i = 0; i < groups.size(); i++){
            result *= DP[groups.get(i)];
        }

        bw.write(Integer.toString(result));
        bw.flush();
    }
}
