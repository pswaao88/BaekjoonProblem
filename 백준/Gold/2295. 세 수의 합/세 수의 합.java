import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(number);

        HashMap<Integer, ArrayList<ZK>> map = new HashMap<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int now = number[j] - number[i];
                ArrayList<ZK> nowList = map.getOrDefault(now, new ArrayList<>());
                nowList.add(new ZK(number[i], number[j]));
                map.put(now, nowList);
            }
        }
        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int now = number[j] + number[i];
                if(map.containsKey(now)){
                    ArrayList<ZK> nowList = map.get(now);
                    for(int k = 0; k < nowList.size();k++) {
                        ZK result = nowList.get(k);
                        max = Math.max(max, result.k);
                    }
                }
            }
        }
        bw.write(Integer.toString(max));
        bw.flush();
    }
}
class ZK{
    int z;
    int k;
    ZK(int z, int k){
        this.z = z;
        this.k = k;
    }
}
