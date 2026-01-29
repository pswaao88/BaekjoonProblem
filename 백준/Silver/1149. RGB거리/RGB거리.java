import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] min = new int[N+1][3];
        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(i == 1){
                min[1][0] = R;
                min[1][1] = G;
                min[1][2] = B;
            }else{
                min[i][0] = Math.min(min[i-1][1], min[i-1][2]) + R;
                min[i][1] = Math.min(min[i-1][2], min[i-1][0]) + G;
                min[i][2] = Math.min(min[i-1][0], min[i-1][1]) + B;
            }
        }
        bw.write(Integer.toString(Math.min(Math.min(min[N][0], min[N][1]), min[N][2])));
        bw.flush();
    }
}
