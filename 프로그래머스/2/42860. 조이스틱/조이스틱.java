class Solution {
    int min = 987654321;
    int n;
    String target;
    public int solution(String name) {
        int answer = 0;
        int nowIndex = 0;
        n = name.length();
        target = name;
        
        
        int visit = 0;
        int depth = 0;
        for(int i = 0; i < n; i++){
            answer += calMin(name.charAt(i));
            if(name.charAt(i) == 'A'){
                visit = visit | (1 << i);
                depth++;
            }
        }
        
        find(visit, 0, 0, depth);
        System.out.println(answer);
        System.out.println(min);
        return answer + min;
    }
    void find(int visit, int value, int start, int depth){
        if(depth >= n){
            min = Math.min(min, value);
            return;
        }
        for(int i = 0; i < n; i++){
            if((visit & (1 << i)) != 0) continue;
            int nextVisit = visit | (1 << i);
            find(nextVisit, value + calIndex(start, i), i, depth + 1);
            
        }
        
    }
    int calMin(char a){
        // 얼마나 떨어져 있는가
        int nowA = a - 'A';
        int reverseA = 'Z' - a + 1; 
        return Math.min(nowA, reverseA);
    }
    int calIndex(int nowIndex, int targetIndex){
        int A = Math.abs(nowIndex - targetIndex);
        int B = nowIndex + n - targetIndex; 
        int C = n - nowIndex + targetIndex;
        return Math.min(Math.min(A, B), C);
    }
    
}