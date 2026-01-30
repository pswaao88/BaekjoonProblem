import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        ArrayList<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
            list.add(number[i]);
        }
        Collections.sort(list);
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for(int i = 0; i < N; i++){
            if(i == 0){
                map.put(list.get(0), index);
            }else{
                if(list.get(i-1) < list.get(i)){
                    index++;
                    map.put(list.get(i), index);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            int now = number[i];
            sb.append(map.get(now)).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();



    }
}
