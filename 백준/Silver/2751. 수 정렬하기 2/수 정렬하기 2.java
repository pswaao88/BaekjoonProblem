import java.io.*;

public class Main {
    static int[] number;
    static int[] tmp;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        number = new int[N];
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(br.readLine());
        }
        mergeSort(0, N-1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append(number[i]);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
    static void mergeSort(int lo, int hi){
        int size = hi - lo + 1;
        if(size <= 1) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(lo, mid);
        mergeSort(mid + 1, hi);
        tmp = new int[size];
        int left = lo;
        int right = mid + 1;
        for(int i = 0; i < size; i++){
            if(right > hi){
                tmp[i] = number[left];
                left++;
            }else if(left > mid){
                tmp[i] = number[right];
                right++;
            }else{
                if(number[left] <= number[right]){
                    tmp[i] = number[left];
                    left++;
                }else{
                    tmp[i] = number[right];
                    right++;
                }
            }
        }
        for(int i = 0; i < size; i++){
            number[i + lo] = tmp[i];
        }
    }
}
