import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Node root;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            int[] preOrder = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                preOrder[i] = Integer.parseInt(st.nextToken());
            }
            int[] inOrder = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                inOrder[i] = Integer.parseInt(st.nextToken());
            }
            findTree(preOrder, inOrder);
            postOrder(root);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
    // preOrder 순서 루트/왼쪽/오른쪽
    // inOrder 순서 왼쪽/루트/오른쪽
    // preOrder 에서의 루트를 가지고 inOrder에서 왼쪽 오른쪽으로 나누기 반복
    static Node findTree(int[] preOrder, int[] inOrder){
        if(preOrder.length == 0){
            return null;
        }

        if(preOrder.length == 1){
            return new Node(null, null, preOrder[0]);
        }

        Node now = new Node(null, null, preOrder[0]);
        if(preOrder.length == n){
            root = now;
        }

        int rootValue = preOrder[0];
        int rootIndex = 0;
        for(int i = 0; i < inOrder.length; i++){
            if(rootValue == inOrder[i]){
                rootIndex = i;
                break;
            }
        }
        int leftSize = rootIndex;
        int rightSize = preOrder.length - rootIndex - 1;

        int[] leftPreOrder = new int[leftSize];
        int[] leftInOrder = new int[leftSize];

        int[] rightPreOrder = new int[rightSize];
        int[] rightInOrder = new int[rightSize];

        int leftPreOrderIndex = 0;
        int leftInOrderIndex = 0;
        for(int i = 1; i <= leftSize; i++, leftPreOrderIndex++){
            leftPreOrder[leftPreOrderIndex] = preOrder[i];
        }

        for(int i = 0; i < rootIndex; i++, leftInOrderIndex++){
            leftInOrder[leftInOrderIndex] = inOrder[i];
        }

        int rightPreOrderIndex = 0;
        int rightInOrderIndex = 0;
        for(int i = leftSize + 1; i < preOrder.length; i++, rightPreOrderIndex++){
            rightPreOrder[rightPreOrderIndex] = preOrder[i];
        }

        for(int i = rootIndex + 1; i < inOrder.length; i++, rightInOrderIndex++){
            rightInOrder[rightInOrderIndex] = inOrder[i];
        }

        now.left = findTree(leftPreOrder, leftInOrder);
        now.right = findTree(rightPreOrder, rightInOrder);
        return now;
    }
    static void postOrder(Node now){
        if(now.left != null){
            postOrder(now.left);
        }
        if(now.right != null){
            postOrder(now.right);
        }
        sb.append(now.value).append(" ");
    }
}
class Node{
    Node left;
    Node right;
    int value;
    Node(Node left, Node right, int value){
        this.left = left;
        this.right = right;
        this.value = value;
    }
}
