import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[][] map;
    static ArrayList<int[]> home = new ArrayList<>();
    static ArrayList<int[]> chicken = new ArrayList<>();
    static boolean visit[];
    static int min = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                addList(i, j, map[i][j]);
            }
        }
        visit = new boolean[chicken.size()];
        findMin(0, 0);
        bw.write(Integer.toString(min));
        bw.flush();
        bw.close();
        br.close();

    }
    static void addList(int x, int y, int chickenOrHome){
        if(chickenOrHome == 1){
            home.add(new int[] {x,y});
            return;
        }
        if(chickenOrHome == 2){
            chicken.add(new int[] {x,y});
        }
    }
    static void findMin(int depth, int start){
        if(depth == M){ // 치킨집의 개수가 M개일 때
            int result = 0;
            for(int i = 0; i < home.size(); i++){
                int homeChickenDistance = 987654321;
                int[] nowHome = home.get(i);
                for(int j = 0; j < chicken.size(); j++){
                    int[] nowChicken = chicken.get(j);
                    if(visit[j]){
                        homeChickenDistance = Math.min((Math.abs(nowHome[0] - nowChicken[0]) + Math.abs(nowHome[1] - nowChicken[1])), homeChickenDistance);
                    }
                }
                result += homeChickenDistance;
            }
            min = Math.min(min, result);
            return;
        }
        // 백 트레킹
        for(int i = start; i < chicken.size(); i++){
            visit[i] = true;
            findMin(depth + 1, i + 1);
            visit[i] = false;
        }
    }


}
