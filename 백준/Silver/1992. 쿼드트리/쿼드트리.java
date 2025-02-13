import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] video;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        video = new int[N][N];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                video[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
        compression(0,0,N);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
    static void compression(int startX, int startY, int size){
        if(size == 1){
            sb.append(video[startX][startY]);
            return;
        }
        int totalZero = countZero(startX, startY, size);
        if(totalZero == size * size){
            sb.append("0");
            return;
        }
        if(totalZero == 0){
            sb.append("1");
            return;
        }
        sb.append("(");
        int compressionSize = size / 2;
        for(int i = 0; i < 2; i++){
            int startCompressionX = startX + compressionSize * i;
            for(int j = 0; j < 2; j++){
                int startCompressionY = startY + compressionSize * j;
                int zero = countZero(startCompressionX, startCompressionY, compressionSize);
                if(zero == compressionSize * compressionSize){
                    sb.append("0");
                }else if(zero == 0){
                    sb.append("1");
                }else {
                    compression(startCompressionX, startCompressionY, compressionSize);
                }
            }
        }
        sb.append(")");
    }
    static int countZero(int startX, int startY, int size){
        int cntZero = 0;

        for(int i = startX; i < startX + size; i++){
            for(int j = startY; j < startY + size; j++){
                if(video[i][j] == 0){
                    cntZero++;
                }
            }
        }
        return cntZero;
    }
}
