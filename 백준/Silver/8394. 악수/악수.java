import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // DP[i][0] 본인은 악수 안할때 DP[i-1][0] + DP[i-1][1]
        // DP[i][1] 왼쪽(i-1)과 악수 할때 DP[i-2][0] + DP[i-2][1]
        // 오른쪽은 i + 1이 처리

        int n = Integer.parseInt(br.readLine());
        // base case
        int f1 = 1;
        int f2 = 1;
        int f3 = f1;
        for(int i = 2; i <= n; i++){
            f3 = (f1 + f2) % 10;
            f1 = f2;
            f2 = f3;
        }
        bw.write(Integer.toString(f3));
        bw.flush();

    }
}
