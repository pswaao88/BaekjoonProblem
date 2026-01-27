import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = br.readLine();
        int index = s.length();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '-'){
                index = i;
                break;
            }
        }

        int result = 0;
        String positive = s.substring(0, index);
        StringTokenizer st = new StringTokenizer(positive, "+");
        int positiveSize = st.countTokens();
        for(int i = 0; i < positiveSize; i++){
            result += Integer.parseInt(st.nextToken());
        }
        if(index != s.length()){
            String negative = s.substring(index + 1);
            st = new StringTokenizer(negative, "+|-");
            int negativeSize = st.countTokens();
            for(int i = 0; i < negativeSize; i++){
                result -= Integer.parseInt(st.nextToken());
            }
        }

        bw.write(Integer.toString(result));
        bw.flush();
    }
}
