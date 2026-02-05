import java.io.*;
import java.util.*;

public class Main {
    static int[][] status;
    static int min = 987654321;
    static int N;
    static boolean[] teamA;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        status = new int[N][N];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                status[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            teamA = new boolean[N];
            teamA[i] = true;
            find(i, 1);
        }
        bw.write(Integer.toString(min));
        bw.flush();
    }
    static void find(int x, int depth){
        if(depth == N/2){
            // 능력치 계산
            int teamAStatus = 0;
            int teamBStatus = 0;
            ArrayList<Integer> listA = new ArrayList<>();
            ArrayList<Integer> listB = new ArrayList<>();
            for(int i = 0; i < N; i++){
                if(teamA[i]){
                    listA.add(i);
                }else{
                    listB.add(i);
                }
            }
            for(int i = 0; i < N / 2; i++){
                int left = listA.get(i);
                for(int j = i + 1; j < N / 2; j++){
                    int right = listA.get(j);
                    teamAStatus += status[left][right];
                    teamAStatus += status[right][left];
                }
            }

            for(int i = 0; i < N / 2; i++){
                int left = listB.get(i);
                for(int j = i + 1; j < N / 2; j++){
                    int right = listB.get(j);
                    teamBStatus += status[left][right];
                    teamBStatus += status[right][left];
                }
            }

            min = Math.min(min, Math.abs(teamAStatus - teamBStatus));
        }

        for(int i = x + 1; i < N; i++){
            teamA[i] = true;
            find(i, depth+1);
            teamA[i] = false;
        }
    }
}
