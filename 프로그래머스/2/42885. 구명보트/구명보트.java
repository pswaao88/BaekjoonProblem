import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int n = people.length;
        Arrays.sort(people);
        Deque<Integer> deq = new LinkedList<>();
        for(int i = 0; i < n; i++){
            deq.addLast(people[i]);
        }
        while(!deq.isEmpty()){
            if(deq.size() >= 2){
                int min = deq.removeFirst();
                int max = deq.removeLast();
                if(min + max > limit){
                    deq.addFirst(min);
                }
                answer++;
            }else{
                answer++;
                break;
            }
        }
        return answer;
    }
}