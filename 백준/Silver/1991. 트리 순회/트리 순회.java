import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
class Node{
	char alpha;
	Node left;
	Node right;
	public Node(char alpha) {
		this.alpha = alpha;
		this.left = null;
		this.right = null;
	}
}
public class Main {
	
	public static void preOrder(Node node) {
		if(node == null) return;
		System.out.print(node.alpha);
		preOrder(node.left);
		preOrder(node.right);
	}
	public static void inOrder(Node node) {
		if(node == null) return;
		inOrder(node.left);
		System.out.print(node.alpha);
		inOrder(node.right);
	}
	public static void postOrder(Node node) {
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.alpha);
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		Node[] tree = new Node[26];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			if(tree[root-'A'] == null) {
				tree[root-'A'] = new Node(root);
			}
			if(left != '.') {
				tree[left-'A'] = new Node(left);
				tree[root-'A'].left = tree[left-'A'];
			}
			if(right != '.') {
				tree[right-'A'] = new Node(right);
				tree[root-'A'].right = tree[right-'A'];
			}
		}
		preOrder(tree[0]);
		System.out.println();
		inOrder(tree[0]);
		System.out.println();
		postOrder(tree[0]);
		System.out.println();
	}

}
