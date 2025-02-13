import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	public static void xToY(int n , int x, int y) {
		//n은 x => y 1~n-1은 x => z로 이동
		int z;
		if((x == 1 && y == 2) || (x == 2 && y == 1)) {
			z = 3;
		}else if((x == 1 && y == 3) || (x == 3 && y == 1)) {
			z = 2;
		}else {
			z = 1;
		}
		if(n == 1) {
			sb.append(x);
			sb.append(" ");
			sb.append(y);
			sb.append("\n");
			cnt++;
			return;
		}else {
			xToY(n-1, x, z);
			sb.append(x);
			sb.append(" ");
			sb.append(y);
			sb.append("\n");
			cnt++;
			xToY(n-1,z,y);
		}
		
		
		
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		xToY(N,1,3);
		bw.write(Integer.toString(cnt)+"\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
