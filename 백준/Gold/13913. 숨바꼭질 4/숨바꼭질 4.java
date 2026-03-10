import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        int[] time = new int[100001];
        Arrays.fill(time, -1);
        time[N] = 0;
        int[] parent = new int[100001];
        parent[N] = N;
        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        while(!q.isEmpty()){
            int now = q.remove();
            if(now == K){
                break;
            }
            int x1 = now - 1;
            int x2 = now + 1;
            int x3 = now * 2;
            // 범위 안에 있을 경우
            if(x1 >= 0 && x1 <= 100000){
                // 아직 방문 x
                if(time[x1] < 0){
                    time[x1] = time[now] + 1;
                    parent[x1] = now;
                    q.add(x1);
                }
            }
            if(x2 >= 0 && x2 <= 100000){
                // 아직 방문 x
                if(time[x2] < 0){
                    time[x2] = time[now] + 1;
                    parent[x2] = now;
                    q.add(x2);
                }
            }
            if(x3 >= 0 && x3 <= 100000){
                // 아직 방문 x
                if(time[x3] < 0){
                    time[x3] = time[now] + 1;
                    parent[x3] = now;
                    q.add(x3);
                }
            }
        }
        int index = K;
        Stack<Integer> stack = new Stack<>();
        while(index != N){
            stack.push(index);
            index = parent[index];
        }
        stack.push(N);
        sb.append(time[K]).append("\n");
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
