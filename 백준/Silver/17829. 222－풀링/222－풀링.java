import java.io.*;
import java.util.*;

public class Main {
    static int result;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        polling(matrix);
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static void polling(int[][] target){
        if(target[0].length == 1){
            result = target[0][0];
            return;
        }
        int size = target[0].length;
        int[][] newTarget = new int[size / 2][size / 2];
        int indexX = 0;

        for(int i = 1; i < target[0].length; i+=2){
            int indexY = 0;
            for(int j = 1; j < target[0].length; j+=2){
                int[] sortedNumber = new int[4];
                sortedNumber[0] = target[i-1][j-1];
                sortedNumber[1] = target[i-1][j];
                sortedNumber[2] = target[i][j-1];
                sortedNumber[3] = target[i][j];
                Arrays.sort(sortedNumber);
                // 2번째 숫자 i-1, j-1에 저장
                newTarget[indexX][indexY++] = sortedNumber[2];
            }
            indexX++;
        }
        polling(newTarget);
    }
}
