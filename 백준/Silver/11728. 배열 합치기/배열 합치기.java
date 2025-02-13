import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[M];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            B[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        int right = 0;

        StringBuilder sb = new StringBuilder();
        while(left < N || right < M){
            if(left == N){
                sb.append(B[right]);
                sb.append(" ");
                right++;
            }else if(right == M){
                sb.append(A[left]);
                sb.append(" ");
                left++;
            }else{
                if(A[left] <= B[right]){
                    sb.append(A[left]);
                    sb.append(" ");
                    left++;
                }else{
                    sb.append(B[right]);
                    sb.append(" ");
                    right++;
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
