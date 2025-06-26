import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int a;
	static int lowerBound(int num[]) {
		int lo = -1;
		int hi = num.length;
		while(lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if(a + num[mid] >= 0) {
				hi = mid;
			}else {
				lo = mid;
			}
		}
		return hi;
	}
	static int upperBound(int[] num) {
		int lo = -1;
		int hi = num.length;
		while(lo + 1 < hi) {
			int mid = (lo + hi) / 2;
			if(a + num[mid] > 0) {
				hi = mid;
			}else {
				lo = mid;
			}
		}
		return hi;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			for(int j = 0; j < 4; j++) {
				if(j == 0) {
					A[i] = Integer.parseInt(st.nextToken());
				}else if(j == 1) {
					B[i] = Integer.parseInt(st.nextToken());
				}else if(j == 2) {
					C[i] = Integer.parseInt(st.nextToken());
				}else {
					D[i] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		int[] sumAB = new int[N*N];
		int[] sumCD = new int[N*N];
		int index = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sumAB[index]= A[i] + B[j];
				sumCD[index]= C[i] + D[j];
				index++;
			}
		}
		Arrays.sort(sumCD);
		long cnt = 0;
		for(int i = 0; i < N*N; i++) {
			a = sumAB[i];
			cnt += upperBound(sumCD) - lowerBound(sumCD);			
		}
		bw.write(Long.toString(cnt));
		bw.flush();
		bw.close();
		br.close();
		
		
	}

}
