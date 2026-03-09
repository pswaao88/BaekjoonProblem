import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 추천수 저장 배열
        int[] recommends = new int[101];
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 0; i < T; i++){
            int now = Integer.parseInt(st.nextToken());
            // 아직 후보가 아님
            if(recommends[now] == 0){
                // N개인지 확인
                // 가장 작은 학생 찾기
                if(students.size() == N){
                    int minRecommend = 987654321;
                    int minTime = 987654321;
                    int minIndex = 0;
                    for(int j = 0; j < students.size(); j++){
                        Student nowStudent = students.get(j);
                        if(minRecommend > recommends[nowStudent.number]){
                            minRecommend = recommends[nowStudent.number];
                            minTime = nowStudent.time;
                            minIndex = j;
                        }else if(minRecommend == recommends[nowStudent.number] && minTime > nowStudent.time){
                            minTime = nowStudent.time;
                            minIndex = j;
                        }
                    }
                    recommends[students.get(minIndex).number] = 0;
                    students.remove(minIndex);
                }
                students.add(new Student(now, i));
                recommends[now] = 1;
            }else{
                recommends[now]++;
            }
        }
        Collections.sort(students, (s1, s2) -> s1.number - s2.number);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < students.size(); i++){
            sb.append(students.get(i).number).append(" ");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
class Student{
    int number;
    int time;
    Student(int number, int time){
        this.number = number;
        this.time = time;
    }
}
