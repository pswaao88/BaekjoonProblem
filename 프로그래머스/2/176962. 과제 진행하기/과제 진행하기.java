import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        int answerIndex = 0;
        int n = plans.length;
        String[] answer = new String[n];
        Practice[] practices = new Practice[n];
        
        for(int i = 0; i < n; i++){
            practices[i] = new Practice(plans[i][0], plans[i][1], Integer.parseInt(plans[i][2]));
        }

        // 1. 시작 시간 기준 정렬
        Arrays.sort(practices, (p1, p2) -> p1.time - p2.time);

        Stack<Practice> stop = new Stack<>();
        int currentTime = 0; // 현재 시각을 추적합니다.

        for(int i = 0; i < n; i++){
            Practice now = practices[i];
            currentTime = now.time; // 새 과제 시작 시간으로 갱신

            if(i == n - 1){
                // 마지막 과제는 무조건 끝내기
                answer[answerIndex++] = now.title;
                while(!stop.isEmpty()) answer[answerIndex++] = stop.pop().title;
            } else {
                Practice next = practices[i + 1];
                int availableTime = next.time - currentTime; // 다음 과제까지 남은 여유 시간

                if(now.remainTime > availableTime){
                    // 1. 다 못 끝내는 경우
                    now.remainTime -= availableTime;
                    stop.push(now);
                } else {
                    // 2. 현재 과제 완료
                    answer[answerIndex++] = now.title;
                    int freeTime = availableTime - now.remainTime; // 숙제 끝내고 남은 '진짜' 자유 시간
                    
                    // 3. 남은 자유 시간 동안 멈춘 과제들 해결
                    while(!stop.isEmpty() && freeTime > 0){
                        Practice paused = stop.pop();
                        if(paused.remainTime <= freeTime){
                            // 멈췄던 것도 끝냄
                            freeTime -= paused.remainTime;
                            answer[answerIndex++] = paused.title;
                        } else {
                            // 또 하다가 멈춤
                            paused.remainTime -= freeTime;
                            stop.push(paused);
                            freeTime = 0; // 자유 시간 끝
                        }
                    }
                }
            }
        }
        return answer;
    }
}

class Practice{
    String title;
    int time;
    int remainTime;
    Practice(String title, String startTime, int remainTime){
        this.title = title;
        String[] split = startTime.split(":");
        this.time = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        this.remainTime = remainTime;
    }
}