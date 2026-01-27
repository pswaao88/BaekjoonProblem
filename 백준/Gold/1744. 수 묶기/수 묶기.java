import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        boolean zero = false;
        long result = 0;
        PriorityQueue<Integer> positive = new PriorityQueue<>((i1, i2) -> Integer.compare(i2, i1));
        PriorityQueue<Integer> negative = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            int now = Integer.parseInt(br.readLine());
            if(now > 0) {
                positive.add(now);
            }else if(now < 0){
                negative.add(now);
            }else{
                zero = true;
            }
        }
        int positiveSize = positive.size() / 2;
        boolean positiveEven = (positive.size() % 2 == 0);
        for(int i = 0; i < positiveSize; i++){
            int a = positive.remove();
            int b = positive.remove();
            result += Math.max(((long) a * b), a + b);
        }
        if(!positiveEven){
            result += positive.remove();
        }

        int negativeSize = negative.size() / 2;
        boolean negativeEven = (negative.size() % 2 == 0);
        for(int i = 0; i < negativeSize; i++){
            int a = negative.remove();
            int b = negative.remove();
            result += ((long) a * b);
        }
        if(!negativeEven && !zero){
            result += negative.remove();
        }
        bw.write(Long.toString(result));
        bw.flush();
    }
}
