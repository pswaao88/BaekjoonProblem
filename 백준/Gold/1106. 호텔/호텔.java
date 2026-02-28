import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        City[] cities = new City[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            cities[i] = new City(a, b);
        }
        // 최대 비용은 고객 1명에 비용 100이고 C가 1000이면 100000이므로 비용을 기준으롷 선택
        // DP[i]는 비용이 i일떄 최대 고객수
        int[] DP = new int[100001];
        for(int now = 1; now <= 100000; now++){
            for(int cityNum = 0; cityNum < N; cityNum++){
                City nowCity = cities[cityNum];
                int next = now - nowCity.cost;
                if(next >= 0){
                    DP[now] = Math.max(DP[now], DP[next] + nowCity.customer);
                }
            }
        }
        int result = 0;
        for(int i = 1; i <= 100000; i++){
            if(DP[i] >= C){
                result = i;
                break;
            }
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
}
class City{
    int cost;
    int customer;
    City(int cost, int customer){
        this.cost = cost;
        this.customer = customer;
    }
}
