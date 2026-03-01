import java.io.*;
import java.util.*;

class Solution {
    int max = 0;
    int[] answer;
    int n;
    int[][] dices;
    ArrayList<int[]> diceA;
    ArrayList<int[]> diceB;
    ArrayList<Integer> resultA;
    ArrayList<Integer> resultB;
    public int[] solution(int[][] dice) {
        dices = dice;
        n = dice.length;
        answer = new int[n/2];
        findDice(0, 0, 0);
        
        return answer;
    }
    void findDice(int visit, int start, int depth){
        // 주사위를 나누었을때
        if(depth >= n / 2){
            //해당 visit으로 값 찾아서 조합 찾기
            diceA = new ArrayList<>();
            diceB = new ArrayList<>();
            resultA = new ArrayList<>();
            resultB = new ArrayList<>();
            for(int i = 0; i < n; i++){
                if((visit &(1 << i)) != 0){
                    diceA.add(dices[i]);
                }else{
                    diceB.add(dices[i]);
                }
            }
            findA(0, 0);
            findB(0, 0);
            int win = 0;
            Collections.sort(resultB);
            for(int i = 0; i < resultA.size(); i++){
                int now = resultA.get(i);
                // 이분탐색으로 개수 찾기
                // lo는 now보다 큰 수 시작 lo가 답이 되도록
                // lo가 0이면 0, lo 가 size면 size가 됨
                int lo = 0;
                int hi = resultB.size();
                while(lo < hi){
                    int mid = lo + (hi - lo) / 2;
                    int next = resultB.get(mid);
                    if(now <= next){
                        hi = mid;
                    }else{
                        lo = mid + 1;
                    }
                }
                win += lo;
            }
            if(max < win){
                max = win;
                int index = 0;
                for(int i = 0; i < n; i++){
                    if((visit &(1 << i)) != 0){
                        answer[index] = i+1;
                        index++;
                    }
                }   
            }
            return;
        }
        for(int i = start; i < n; i++){
            int nowBinary = (1 << i);
            int nextVisit = visit | nowBinary;
            findDice(nextVisit, i + 1, depth + 1);
        }
    }
    void findA(int total, int depth){
        if(depth >= n / 2){
            resultA.add(total);
            return;
        }
        int[] nowDice = diceA.get(depth);
        for(int i = 0; i < 6; i++){
            findA(total + nowDice[i], depth + 1);
        }
    }
    void findB(int total, int depth){
        if(depth >= n / 2){
            resultB.add(total);
            return;
        }
        int[] nowDice = diceB.get(depth);
        for(int i = 0; i < 6; i++){
            findB(total + nowDice[i], depth + 1);
        }
    }
    
}