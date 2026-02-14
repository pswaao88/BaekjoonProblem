import java.io.*;
import java.util.*;

public class Main {
    static Ingredient[] all;
    static int min = Integer.MAX_VALUE;
    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        all = new Ingredient[N];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            all[i] = new Ingredient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        find(0, 1, 0);
        bw.write(Integer.toString(min));
        bw.flush();
    }
    static void find(int index, int nowS, int nowB){
        if(index >= N){
            return;
        }
        for(int i = index; i < N; i++){
            Ingredient now = all[i];
            nowS *= now.S;
            nowB += now.B;
            min = Math.min(min, Math.abs(nowS - nowB));
            find(i + 1, nowS, nowB);
            nowS /= now.S;
            nowB -= now.B;
        }
    }
}
class Ingredient{
    int S;
    int B;
    Ingredient(int S, int B){
        this.S = S;
        this.B = B;
    }
}
