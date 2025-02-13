import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        hanoi(N, 1,3);
        bw.write(Integer.toString(cnt));
        bw.newLine();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void hanoi(int N, int x, int y){// N을 x => y로이동
        cnt++;
        if(N == 1){
            sb.append(x);
            sb.append(" ");
            sb.append(y);
            sb.append("\n");
            return;
        }
        int z = setZ(x,y);
        hanoi(N-1, x, z);
        sb.append(x);
        sb.append(" ");
        sb.append(y);
        sb.append("\n");
        hanoi(N-1, z, y);
    }
    static int setZ(int x, int y){
        if((x == 1 && y == 2) || (x == 2 && y == 1)){
            return 3;
        }
        if((x == 2 && y == 3) || (x == 3 && y == 2)){
            return 1;
        }
        return 2;
    }

}
