import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            String now = br.readLine();
            if(palindrome(0, now.length()-1, now)){
                sb.append(0);
            }else if(pseudoPalindrome(0, now.length() - 1, now, 0)){
                sb.append(1);
            }else{
                sb.append(2);
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static boolean palindrome(int start, int end, String s){
        char[] now = s.toCharArray();
        int left = start;
        int right = end;
        while(left <= right){
            if(now[left] != now[right]) return false;
            left++;
            right--;
        }
        return true;
    }
    static boolean pseudoPalindrome(int start, int end, String s, int depth){
        if(depth > 1){
            return false;
        }
        char[] now = s.toCharArray();

        int left = start;
        int right = end;

        while(left <= right){
            if(now[left] != now[right] ) {
                if(depth != 0){
                    return false;
                }
                boolean yes;
                yes = pseudoPalindrome(left + 1, right, s, depth + 1);
                if(!yes){
                    yes = pseudoPalindrome(left, right - 1, s, depth + 1);
                }
                return yes;
            }
            left++;
            right--;
        }
        return true;
    }


}
