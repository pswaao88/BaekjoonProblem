import java.io.*;
import java.util.*;

public class Main {
    static int[][] matrix;
    static int N;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        matrix = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }
        int[][] result = make(B);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static int[][] make(long B){
        if(B == 1){
            return matrix;
        }
        long left;
        long right;
        if(B % 2 == 0){
            left = B / 2;
            int[][] next = make(left);
            return square(next, next);
        }else{
            left = B / 2;
            int[][] nextLeft = make(left);
            int[][] nextRight = square(nextLeft, matrix);
            return square(nextLeft, nextRight);
        }

    }
    static int[][] square(int[][] left, int[][] right){
        int[][] newMatrix = new int[N][N];
        for(int x = 0; x < N; x++){
            int[] row = left[x];
            for(int y = 0; y < N; y++){
                int[] col = makeCol(right, y);
                int result = 0;
                for(int i = 0; i < N; i++){
                    result += (row[i] * col[i]) % 1000;
                    result %= 1000;
                }
                newMatrix[x][y] = result;
            }
        }
        return newMatrix;
    }
    static int[] makeCol(int[][] right, int index){
        int[] col = new int[N];
        for(int x = 0; x < N; x++){
            col[x] = right[x][index];
        }
        return col;
    }
}
