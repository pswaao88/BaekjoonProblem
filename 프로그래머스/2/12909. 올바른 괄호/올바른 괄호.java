import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;

        int n = s.length();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            char now = s.charAt(i);
            if(now == '('){
                stack.push('(');
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}