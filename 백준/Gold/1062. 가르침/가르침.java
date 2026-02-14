import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int max = 0;
    static int wordList = 0;
    static int[] words;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new int[N];
        for(int i = 0; i < N; i++){
            String word = br.readLine();
            int nowWord = 0;
            // 단어에 대해서 비트 만들기 z ~ a ex) 글자 a 라면 000..0001
            // 모든 글자에 대해서 or 연산
            for(int j = 0; j < word.length(); j++){
                int now = word.charAt(j) - 'a';
                nowWord = nowWord | (1 << now);
            }
            words[i] = nowWord;
        }
        makeWordList(0, 0);
        bw.write(Integer.toString(max));
        bw.flush();
    }
    static void makeWordList(int start, int depth){
        // 5개 보다 작으면 필수인 a, c, i, n, t 포함 못시킴
        // 필수 5개 체크
        if(depth == 0){
            wordList += 1 << ('a' - 'a');
            wordList += 1 << ('c' - 'a');
            wordList += 1 << ('i' - 'a');
            wordList += 1 << ('n' - 'a');
            wordList += 1 << ('t' - 'a');
        }
        if(K < 5){
            return;
        }
        if(depth >= K - 5){
            // 가능한 글자수 이므로
            // 모든 글자 와 체크
            int result = 0;
            for(int i = 0; i < N; i++){
                if((words[i] & wordList) == words[i]){
                    result++;
                }
            }
            max = Math.max(max, result);
            return;
        }
        
        for(int i = start; i < 26; i++){
            // list에 없는 경우
            if((wordList & (1 << i)) == 0){
                wordList += (1 << i);
                makeWordList(i + 1, depth + 1);
                wordList -= (1 << i);
            }
        }

    }
}
