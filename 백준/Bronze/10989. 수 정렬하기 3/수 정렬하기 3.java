import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] number = new int[10001];
        for (int i = 0; i < N; i++) {
            number[Integer.parseInt(br.readLine())]++;
        }
        for(int i = 1; i <= 10000; i++){
            writeInteger(number[i], i);
        }
        bw.flush();
        bw.close();
        br.close();
    }
    static void writeInteger(int count, int value)throws IOException{
        for(int i = 0; i < count; i++){
            bw.write(Integer.toString(value));
            bw.newLine();
        }

    }


}
