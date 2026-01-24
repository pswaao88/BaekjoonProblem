import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean yes = true;
        int now = 1;
        for(int i = 0; i < n; i++){
            int stackNumber = Integer.parseInt(br.readLine());
            if(stack.isEmpty() || stack.peek() < stackNumber){
                while(now <= stackNumber){
                    stack.push(now++);
                    sb.append("+").append("\n");
                }
                stack.pop();
                sb.append("-").append("\n");
            }else if(stack.peek() == stackNumber){
                stack.pop();
                sb.append("-").append("\n");
            }else{
                yes = false;
                break;
            }
        }
        if(yes){
            bw.write(sb.toString());
        }else{
            bw.write("NO");
        }
        bw.flush();
    }
}
