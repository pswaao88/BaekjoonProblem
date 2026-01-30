import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            char[] s = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());
            HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
            for(int i = 0; i < s.length; i++){
                char now = s[i];
                ArrayList<Integer> nowList = map.getOrDefault(now, new ArrayList<>());
                nowList.add(i);
                map.put(now, nowList);
            }
            int min = 987654321;
            int max = -1;

            for(Map.Entry<Character, ArrayList<Integer>> entry : map.entrySet()){
                ArrayList<Integer> now = entry.getValue();
                if(now.size() < K) continue;
                for(int j = 0; j <= now.size() - K; j++){
                    int left = j;
                    int right = left + K - 1;
                    min = Math.min(min, now.get(right) - now.get(left) + 1);
                    max = Math.max(max, now.get(right) - now.get(left) + 1);
                }
            }
            if(min == 987654321){
                sb.append("-1\n");
            }else{
                sb.append(min).append(" ").append(max).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }

}
