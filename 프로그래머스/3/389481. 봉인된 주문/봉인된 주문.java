import java.io.*;
import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        // 정렬
        Arrays.sort(bans, (s1, s2) -> {
            if(s1.length() == s2.length()){
                return s1.compareTo(s2);
            }
            return Integer.compare(s1.length(), s2.length()); 
        });
        // ban 포함 순서 구하기
        for(int i = 0; i < bans.length; i++){
            String nowBan = bans[i];
            long bansOrder = 0;
            int start = 0;
            for(int j = nowBan.length() - 1; j >= 0; j--){
                char now = nowBan.charAt(j);
                bansOrder += (now - 'a' + 1) * (long)(Math.pow(26, start++));
            }
            if(bansOrder <= n){
                n++;
            }    
        }
        // 실제 값 구하기
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            n--;
            sb.append((char)('a' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }
}