import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<String> guitar = new PriorityQueue<>((s1, s2) -> {
            if(s1.length() == s2.length()){
                int s1Sum = sum(s1);
                int s2Sum = sum(s2);
                if(s1Sum == s2Sum){
                    return s1.compareTo(s2);
                }
                return Integer.compare(s1Sum, s2Sum);
            }
            return Integer.compare(s1.length(), s2.length());
        });
        for(int i = 0; i < N; i++){
            guitar.add(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        int size = guitar.size();
        for(int i = 0; i < size; i++){
            sb.append(guitar.poll()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    private static int sum(String now){
        int result = 0;
        char[] nowChar = now.toCharArray();
        for(int i = 0; i < now.length(); i++){
            if(nowChar[i] < '0' || nowChar[i] > '9') continue;
            result += nowChar[i] - '0';
        }
        return result;
    }
}
