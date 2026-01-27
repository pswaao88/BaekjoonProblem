import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] alpha = new int[26];
        for(int i = 0; i < N; i++){
            String word = br.readLine();
            for(int j = 0; j < word.length(); j++){
                char now = word.charAt(j);
                alpha[now - 'A'] += change(word.length(), j);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a1, a2) -> a2 - a1);
        for(int i = 0; i < 26; i++){
            if(alpha[i] == 0) continue;
            pq.add(alpha[i]);
        }
        int result = 0;
        int nowValue = 9;
        int size = pq.size();
        for(int i = 0; i < size; i++){
            int nowCount = pq.remove();
            result += (nowValue * nowCount);
            nowValue--;
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    
    static int change(int length, int index){
        int ten = length - index - 1;
        return (int) Math.pow(10, ten);
    }
}
