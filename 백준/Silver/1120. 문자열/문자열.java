import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();
        int result = 987654321;
        for(int i = 0; i < B.length() - A.length() + 1; i++){
            String now = B.substring(i, i + A.length());
            result = Math.min(result, stringMinus(A, now));
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();

    }
    private static int stringMinus(String A, String subStringOfB){
        char[] AChar = A.toCharArray();
        char[] BChar = subStringOfB.toCharArray();
        int count = 0;
        for(int i = 0; i < A.length(); i++){
            if(AChar[i] != BChar[i]){
                count++;
            }
        }
        return count;
    }
}
