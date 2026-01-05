import java.io.*;
import java.util.*;

public class Main {
    static Random random = new Random();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        quickSelect(A, 0, N - 1, K - 1);

        bw.write(Integer.toString(A[K - 1]));
        bw.flush();
        br.close();
    }

    static void quickSelect(int[] a, int lo, int hi, int k) {
        if (lo >= hi) return;

        int pivotIndex = lo + random.nextInt(hi - lo + 1);
        swap(a, lo, pivotIndex);
        int pivot = a[lo];

        int lt = lo;     
        int gt = hi;     
        int i = lo + 1;  

        while (i <= gt) {
            if (a[i] < pivot) {
                swap(a, lt, i);
                lt++;
                i++;
            } else if (a[i] > pivot) {
                swap(a, i, gt);
                gt--;
            } else {
                i++;
            }
        }

        if (k < lt) {
            quickSelect(a, lo, lt - 1, k);
        } else if (k > gt) {
            quickSelect(a, gt + 1, hi, k);
        } else {
            return;
        }
    }

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}