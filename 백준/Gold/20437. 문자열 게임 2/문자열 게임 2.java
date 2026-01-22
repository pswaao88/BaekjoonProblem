import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++){
            char[] W = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());
            HashMap<Character, List<Integer>> map = new HashMap<>();
            for(int i = 0; i < W.length; i++){
                List<Integer> now = map.getOrDefault(W[i], new ArrayList<>());
                now.add(i);
                map.put(W[i],now);
            }
            int min = 987654321;
            int max = -1;
            for(int i = 0; i < 26; i++){
                char now = (char)('a' + i);
                List<Integer> nowList = map.getOrDefault(now, new ArrayList<>());
                if(nowList.size() < K) continue;
                for(int j = 0; j < nowList.size() - K + 1; j++){
                    int left = j;
                    int right = j + K - 1;
                    int nowLength = nowList.get(right) - nowList.get(left) + 1;
                    min = Math.min(min, nowLength);
                    max = Math.max(max, nowLength);
                }
            }
            if(min != 987654321){
                sb.append(min).append(" ").append(max).append("\n");
            }else{
                sb.append(-1).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
