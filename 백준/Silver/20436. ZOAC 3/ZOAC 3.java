import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Character, Coordinate> left = new HashMap<>();
    static HashMap<Character, Coordinate> right = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        makeLeft();
        makeRight();

        Coordinate leftHand = left.get(st.nextToken().charAt(0));
        Coordinate rightHand = right.get(st.nextToken().charAt(0));

        String s = br.readLine();
        int time = 0;
        for(int i = 0; i < s.length(); i++){
            char now = s.charAt(i);
            if(left.containsKey(now)){
                Coordinate nowHand = left.get(now);
                time += Math.abs(leftHand.x - nowHand.x);
                time += Math.abs(leftHand.y - nowHand.y);
                leftHand = nowHand;
            }else{
                Coordinate nowHand = right.get(now);
                time += Math.abs(rightHand.x - nowHand.x);
                time += Math.abs(rightHand.y - nowHand.y);
                rightHand = nowHand;
            }
            // 누르기
            time++;
        }
        bw.write(Integer.toString(time));
        bw.flush();

    }
    static void makeLeft(){
        left.put('q', new Coordinate(0, 0));
        left.put('w', new Coordinate(0, 1));
        left.put('e', new Coordinate(0, 2));
        left.put('r', new Coordinate(0, 3));
        left.put('t', new Coordinate(0, 4));
        left.put('a', new Coordinate(1, 0));
        left.put('s', new Coordinate(1, 1));
        left.put('d', new Coordinate(1, 2));
        left.put('f', new Coordinate(1, 3));
        left.put('g', new Coordinate(1, 4));
        left.put('z', new Coordinate(2, 0));
        left.put('x', new Coordinate(2, 1));
        left.put('c', new Coordinate(2, 2));
        left.put('v', new Coordinate(2, 3));
    }
    static void makeRight(){
        right.put('y', new Coordinate(0, 1));
        right.put('u', new Coordinate(0, 2));
        right.put('i', new Coordinate(0, 3));
        right.put('o', new Coordinate(0, 4));
        right.put('p', new Coordinate(0, 5));
        right.put('h', new Coordinate(1, 1));
        right.put('j', new Coordinate(1, 2));
        right.put('k', new Coordinate(1, 3));
        right.put('l', new Coordinate(1, 4));
        right.put('b', new Coordinate(2, 0));
        right.put('n', new Coordinate(2, 1));
        right.put('m', new Coordinate(2, 2));
    }
}
class Coordinate{
    int x;
    int y;
    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}
