import java.io.*;
import java.util.*;

public class Main {
    static int A = 0;
    static int C = 0;
    static int G = 0;
    static int T = 0;
    static int[] dna;
    static int count = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String s = br.readLine();

        st = new StringTokenizer(br.readLine());
        dna = new int[4];
        for(int i = 0; i < 4; i++){
            dna[i] = Integer.parseInt(st.nextToken());
        }

        // 맨처음 초기화
        for(int i = 0; i < P; i++){
            char now = s.charAt(i);
            plusDna(now);
        }
        isOk();
        // left랑 right는 현재 가리키는 index
        for(int i = 1; i < S - P + 1; i++){
            int left = i;
            int right = left + P - 1;
            next(left, right, s);
        }
        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void isOk(){
        if (A >= dna[0] && C >= dna[1] && G >= dna[2] && T >= dna[3]){
            count++;
        }
    }

    private static void next(int left, int right, String target){
        char minus = target.charAt(left-1);
        char plus = target.charAt(right);
        minusDna(minus);
        plusDna(plus);
        isOk();
    }

    private static void minusDna(char targetDna){
        switch(targetDna){
            case 'A':
                A--;
                break;
            case 'C':
                C--;
                break;
            case 'T':
                T--;
                break;
            case 'G':
                G--;
                break;
            default:
                break;
        }
    }

    private static void plusDna(char targetDna){
        switch(targetDna){
            case 'A':
                A++;
                break;
            case 'C':
                C++;
                break;
            case 'T':
                T++;
                break;
            case 'G':
                G++;
                break;
            default:
                break;
        }
    }
}
