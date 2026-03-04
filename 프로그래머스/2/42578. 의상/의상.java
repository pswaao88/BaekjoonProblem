import java.util.*;

class Solution {
    boolean[] visit;
    int n;
    int answer = 0;
    int[] mul;
    public int solution(String[][] clothes) {
        
        n = clothes.length;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < n; i++){
            String now = clothes[i][1];
            int count = map.getOrDefault(now, 0);
            map.put(now, count + 1);
        }
        mul = new int[n];
        int index = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            mul[index++] = entry.getValue();
        }
        
        find(0, 1, 0);
        return answer;
    }
    
    void find(int start, int value, int depth){
        if(depth >= n){
            return;
        }
        for(int i = start; i < n; i++){
            answer += (value * mul[i]);
            find(i + 1, value * mul[i], depth + 1);
        }
    }
}