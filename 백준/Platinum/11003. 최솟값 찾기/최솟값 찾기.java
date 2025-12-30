import java.io.*;
import java.util.*;

class Node{
    int index;
    int num;
    public Node(int index, int num){
        this.index = index;
        this.num = num;
    }
}

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        Deque<Node> deque = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            Node nowNode = new Node(i, Integer.parseInt(st.nextToken()));
            // 먼저 벗어난 index 제거
            if(!deque.isEmpty() && (nowNode.index - deque.getFirst().index >= L)){
                deque.pollFirst();
            }
            // 값 비교해 넣기
            while(!deque.isEmpty() && (deque.getLast().num > nowNode.num)){
                deque.pollLast();
            }
            deque.addLast(nowNode);
            sb.append(deque.getFirst().num);
            sb.append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
