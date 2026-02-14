import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());
        int result = 0;
        while(X > 0){
            result += X & 1;
            X = X >> 1;
        }
        bw.write(Integer.toString(result));
        bw.flush();


    }
}
