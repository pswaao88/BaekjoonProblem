import java.io.*;
import java.util.*;

public class Main {
    static int[] importance;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            Queue<Document> q = new LinkedList<>();
            importance = new int[10];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                int nowImportance = Integer.parseInt(st.nextToken());
                q.add(new Document(i, nowImportance));
                importance[nowImportance]++;
            }
            int count = 1;
            while(true){
                Document now = q.remove();
                importance[now.priority]--;
                if(isOk(now.priority)){
                    if(now.firstIndex == M){
                        sb.append(count).append("\n");
                        break;
                    }else{
                        count++;
                    }
                }else{
                    q.add(now);
                    importance[now.priority]++;
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static boolean isOk(int target){
        for(int i = target + 1; i <= 9; i++){
            if(importance[i] >= 1){
                return false;
            }
        }
        return true;
    }

}

class Document{
    int firstIndex;
    int priority;
    Document(int firstIndex, int priority){
        this.firstIndex = firstIndex;
        this.priority = priority;
    }
}
