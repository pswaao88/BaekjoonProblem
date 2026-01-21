import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> charStack = new Stack<>();
        Stack<Integer> indexStack = new Stack<>();

        char[] s = br.readLine().toCharArray();
        char[] bomb = br.readLine().toCharArray();

        int bombIndex = 0;
        for(int i = 0; i < s.length; i++){
            charStack.push(s[i]);
            if(charStack.peek() == bomb[bombIndex]){
                indexStack.push(bombIndex);
                bombIndex++;
            }else if(charStack.peek() == bomb[0]){ // 다를 경우 맨처음 값 과 같은지 검사 why => 다시 처음부터 쌓일 수 도 있기 때문
                indexStack.push(0);
                bombIndex = 1;
            }else{
                indexStack.push(-1);
                bombIndex = 0;
            }
            if(charStack.size() >= bomb.length && indexStack.peek() == bomb.length - 1){
                for(int j = 0; j < bomb.length; j++){
                    charStack.pop();
                    indexStack.pop();
                }
                bombIndex = indexStack.isEmpty() ? 0 : indexStack.peek() + 1;
                bombIndex = Math.max(bombIndex, 0);
            }
        }
        StringBuilder sb = new StringBuilder();
        int size = charStack.size();
        for(int i = 0; i < size; i++){
            sb.append(charStack.pop());
        }
        if(sb.length() == 0){
            sb.append("FRULA");
        }else{
            sb.reverse();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
