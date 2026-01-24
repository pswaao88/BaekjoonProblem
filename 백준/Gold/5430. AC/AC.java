import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());


        StringBuilder sb = new StringBuilder();
        while(T-->0){
            boolean error = false;
            boolean reverse = false;

            Deque<Integer> inOrder = new LinkedList<>();

            char[] AC = br.readLine().toCharArray();
            int size = Integer.parseInt(br.readLine());
            String array = br.readLine();
            array = array.substring(1, array.length()-1);

            StringTokenizer st = new StringTokenizer(array, ",");
            // 초기화
            for(int i = 0; i < size; i++){
                int now = Integer.parseInt(st.nextToken());
                inOrder.addLast(now);
            }
            for(int i = 0; i < AC.length; i++){
                char now = AC[i];
                if(now == 'R'){
                    reverse = !reverse;
                }else{
                    if (inOrder.isEmpty()) {
                        error = true;
                        break;
                    }
                    if(reverse){
                        inOrder.removeLast();
                    }else{
                        inOrder.removeFirst();
                    }
                }
            }
            if(error){
                sb.append("error").append("\n");
            }else{
                sb.append("[");
                size = inOrder.size();
                for(int i = 0; i < size; i++){
                    if(reverse){
                        sb.append(inOrder.removeLast());
                    }else{
                        sb.append(inOrder.removeFirst());
                    }
                    if(i == size -1) continue;
                    sb.append(",");
                }
                sb.append("]").append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
