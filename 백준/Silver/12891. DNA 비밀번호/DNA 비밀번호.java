import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        char[] DNA = br.readLine().toCharArray();
        st = new StringTokenizer(br.readLine());
        int[] answer = new int[4];
        for(int i = 0; i < 4; i++){
            answer[i] = Integer.parseInt(st.nextToken());
        }
        // 초기화 후 한번 검사
        int result = 0;
        int[] ACGT = new int[4];
        for(int i = 0; i < P; i++){
            switch(DNA[i]){
                case 'A':
                    ACGT[0]++;
                    break;
                case 'C':
                    ACGT[1]++;
                    break;
                case 'G':
                    ACGT[2]++;
                    break;
                case 'T':
                    ACGT[3]++;
                    break;
            }
        }
        result += yes(ACGT, answer);

        for(int i = 0; i < S - P; i++){
            //i번째 지우고 i + p 번째 추가
            char before = DNA[i];
            char after = DNA[i + P];
            switch(before){
                case 'A':
                    ACGT[0]--;
                    break;
                case 'C':
                    ACGT[1]--;
                    break;
                case 'G':
                    ACGT[2]--;
                    break;
                case 'T':
                    ACGT[3]--;
                    break;
            }
            switch(after){
                case 'A':
                    ACGT[0]++;
                    break;
                case 'C':
                    ACGT[1]++;
                    break;
                case 'G':
                    ACGT[2]++;
                    break;
                case 'T':
                    ACGT[3]++;
                    break;
            }
            result += yes(ACGT, answer);
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static int yes(int[] ACGT, int[] answer){
        if(ACGT[0] < answer[0]) return 0;
        if(ACGT[1] < answer[1]) return 0;
        if(ACGT[2] < answer[2]) return 0;
        if(ACGT[3] < answer[3]) return 0;
        return 1;
    }

}
