import java.io.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] stair = new int[N+1];
        int[] DP = new int[N+1];

        for(int i = 1; i <= N; i++){
            stair[i] = Integer.parseInt(br.readLine());
        }
        DP[1] = stair[1];
        for(int i = 2; i <= N; i++){
            if(i >= 3){
                DP[i] = DP[i-2] + stair[i];
                DP[i] = Math.max(DP[i], stair[i] + stair[i-1] + DP[i-3]);
            }else{
                DP[i] = DP[i-1] + stair[2];
            }
        }

        bw.write(Integer.toString(DP[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}
