import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        HashSet<String> set = new HashSet<>();
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < s.length() - i + 1; j++){
                set.add(s.substring(j, j + i));
            }
        }
        bw.write(Integer.toString(set.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}
