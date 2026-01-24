import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Deque<Integer> ohNumber = new LinkedList<Integer>();
        Deque<Integer> printNumber = new LinkedList<>();
        ohNumber.addFirst(A[N-1]);
        printNumber.addFirst(-1);
        for(int i = N-2; i >= 0; i--){
            while(!ohNumber.isEmpty() && A[i] >= ohNumber.getFirst()){
                ohNumber.removeFirst();
            }
            if(ohNumber.isEmpty()){
                printNumber.addFirst(-1);
            }else{
                printNumber.addFirst(ohNumber.getFirst());
            }
            ohNumber.addFirst(A[i]);
        }
        for(int x : printNumber){
            sb.append(x).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();

    }
}
