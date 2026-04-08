import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Point> points = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            points.add(new Point(Integer.parseInt(st.nextToken()), 0));
            points.add(new Point(Integer.parseInt(st.nextToken()), 1));
        }
        // 겹치지 않으므로 같다면 끝점이 먼저
        Collections.sort(points, (p1, p2) -> {
            if(p1.start == p2.start){
                return p2.type - p1.type;
            }
            return p1.start - p2.start;
        });
        int max = 0;
        int count = 0;
        for(int i = 0; i < points.size(); i++) {
            Point now = points.get(i);
            if(now.type == 0){
                count++;
            }else{
                count--;
            }
            max = Math.max(max, count);
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
class Point{
    int start;
    int type; // 0이면 시작점, 1이면 끝점
    Point(int start, int type){
        this.start = start;
        this.type = type;
    }
}
