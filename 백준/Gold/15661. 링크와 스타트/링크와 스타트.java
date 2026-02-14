import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int count = (1 << N);
        int[][] score = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < count; i++){
            // 0이면 팀 A 1이면 팀 B
            ArrayList<Integer> teamA = new ArrayList<>();
            ArrayList<Integer> teamB = new ArrayList<>();
            int teamAScore = 0;
            int teamBScore = 0;
            for(int j = 0; j < N; j++){
                // 팀 B인경우
                if((i & (1 << j)) != 0){
                    teamB.add(j);
                }else{
                    teamA.add(j);
                }
            }
            if(teamA.size() > 1){
                for(int first = 0; first < teamA.size(); first++){
                    for(int second = first + 1; second < teamA.size(); second++){
                        int firstMember = teamA.get(first);
                        int secondMember = teamA.get(second);
                        teamAScore += score[firstMember][secondMember];
                        teamAScore += score[secondMember][firstMember];
                    }
                }
            }
            if(teamB.size() > 1){
                for(int first = 0; first < teamB.size(); first++){
                    for(int second = first + 1; second < teamB.size(); second++){
                        int firstMember = teamB.get(first);
                        int secondMember = teamB.get(second);
                        teamBScore += score[firstMember][secondMember];
                        teamBScore += score[secondMember][firstMember];
                    }
                }
            }
            min = Math.min(min, Math.abs(teamAScore - teamBScore));
        }
        bw.write(Integer.toString(min));
        bw.flush();
    }
}
