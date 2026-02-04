import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;
        BinaryNode before = null;
        BinaryNode now = null;
        BinaryNode root = null;
        while ((s = br.readLine()) != null) {
            int value = Integer.parseInt(s);
            if (before == null) {
                now = new BinaryNode(value, null, null, null);
                root = now;
                before = now;
            } else {
                now = new BinaryNode(value, null, null, null);
                if (before.value > value) {
                    before.left = now;
                    now.parent = before;
                } else {
                    BinaryNode target = root;
                    while(true){
                        if(target.value > value){
                            if(target.left == null) break;
                            target = target.left;
                        }else{
                            if(target.right == null) break;
                            target = target.right;
                        }
                    }
                    target.right = now;
                    now.parent = target;
                }
                before = now;

            }
        }

        postorder(root);
        bw.write(sb.toString());
        bw.flush();
    }
    static void postorder(BinaryNode now){
        if(now == null) return;
        postorder(now.left);
        postorder(now.right);
        sb.append(now.value).append("\n");
    }
}
class BinaryNode{
    int value;
    BinaryNode parent;
    BinaryNode left;
    BinaryNode right;
    BinaryNode(int value, BinaryNode parent, BinaryNode left, BinaryNode right ){
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }
}
