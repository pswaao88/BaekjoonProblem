import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int total = 0;
        int n  = minerals.length;
        
        int dia = 0;
        int iron = 0;
        int stone = 0;
        ArrayList<Mineral> list = new ArrayList<>();
        // 광물을 5개씩 끊어서 객체로 관리
        for(int i = 0; i < n; i++){            
            String now = minerals[i];
            
            if(i == n-1){
                if(now.charAt(0) == 'd'){
                    dia++;
                }else if(now.charAt(0) == 'i'){
                    iron++;
                }else{
                    stone++;
                }
                list.add(new Mineral(dia, iron, stone));
                break;
            }
            
            if(now.charAt(0) == 'd'){
                dia++;
            }else if(now.charAt(0) == 'i'){
                iron++;
            }else{
                stone++;
            }
            total++;
            
            if(total == 5){
                list.add(new Mineral(dia, iron, stone));
                dia = 0;
                iron = 0;
                stone = 0;
                total = 0;
            }
        }
        int totalPick = 0;
        for(int i = 0; i < 3; i++){
            totalPick += picks[i];
        }
        // 광물 개수 대로 끊고 정렬
        
        int size = Math.min(totalPick, list.size());
        // 정렬해서 무거운 순으으로 다이아 철 돌 순으로 처리
        ArrayList<Mineral> finalList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            finalList.add(list.get(i));
        }
        Collections.sort(finalList, (m1, m2) -> {
            if(m2.dia == m1.dia){
                if(m2.iron == m1.iron){
                    return m2.stone - m1.stone;
                }    
                return m2.iron - m1.iron;
            }
            return m2.dia - m1.dia;
        });
        
        // 곡괭이 개수 만큼 처리하기
        for(int i = 0; i < size; i++){
            int pickType = 0;
            for(int j = 0; j < 3; j++){
                if(picks[j] > 0){
                    picks[j]--;
                    pickType = j;
                    break;
                }
            }
            Mineral now = finalList.get(i);
            if(pickType == 0){
                answer += now.dia;
                answer += now.iron;
                answer += now.stone;
            }else if(pickType == 1){
                answer += now.dia * 5;
                answer += now.iron;
                answer += now.stone;
            }else{
                answer += now.dia * 25;
                answer += now.iron * 5;
                answer += now.stone;
            }
        }
        
        return answer;
    }
}
class Mineral{
    int dia;
    int iron;
    int stone;
    Mineral(int dia, int iron, int stone){
        this.dia = dia;
        this.iron = iron;
        this.stone = stone;
    }
}