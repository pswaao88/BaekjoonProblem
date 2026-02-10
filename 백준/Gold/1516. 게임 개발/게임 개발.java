import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> buildings = new ArrayList<>();
    static int[] time;
    static int[] totalTime;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i <= N; i++){
            buildings.add(new ArrayList<>());
        }
        time = new int[N+1];
        totalTime = new int[N+1];

        StringTokenizer st;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> now = buildings.get(i);
            time[i] = Integer.parseInt(st.nextToken());
            int beforeBuilding;
            while((beforeBuilding = Integer.parseInt(st.nextToken())) != -1){
                now.add(beforeBuilding);
            }
        }
        for(int i = 1; i <= N; i++){
            if(buildings.get(i).size() == 0){
                totalTime[i] = time[i];
            }
        }
        while(true){
            boolean canBreak = true;
            for(int i = 1; i <= N; i++){
                // 지을 수 있을 때
                if(canBuild(i)){
                    totalTime[i] = time[i] + findMaxTime(i);
                    canBreak = false;
                }
            }
            if(canBreak) break;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++){
            sb.append(totalTime[i]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static int findMaxTime(int nowBuilding){
        int max = 0;
        for(int i = 0; i < buildings.get(nowBuilding).size(); i++){
            max = Math.max(max, totalTime[buildings.get(nowBuilding).get(i)]);
        }
        return max;
    }
    static boolean canBuild(int nowBuilding){
        if(totalTime[nowBuilding] != 0) return false;
        for(int i = 0; i < buildings.get(nowBuilding).size(); i++){
            // 아직 안지어진거임
            if(totalTime[buildings.get(nowBuilding).get(i)] == 0) return false;
        }
        return true;
    }
}
