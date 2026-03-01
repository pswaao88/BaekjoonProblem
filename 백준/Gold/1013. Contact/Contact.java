import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String regex1 = "(100+1+|01)+";
        Pattern pattern = Pattern.compile(regex1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            Matcher matcher = pattern.matcher(br.readLine());
            if(matcher.matches()){
                sb.append("YES\n");
            }else{
                sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
