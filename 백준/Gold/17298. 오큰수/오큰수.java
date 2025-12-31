import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = N-1; i >= 0; i--){
            number[i] = Integer.parseInt(st.nextToken());
        }
        // 초기화
        Stack<Integer> stackNumber = new Stack<>();
        Stack<Integer> stackNGE = new Stack<>();
        stackNumber.push(number[0]);
        stackNGE.push(-1);

        for(int i = 1; i < N; i++){
            if(number[i-1] < number[i]){
                while(!stackNumber.isEmpty()){
                    if(stackNumber.peek() > number[i]){
                        break;
                    }
                    stackNumber.pop();
                }
                if(stackNumber.isEmpty()){
                    stackNumber.push(number[i]);
                    stackNGE.push(-1);
                }else{
                    stackNGE.push(stackNumber.peek());
                    stackNumber.push(number[i]);

                }
            }else if(number[i-1] > number[i]){
                stackNumber.push(number[i]);
                stackNGE.push(number[i-1]);
            }else{
                stackNGE.push(stackNGE.peek());
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append(stackNGE.pop());
            sb.append(" ");
        }
        bw.write(sb.toString().trim());
        bw.flush();
        bw.close();
        br.close();
    }
}
