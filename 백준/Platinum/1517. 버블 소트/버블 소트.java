import java.io.*;
import java.util.*;

public class Main {
    static int[] number;
    static int[] tmp;
    static long count = 0;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        number = new int[N];
        tmp = new int[N];
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }
        mergeSort(0, N-1);
        bw.write(Long.toString(count));
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

        int left = lo;
        int right = mid + 1;
        int index = lo;

        while(left <= mid && right <= hi){
            if(number[left] <= number[right]){
                tmp[index++] = number[left++];
            }else{
                count += right - index;
                tmp[index++] = number[right++];
            }
        }
        while(left <= mid){
            tmp[index++] = number[left++];
        }
        while(right <= hi){
            tmp[index++] = number[right++];
        }
        for(int i = lo; i <= hi; i++){
            number[i] = tmp[i];
        }
    }
}
