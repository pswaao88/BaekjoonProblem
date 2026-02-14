import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int count = (1 << N) / 2;
        int[][] score = new int[N][N];
        int[][] sumScore = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sumScore[i][j] = score[i][j] + score[j][i];
            }
        }

        int[] teamA = new int[N];
        int[] teamB = new int[N];

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < count; i++){
            // 0이면 팀 A 1이면 팀 B
            int teamAScore = 0;
            int teamBScore = 0;
            int teamAIndex = 0;
            int teamBIndex = 0;
            for(int j = 0; j < N; j++){
                // 팀 B인경우
                if((i & (1 << j)) != 0){
                    teamB[teamBIndex++] = j;
                }else{
                    teamA[teamAIndex++] = j;
                }
            }
            if(teamAIndex > 1){
                for(int first = 0; first < teamAIndex; first++){
                    for(int second = first + 1; second < teamAIndex; second++){
                        int firstMember = teamA[first];
                        int secondMember = teamA[second];
                        teamAScore += score[firstMember][secondMember];
                        teamAScore += score[secondMember][firstMember];
                    }
                }
            }
            if(teamBIndex > 1){
                for(int first = 0; first < teamBIndex; first++){
                    for(int second = first + 1; second < teamBIndex; second++){
                        int firstMember = teamB[first];
                        int secondMember = teamB[second];
                        teamBScore += score[firstMember][secondMember];
                        teamBScore += score[secondMember][firstMember];
                    }
                }
            }
            min = Math.min(min, Math.abs(teamAScore - teamBScore));
            if(min == 0){
                break;
            }
        }
        bw.write(Integer.toString(min));
        bw.flush();
    }
}
