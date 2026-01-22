import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Student> young = new PriorityQueue<>((s1, s2) -> {
            if(s1.year == s2.year){
                if(s1.month == s2.month){
                    return Integer.compare(s2.day, s1.day);
                }
                return Integer.compare(s2.month, s1.month);
            }
            return Integer.compare(s2.year, s1.year);
        });

        PriorityQueue<Student> old = new PriorityQueue<>((s1, s2) -> {
            if(s1.year == s2.year){
                if(s1.month == s2.month){
                    return Integer.compare(s1.day, s2.day);
                }
                return Integer.compare(s1.month, s2.month);
            }
            return Integer.compare(s1.year, s2.year);
        });
        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            Student now = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            young.offer(now);
            old.offer(now);
        }
        Student youngStudent = young.poll();
        Student olderStudent = old.poll();
        StringBuilder sb = new StringBuilder();
        sb.append(youngStudent.name).append("\n").append(olderStudent.name);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
class Student{
    String name;
    int day;
    int month;
    int year;
    Student(String name, int day, int month, int year){
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
