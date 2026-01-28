import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[] circle = new int[N];
        for(int i = 0; i < N; i++){
            circle[i] = Integer.parseInt(br.readLine());
        }
        Map<Integer, Integer> map = new HashMap<>();
        // 쿠폰 초기화
        map.put(c, 1);
        // 맨처음 k개 초기화
        for(int i = 0; i < k; i++){
            int now = map.getOrDefault(circle[i],0);
            map.put(circle[i], now + 1);
        }
        int max = map.size();
        for(int i = 0; i < N - 1; i++){
            // i번째 삭제 i + k번째 추가
            int before = i;
            int beforeCount = map.get(circle[before]);
            if(beforeCount == 1) {
                map.remove(circle[before]);
            }else{
                map.put(circle[before], beforeCount - 1);
            }

            int after = (i + k ) >= N ? (i + k - N) : i + k;
            int afterCount = map.getOrDefault(circle[after],0);
            map.put(circle[after], afterCount + 1);
            max = Math.max(max, map.size());
        }
        bw.write(Integer.toString(max));
        bw.flush();

    }
}
