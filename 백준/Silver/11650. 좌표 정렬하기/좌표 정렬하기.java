import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        ArrayList<SortCoordinate> list = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new SortCoordinate(x, y));
        }
        Collections.sort(list, (c1, c2) -> {
           if(c1.x == c2.x){
               return Integer.compare(c1.y, c2.y);
           }
           return Integer.compare(c1.x, c2.x);
        });
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            SortCoordinate now = list.get(i);
            sb.append(now.x).append(" ").append(now.y).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
class SortCoordinate{
    int x;
    int y;
    SortCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
