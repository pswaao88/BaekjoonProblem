import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[] DP = new int[D+1];
        for(int i = 0; i <= D; i++){
            DP[i] = i;
        }

        ArrayList<ShortPath> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list.add(new ShortPath(a, b, c));
        }
        Collections.sort(list, (s1, s2) -> {
            if(s1.start == s2.start){
                return s1.end - s2.end;
            }
            return s1.start - s2.start;
        });
        for(int i = 0; i <= D; i++){
            DP[i] = i;
        }
        for(int i = 0; i < N; i++){
            ShortPath now = list.get(i);
            if(now.start > D || now.end > D) continue;
            if(DP[now.end] > DP[now.start] + now.length){
                DP[now.end] = DP[now.start] + now.length;
                for(int j = now.end + 1; j <= D; j++){
                    DP[j] = Math.min(DP[j], DP[j-1] + 1);
                }
            }
        }
        bw.write(Integer.toString(DP[D]));
        bw.flush();
    }
}
class ShortPath{
    int start;
    int end;
    int length;
    ShortPath(int start, int end, int length){
        this.start = start;
        this.end = end;
        this.length = length;
    }
}
