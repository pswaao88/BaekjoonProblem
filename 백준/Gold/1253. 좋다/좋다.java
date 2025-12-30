import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] number = new int[N];
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        int result = 0;
        for(int i = 0; i < N; i++){
            // 초기화
            ArrayList<Integer> tmpNumber = new ArrayList<>();
            for(int j = 0; j < N; j++){
                if(j == i) continue;
                tmpNumber.add(number[j]);
            }
            Collections.sort(tmpNumber);
            // 변수 세팅
            int now = number[i];
            int left = 0;
            int right = N-2;
            boolean good = false;
            // 탐색
            while(left < right){
                int sum = tmpNumber.get(left) + tmpNumber.get(right);
                if(sum < now){
                    left++;
                }else if(sum > now){
                    right--;
                }else{
                    good = true;
                    break;
                }
            }
            if(good){
                result++;
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
