import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n = progresses.length;
        
        Queue<Progress> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            q.add(new Progress(progresses[i], speeds[i]));
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        for(int index = 0; index < n;){
            Progress now = q.remove();
            int remain = 100 - now.nowProgress;
            int days = remain / now.speed;
            if(remain % speeds[index] != 0){
                days++;
            }
            
            int count = 1;
            int size = q.size();
            int nowSize = 0;
            boolean yes = true;
            while(nowSize < size){
                Progress next = q.remove();
                next.nowProgress += days * next.speed;
                if(next.nowProgress >= 100 && yes){
                    count++;
                }else{
                    yes = false;
                    q.add(next);
                }
                nowSize++;
            }
            result.add(count);
            index += count;
        }
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
class Progress{
    int nowProgress;
    int speed;
    Progress(int nowProgress, int speed){
        this.nowProgress = nowProgress;
        this.speed = speed;
    }
}