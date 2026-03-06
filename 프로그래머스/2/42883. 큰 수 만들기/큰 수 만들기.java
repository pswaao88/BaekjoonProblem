import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < number.length(); i++){
            int now = number.charAt(i) - '0';
            while(!stack.isEmpty() && stack.peek() < now && k > 0){
                stack.pop();
                k--;
            }
            stack.push(now);
        }
        if(k > 0){
            for(int i = 0; i < k; i++){
                stack.pop();
            }
            
        }
        StringBuilder sb = new StringBuilder();
        int size = stack.size();
        for(int i = 0; i < size; i++){
            int now = stack.pop();
            sb.append(now);
        }
        sb.reverse();
        return sb.toString();
    }
    
}