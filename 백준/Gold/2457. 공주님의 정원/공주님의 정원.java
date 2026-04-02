import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        visit = new boolean[calDay(12, 31) + 1];

        PriorityQueue<Flower> pq = new PriorityQueue<>((f1, f2) -> {
            if(f1.start == f2.start){
                return f2.end - f1.end;
            }
            return f1.start - f2.start;
        });
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int days1 = calDay(a, b);
            int days2 = calDay(c, d);
            pq.add(new Flower(days1, days2));
        }

        int nowDay = calDay(3, 1);
        int start = calDay(3, 1);
        int end = calDay(11, 30);

        int count = 0;
        Flower tmp = null;
        while(!pq.isEmpty()){
            Flower now = pq.remove();

            if(now.start > end || now.end < start) continue;
            // 아무것도 없다면 처음이므로 tmp를 갱신
            if(tmp == null){
                tmp = now;
            }else{
                // tmp랑 구간이 겹치면 대체가 되면서 더 길게 커버가 되는지 체크
                // now.start <= nowDay 여야 안끊김
                if(now.start <= nowDay){
                    // 안끊길 경우 end를 비교해 더 긴거를 선택해야함
                    // 기존이 더 길면 그대로 새로운게 더 길면 tmp 갱신
                    if(tmp.end < now.end){
                        tmp = now;
                    }
                }else{
                    // 끊길 경우 tmp를 visit으로 전부 true 처리 및 nowDay를 now.end로 갱신
                    nowDay = tmp.end;
                    makeVisit(tmp.start, tmp.end);
                    count++;
                    tmp = now;
                }
            }
        }
        // 마지막 한번 처리
        if(!isOk()){
            makeVisit(tmp.start, tmp.end);
            count++;
        }

        if(isOk()){
            bw.write(Integer.toString(count));
        }else{
            bw.write("0");
        }
        bw.flush();

    }
    static boolean isOk(){
        for(int i = calDay(3,1); i <= calDay(11, 30); i++){
            if(!visit[i]){
                return false;
            }
        }
        return true;
    }
    static int calDay(int month, int day){
        int result = day;
        for(int i = 1; i < month; i++){
            if(i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12){
                result += 31;
            }else if(i == 4 || i == 6 || i == 9 || i == 11){
                result += 30;
            }else{
                result += 28;
            }
        }
        return result;
    }
    static void makeVisit(int start, int end){
        for(int i = start; i < end; i++){
            visit[i] = true;
        }
    }

}
class Flower{
    int start;
    int end;
    Flower(int start, int end){
        this.start = start;
        this.end = end;
    }
}
