import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static Node[] tree;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        tree = new Node[26];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            char b = st.nextToken().charAt(0);
            char c = st.nextToken().charAt(0);
            if(tree[a - 'A'] == null){
                tree[a - 'A'] = new Node(a, null, null);
            }
            if(b != '.'){
                tree[b - 'A'] = new Node(b, null, null);
                tree[a - 'A'].left = tree[b - 'A'];
            }
            if(c != '.'){
                tree[c - 'A'] = new Node(c, null, null);
                tree[a - 'A'].right = tree[c - 'A'];
            }
        }
        preOrder(tree[0]);
        sb.append("\n");
        inorder(tree[0]);
        sb.append("\n");
        postorder(tree[0]);
        sb.append("\n");

        bw.write(sb.toString());
        bw.flush();
    }
    static void preOrder(Node node){
        if(node == null) return;
        sb.append(node.now);
        preOrder(node.left);
        preOrder(node.right);

    }
    static void inorder(Node node){
        if(node == null) return;
        inorder(node.left);
        sb.append(node.now);
        inorder(node.right);


    }
    static void postorder(Node node){
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.now);
    }
}
class Node{
    char now;
    Node left;
    Node right;
    Node(char now, Node left, Node right){
        this.now = now;
        this.left = left;
        this.right = right;
    }
}
