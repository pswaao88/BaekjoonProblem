import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Meeting> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list, (m1, m2) -> {
            if(m1.end == m2.end){
                return Integer.compare(m1.start, m2.start);
            }
            return Integer.compare(m1.end, m2.end);
        });
        int count = 0;
        int endTime = -1;
        for(int i = 0; i < N; i++){
            Meeting now = list.get(i);
            if(endTime <= now.start){
                count++;
                endTime = now.end;
            }
        }
        bw.write(Integer.toString(count));
        bw.flush();
    }
}
class Meeting{
    int start;
    int end;
    Meeting(int start, int end){
        this.start = start;
        this.end = end;
    }
}
