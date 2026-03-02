import java.util.*;
import java.io.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int remainCard = n / 3 * 2;
        int cardIndex = n / 3;
        // 현재 가지고 있는 카드
        ArrayList<Integer> card = new ArrayList<>();
        for(int i = 0; i < (n / 3); i++){
            card.add(cards[i]);
        }
        // 임시 카드
        ArrayList<Integer> tmp = new ArrayList<>();
        int round = 1;
        while(cardIndex < n){
            // n + 1 카드를 제거 했는지
            boolean yes = false;
            // 카드 2장뽑기
            int card1 = cards[cardIndex++];
            int card2 = cards[cardIndex++];

            // 임시로 저장
            tmp.add(card1);
            tmp.add(card2);
            // 현재 카드 뭉치에서 n+1 찾아 있으면 제거
            boolean remove = false;
            for(int i = 0; i < card.size(); i++){
                int cardI = card.get(i);
                boolean loop = true;
                for(int j = i + 1; j < card.size(); j++){
                    int cardJ = card.get(j);
                    if(cardI + cardJ == n + 1){
                        card.remove(j);
                        card.remove(i);
                        remove = true;
                        yes = true;
                        loop = false;
                        break;
                    }
                }
                if(!loop) break;
            }
            // 제거하지 못한 경우 tmp에서 되는 카드 찾기
            // 현재 카드와 비교
            if(!remove && coin > 0){
                for(int i = 0; i < card.size(); i++){
                    int cardI = card.get(i);
                    boolean loop = true;
                    for(int j = 0; j < tmp.size(); j++){
                        int cardJ = tmp.get(j);
                        if(cardI + cardJ == n + 1){
                            coin--;
                            card.remove(i);
                            tmp.remove(j);
                            remove = true;
                            yes = true;
                            loop = false;
                            break;
                        }
                    }
                    if(!loop) break;
                }
            }
            // tmp 내에서 비교후 제거
            if(!remove && coin >= 2){
                for(int i = 0; i < tmp.size(); i++){
                    int cardI = tmp.get(i);
                    boolean loop = true;
                    for(int j = i + 1; j < tmp.size(); j++){
                        int cardJ = tmp.get(j);
                        if(cardI + cardJ == n + 1){
                            coin -= 2;
                            tmp.remove(j);
                            tmp.remove(i);
                            yes = true;
                            loop = false;
                            break;
                        }
                    }
                    if(!loop) break;

                }
            }
            if(!yes) break;
            round++;
        }
                
        return round;
    }
}