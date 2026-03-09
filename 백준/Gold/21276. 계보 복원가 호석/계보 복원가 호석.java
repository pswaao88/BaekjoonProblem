import java.io.*;
import java.util.*;

public class Main {
    static int[] indegree;
    static int[] count;
    static HashMap<String, Integer> indexMap = new HashMap<>();
    static String[] member;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static ArrayList<String> root = new ArrayList<>();
    static int N, M;
    static StringBuilder sb;
    static HashMap<String, String> stringMap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        // 시조를 찾기 위함 => 0이면 시조
        indegree = new int[N];
        count = new int[N];
        member = new String[N];
        int index = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            String now = st.nextToken();
            member[i] = now;
            indexMap.put(now, index++);
        }
        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            // 후손
            String s1 = st.nextToken();
            // 조상
            String s2 = st.nextToken();
            int s1Index = indexMap.get(s1);
            int s2Index = indexMap.get(s2);
            // 후손에 indegree 증가
            indegree[s1Index]++;
            // 조상 -> 후손 방향 그래프 추가
            graph.get(s2Index).add(s1Index);
        }

        for(int i = 0; i < N; i++){
            if(indegree[i] == 0){
                // root 추가
                root.add(member[i]);
                // 출력할 문자열 만들기
                makeString(i);
            }
        }
        sb = new StringBuilder();

        //root 수 추가
        Collections.sort(root);
        sb.append(root.size()).append(("\n"));
        // root 추가
        for(int i = 0; i < root.size(); i++){
            sb.append(root.get(i)).append(" ");
        }
        sb.append("\n");
        Arrays.sort(member);
        for(int i = 0; i < N; i++){
            sb.append(stringMap.get(member[i])).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    static void makeString(int now){
        ArrayList<Integer> childNumList = new ArrayList<>();
        ArrayList<String> childNameList = new ArrayList<>();
        for(int i = 0; i < graph.get(now).size(); i++){
            int childNum = graph.get(now).get(i);
            if(indegree[now] == indegree[childNum] - 1){
                childNumList.add(childNum);
                childNameList.add(member[childNum]);
            }
        }
        sb = new StringBuilder();
        sb.append(member[now]).append(" ").append(childNumList.size()).append(" ");
        Collections.sort(childNameList);
        for(String s : childNameList){
            sb.append(s).append(" ");
        }
        stringMap.put(member[now], sb.toString());
        for(int x : childNumList){
            makeString(x);
        }
    }
}
