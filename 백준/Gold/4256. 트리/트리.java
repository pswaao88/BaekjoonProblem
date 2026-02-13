import java.io.*;
import java.util.*;

public class Main {
    static int n, postIndex;
    static HashMap<Integer, Integer> rootIndex = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    static int[] preOrder, inOrder;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            StringTokenizer st;
            n = Integer.parseInt(br.readLine());
            postIndex = 0;
            
            preOrder = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                preOrder[i] = Integer.parseInt(st.nextToken());
            }

            inOrder = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                inOrder[i] = Integer.parseInt(st.nextToken());
                rootIndex.put(inOrder[i], i);
            }

            findPost(0, n - 1);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    // preOrder 순서 루트/왼쪽/오른쪽
    // inOrder 순서 왼쪽/루트/오른쪽
    // preOrder 에서의 루트를 가지고 inOrder에서 왼쪽 오른쪽으로 나누기 반복
    static void findPost(int start, int end){
        if(start > end) return;

        int nowRoot = preOrder[postIndex++];
        int nowRootIndex = rootIndex.get(nowRoot);
        findPost(start, nowRootIndex - 1);
        findPost(nowRootIndex + 1, end);

        sb.append(nowRoot).append(" ");
    }
}

