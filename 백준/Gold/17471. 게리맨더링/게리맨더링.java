import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] population;
    static int N, min = 987654321;
    static ArrayList<Integer> left, right;
    static boolean[] city;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        population = new int[N+1];
        city = new boolean[N+1];
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            population[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for(int j = 0; j < size; j++){
                int next = Integer.parseInt(st.nextToken());
                graph.get(i).add(next);
            }
        }
        // 두개의 집합 왼쪽 1 ... N-1개 / 오른쪽 N-1 ... 1개
        for(int i = 1; i <= N-1; i++){
            make(1, 0, i);
        }
        if(min == 987654321){
            bw.write("-1");
        }else{
            bw.write(Integer.toString(min));
        }
        bw.flush();

    }
    // 집합 만들기, depth는 left에 들어간 크기 - 1
    static void make(int start, int depth, int maxSize){
        if(depth >= maxSize) {
            // left에 maxSize만큼 분류 완료
            // right 분류
            left = new ArrayList<>();
            right = new ArrayList<>();
            for(int i = 1; i <= N; i++){
                if(city[i]){
                    left.add(i);
                }else{
                    right.add(i);
                }
            }

            // left, right가 각각 이어져있으면 값 계산
            if(connected()){
                // left 값
                int leftResult = 0;
                for(int i = 0; i < left.size(); i++){
                    leftResult += population[left.get(i)];
                }

                // right 값
                int rightResult = 0;
                for(int i = 0; i < right.size(); i++){
                    rightResult += population[right.get(i)];
                }

                min = Math.min(min, Math.abs(leftResult - rightResult));
            }
            return;
        }
        for(int i = start; i <= N; i++){
            city[i] = true;
            make(i + 1, depth + 1, maxSize);
            city[i] = false;
        }
    }
    static boolean containLeft(int x){
        for(int i = 0; i < left.size(); i++){
            if(left.get(i) == x) return true;
        }
        return false;
    }

    static boolean containRight(int x){
        for(int i = 0; i < right.size(); i++){
            if(right.get(i) == x) return true;
        }
        return false;
    }

    static boolean connected(){
        Queue<Integer> qLeft = new LinkedList<>();
        qLeft.add(left.get(0));

        Queue<Integer> qRight = new LinkedList<>();
        qRight.add(right.get(0));

        boolean[] visited = new boolean[N+1];

        while(!qLeft.isEmpty()){
            int now = qLeft.remove();
            visited[now] = true;
            for(int i = 0; i < graph.get(now).size(); i++){
                int next = graph.get(now).get(i);
                if(containLeft(next) && !visited[next]){
                    qLeft.add(next);
                }
            }
        }

        while(!qRight.isEmpty()){
            int now = qRight.remove();
            visited[now] = true;
            for(int i = 0; i < graph.get(now).size(); i++){
                int next = graph.get(now).get(i);
                if(containRight(next) && !visited[next]){
                    qRight.add(next);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            if(!visited[i]) return false;
        }
        return true;
    }
}
