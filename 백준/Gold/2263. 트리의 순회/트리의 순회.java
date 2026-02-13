import java.io.*;
import java.util.*;

public class Main {
    static int[] inOrder, postOrder;
    static StringBuilder sb = new StringBuilder();
    static HashMap<Integer, Integer> inOrderIndex = new HashMap<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        inOrder = new int[n];
        postOrder = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            inOrder[i] = Integer.parseInt(st.nextToken());
            inOrderIndex.put(inOrder[i], i);
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            postOrder[i] = Integer.parseInt(st.nextToken());
        }
        findPreOrder(0, n - 1, 0, n - 1);
        bw.write(sb.toString());
        bw.flush();
    }
    // inOrder 왼쪽 루트 오른쪽
    // postOrder 왼쪽 오른쪽 루트
    // 찾을때 postOrder의 마지막이 항상 루트
    // index를 통해 개수 구해 자르기
    static void findPreOrder(int postStart, int postEnd, int inStart, int inEnd){
        if(postStart > postEnd || inStart > inEnd){
            return;
        }
        // post의 끝이 root
        int nowRoot = postOrder[postEnd];
        // map으로 저장한 index 리턴
        int nowRootIndex = inOrderIndex.get(nowRoot);
        int leftSize = nowRootIndex - inStart;

        // root 방문
        sb.append(nowRoot).append(" ");
        // 왼쪽
        findPreOrder(postStart, postStart + leftSize - 1, inStart, nowRootIndex - 1);
        // 오른쪽
        findPreOrder(postStart + leftSize, postEnd - 1, nowRootIndex + 1, inEnd);
    }
}
