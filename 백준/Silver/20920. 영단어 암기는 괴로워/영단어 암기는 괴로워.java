import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Word> isPresent = new HashMap<>();
        PriorityQueue<Word> pq = new PriorityQueue<>((o1, o2) -> {
            if(o2.count == o1.count){
                if(o1.content.length() == o2.content.length()){
                    return o1.content.compareTo(o2.content);
                }
                return Integer.compare(o2.content.length(), o1.content.length());
            }
            return Integer.compare(o2.count, o1.count);
        });
        for(int i = 0; i < N; i++){
            String now = br.readLine();
            if(now.length() < M) continue;
            Word nowWord;
            if(isPresent.containsKey(now)){
                nowWord = isPresent.get(now);
                nowWord.count++;
            }else{
                nowWord = new Word(now, 1);
                isPresent.put(now, nowWord);
            }
        }
        List<Map.Entry<String, Word>> list = new LinkedList<>(isPresent.entrySet());
        for(Map.Entry<String, Word> now : list){
            pq.add(now.getValue());
        }
        StringBuilder sb = new StringBuilder();
        int size = pq.size();
        for(int i = 0; i < size; i++){
            sb.append(pq.poll().content).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
class Word{
    String content;
    int count;
    Word(String content, int count){
        this.content = content;
        this.count = count;
    }
}
