import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        boolean yes = true;
        int nextNumber = 1;
        for(int i = 0; i < N; i++){
            int now = Integer.parseInt(br.readLine());
            // 불가능한 조건
            if(!stack.isEmpty() && stack.peek() > now){
                yes = false;
                break;
            }
            // push
            if(stack.isEmpty() || stack.peek() < now){
                while(nextNumber <= now){
                    stack.push(nextNumber);
                    sb.append("+");
                    sb.append("\n");
                    nextNumber++;
                }
            }
            // pop
            stack.pop();
            sb.append("-");
            if(i == N-1) continue;
            sb.append("\n");
        }
        if(yes){
            bw.write(sb.toString());
        }else{
            bw.write("NO");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
