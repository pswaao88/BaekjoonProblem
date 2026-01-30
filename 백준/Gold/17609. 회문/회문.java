import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            String s = br.readLine();
            sb.append(isPalindrome(s)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static int isPalindrome(String target){
        for(int i = 0; i < (target.length() + 1) / 2; i++){
            int left = i;
            int right = (target.length() - 1) - i;
            if(target.charAt(left) != target.charAt(right)){
                String leftTarget = target.substring(left + 1, right + 1);
                int leftPalindrome = isPseudoPalindrome(leftTarget);

                String rightTarget = target.substring(left, right);
                int rightPalindrome = isPseudoPalindrome(rightTarget);

                if(leftPalindrome == 1 || rightPalindrome == 1){
                    return 1;
                }else{
                    return 2;
                }

            }
        }
        return 0;
    }
    static int isPseudoPalindrome(String target){
        for(int i = 0; i < (target.length() + 1) / 2; i++){
            int left = i;
            int right = (target.length() - 1) - i;
            if(target.charAt(left) != target.charAt(right)){
                return 2;
            }
        }
        return 1;
    }
}
