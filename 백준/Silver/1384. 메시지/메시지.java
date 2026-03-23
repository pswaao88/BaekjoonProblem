import java.io.*;
import java.util.*;

public class Main {
    static final String word = "was nasty";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s;
        StringBuilder sb = new StringBuilder();
        int group = 1;
        while(!(s = br.readLine()).equals("0")){
            sb.append("Group ").append(group).append("\n");
            group++;
            int N = Integer.parseInt(s);
            Person[] people = new Person[N];
            StringTokenizer st;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                boolean[] friends = new boolean[N - 1];
                for(int j = 0; j < N - 1; j++){
                    String yes = st.nextToken();
                    friends[j] = yes.equals("P");
                }
                people[i] = new Person(name, i, friends);
            }
            int count = 0;
            for(int i = 0; i < N; i++){
                Person now = people[i];
                for(int j = 0; j < N - 1; j++){
                    if(!now.friends[j]){
                        int nextIndex = now.calIndex(j, N);
                        sb.append(people[nextIndex].name).append(" ").append(word).append(" about ").append(now.name).append("\n");
                        count++;
                    }
                }
            }
            if(count == 0){
                sb.append("Nobody ").append(word).append("\n");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
class Person{
    String name;
    int index;
    boolean[] friends;
    Person(String name, int index, boolean[] friends){
        this.name = name;
        this.index = index;
        this.friends = friends;
    }
    // index가 들어감
    int calIndex(int nowIndex, int N){
        int result = this.index;
        result -= (nowIndex + 1);
        // 음수 일때 인덱스 처리
        if(result < 0){
            result = N + result;
        }
        return result;
    }
}
