import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Lecture> list = new ArrayList<>();
        StringTokenizer st;
        int max = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            list.add(new Lecture(p, d));
        }
        Collections.sort(list, (l1, l2) -> {
            if(l1.d == l2.d){
                return Integer.compare(l2.p, l1.p);
            }
            return Integer.compare(l2.d, l1.d);
        });
        int money = 0;
        if(n != 0){
            PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> Integer.compare(i2, i1));
            int nowDay = list.get(0).d;
            int index = 0;
            while(nowDay >= 1){
                while(index < n && list.get(index).d == nowDay){
                    pq.add(list.get(index).p);
                    index++;
                }
                if(!pq.isEmpty()){
                    int now = pq.remove();
                    money += now;
                }
                nowDay--;
            }
        }
        bw.write(Integer.toString(money));
        bw.flush();
    }
}
class Lecture{
    int p;
    int d;
    Lecture(int p, int d){
        this.p = p;
        this. d = d;
    }
}
