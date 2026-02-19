import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        ArrayList<Matrix> matrixList = new ArrayList<>();
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrixList.add(new Matrix(r, c));
        }
        // DP[i][j] = i ~ j까지 곱했을때 최소값
        // 왜 DP냐? 행렬 곱을 하면 결과가 바뀌기 떄문 3 x 4 4 x 8 => 3 x 8
        int[][] DP = new int[N][N];
        //base case 01 12 ... n-1n-2 끼리 곱할때
        for(int i = 0; i < N - 1; i++){
            Matrix now = matrixList.get(i);
            Matrix next = matrixList.get(i+1);
            DP[i][i+1] = now.row * now.col * next.col;
        }
        // 구간의 수로 DP 돌리기
        for(int i = 3; i <= N; i++){
            for(int j = 0; j <= N - i; j++){
                DP[j][j + i - 1] = Integer.MAX_VALUE;
                for(int k = j + 1; k <= j + i - 1; k++){
                    // left는 j ~ k-1까지 right는 k 부터 j + i - 1까지
                    int now = DP[j][k-1] + DP[k][j + i - 1] + (matrixList.get(j).row * matrixList.get(k-1).col * matrixList.get(j + i - 1).col);
                    DP[j][j + i - 1] = Math.min(now, DP[j][j + i - 1]);
                }
            }
        }
        bw.write(Integer.toString(DP[0][N-1]));
        bw.flush();
    }
}
class Matrix{
    int row;
    int col;
    Matrix(int row, int col){
        this.row = row;
        this.col = col;
    }
}
