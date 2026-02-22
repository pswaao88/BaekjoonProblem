import java.io.*;
import java.util.*;

class Solution {
    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<String> notX = new ArrayList<>();
    ArrayList<String> X = new ArrayList<>();

    public String[] solution(String[] expressions) {
        for(int i = 0; i < expressions.length; i++){
            String now = expressions[i];
            if(now.charAt(now.length() - 1) == 'X'){
                X.add(now);
            }else{
                notX.add(now);
            }
        }
        int visit = find();
                
        String[] answer = new String[X.size()];
        
        for(int i = 0; i < X.size(); i++){
            StringTokenizer st = new StringTokenizer(X.get(i));
            String A = st.nextToken();
            String op = st.nextToken();
            String B = st.nextToken();
            String eq = st.nextToken();
            String C = st.nextToken();
            
            String result = "";
            for(int j = 2; j <= 9; j++){
                // 후보 진법
                if((visit & (1 << j)) != 0){
                    int numberA = Integer.parseInt(A, j);
                    int numberB = Integer.parseInt(B, j);
                    int numberC;
                    if(op.equals("+")){
                        numberC = numberA + numberB;
                    }else{
                        numberC = numberA - numberB;
                    }
                    String nowResult = Integer.toString(numberC, j);
                    if(result.equals("")){
                        result = nowResult;
                    }else{
                        // ? 처리
                        if(!result.equals(nowResult)){
                            result = "?";
                            break;
                        }
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(A).append(" ")
                .append(op).append(" ")
                .append(B).append(" ")
                .append(eq).append(" ")
                .append(result);
            answer[i] = sb.toString();
        }
        
        return answer;
    }
    // 후보 진법 찾기
    int find(){
        int visit = 0;
        for(int i = 2; i <= 9; i++){
            visit = visit | (1 << i);
        }
        
        // 모든 문자열에 대해서 숫자 이하는 진법 불가
        for(int i = 0; i < notX.size(); i++){
            String s = notX.get(i);
            int max = 0;
            for(int j = 0; j < s.length(); j++){
                int now = s.charAt(j) - '0';
                if(1 <= now && now <= 9){
                    max = Math.max(max, now);
                }
            }
            for(int j = 2; j <= max; j++){
                visit = visit & ~(1 << j);    
            }
        }
        for(int i = 0; i < X.size(); i++){
            String s = X.get(i);
            int max = 0;
            for(int j = 0; j < s.length(); j++){
                int now = s.charAt(j) - '0';
                if(1 <= now && now <= 9){
                    max = Math.max(max, now);
                }
            }
            for(int j = 2; j <= max; j++){
                visit = visit & ~(1 << j);    
            }
        }
        // 결과값 다른지 체크
        for(int i = 0; i < notX.size(); i++){
            StringTokenizer st = new StringTokenizer(notX.get(i));
            
            String A = st.nextToken();
            String op = st.nextToken();
            String B = st.nextToken();
            String eq = st.nextToken();
            String C = st.nextToken();
            for(int j = 2; j <= 9; j++){
                // 아직 남아있는 경우만 체크
                if((visit & (1 << j)) != 0){
                    int numberA = Integer.parseInt(A, j);
                    int numberB = Integer.parseInt(B, j);
                    int numberC = Integer.parseInt(C, j);
                    if(op.equals("+") && (numberA + numberB) != numberC){
                        visit = visit & ~(1 << j);
                    }else if(op.equals("-") && (numberA - numberB) != numberC){
                        visit = visit & ~(1 << j);
                    }
                }
            }
        }
        return visit;
    }
}