import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st. nextToken());
        int m = Integer.parseInt(st. nextToken());

        // 기본 초기화
        parent = new int[n+1];
        for(int i = 0; i <= n; i++){
            parent[i] = i;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a == 0){ // 합집합
                if(b == c){ // 같으면 그냥 끝
                    continue;
                }

                if(b > c){ // 순서 지키기 위해서 경로압축시에 작은거로 합치기 위함
                    int tmp = b;
                    b = c;
                    c = tmp;
                }
                union(b, c);
            }else{ // 같은 집합인지 확인

                if(b == c){
                    sb.append("YES\n");
                }else{
                    int parentB = find(b);
                    int parentC = find(c);
                    if(parentB == parentC){
                        sb.append("YES\n");
                    }else{
                        sb.append("NO\n");
                    }
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
    static void union(int a, int b){
        int parentA = find(a); // a의 부모확인
        int parentB = find(b); // b의 부모확인
        // 같은 집합이면 어차피 같음
        // 다른 집합이라면 작은 쪽으로 합쳐서 합집합 형성
        parent[parentA] = parentB;
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        return parent[x] = find(parent[x]);
    }

}
