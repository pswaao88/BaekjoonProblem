import java.io.*;
import java.util.*;

class Node{
    int num;
    int index;
    public Node(int num, int index){
        this.num = num;
        this.index = index;
    }
}

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        Node[] sortedNumber = new Node[N];
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(br.readLine());
            sortedNumber[i] = new Node(number[i], i);
        }
        Arrays.sort(sortedNumber, (o1, o2) -> {
           if(o1.num == o2.num){
               return o1.index - o2.index;
           }
           return o1.num - o2.num;
        });
        int max = 0;
        for(int i = 0; i < N; i++){
            max = Math.max(max, sortedNumber[i].index - i + 1);
        }
        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
