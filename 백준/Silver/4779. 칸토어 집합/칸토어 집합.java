import java.io.*;
import java.util.*;

public class Main {
    static char[] target;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        String s;
        while((s = br.readLine()) != null){
            int N = Integer.parseInt(s);
            int stringLength = (int) Math.pow(3, N);

            target = new char[stringLength];
            for(int i = 0; i < stringLength; i++){
                target[i] = '-';
            }
            make(0, stringLength - 1);

            for(int i = 0; i < stringLength; i++){
                sb.append(target[i]);
            }
            sb.append("\n");
        }


        bw.write(sb.toString());
        bw.flush();
    }
    // 시작 및 끝 index
    static void make(int start, int end){
        if(start == end){
            return;
        }
        int nextSize = (end - start + 1) / 3;
        int leftStart = start;
        int leftEnd = start + nextSize - 1;
        make(leftStart, leftEnd);

        int rightStart = end - nextSize + 1;
        int rightEnd = end;
        make(rightStart, rightEnd);

        for(int i = leftEnd + 1; i < rightStart; i++){
            target[i] = ' ';
        }
    }
}
