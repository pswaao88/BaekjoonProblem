import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static boolean[] start;
    static int N;
    static final int MAX = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        int[] DP = new int[(1 << N)];
        Arrays.fill(DP, MAX);
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작할 연구소 전부 기록
        int start = 0;
        String s = br.readLine();
        for(int i = 0; i < N; i++){
            if(s.charAt(i) == 'Y'){
                start = start | (1 << i);
            }
        }
        DP[start] = 0;
        int P = Integer.parseInt(br.readLine());
        int min = MAX;
        if(isOk(start, P)){
            min = 0;
        }else{
            for(int visit = 0; visit < (1 << N); visit++){
                for(int now = 0; now < N; now++){
                    int nowBinary = (1 << now);
                    if((visit & nowBinary) != 0){
                        int beforePath = visit & (~nowBinary);
                        for(int before = 0; before < N; before++){
                            int beforeBinary = (1 << before);
                            if(((beforePath) & beforeBinary) != 0){
                                DP[visit] = Math.min(DP[visit], DP[beforePath] + graph[before][now]);
                            }
                        }
                    }
                }
                if(isOk(visit, P)){
                    min = Math.min(min, DP[visit]);
                }
            }
        }

        // 최소값 찾기
        if(min == MAX) min = -1;
        bw.write(Integer.toString(min));
        bw.flush();

    }
    static boolean isOk(int visit, int P){
        int now = visit;
        int result = 0;
        while(now > 0){
            result += (now & 1);
            now = (now >> 1);
        }
        return result >= P;
    }
    static boolean isVisit(int visit, int target){
        int targetBinary = (1 << target);
        if((visit & targetBinary) != 0) return true;
        return false;
    }
}

