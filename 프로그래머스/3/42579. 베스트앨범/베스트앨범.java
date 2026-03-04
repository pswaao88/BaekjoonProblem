import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int n = plays.length;
        Music[] musics = new Music[n];
        HashMap<String, int[]> map = new HashMap<>();

        for(int i = 0; i < n; i++){
            musics[i] = new Music(genres[i], plays[i], i);
            int[] count = map.getOrDefault(musics[i].title, new int[2]);
            count[0] += 1;
            count[1] += plays[i];
            map.put(musics[i].title, count);
        }
        
        Arrays.sort(musics, (m1, m2) -> {
           if(m1.title.equals(m2.title)){
               if(m2.play == m1.play){
                   return m1.id - m2.id;
               }
               return m2.play - m1.play;
           }
            return m1.title.compareTo(m2.title);
        });
        
        Genre[] result = new Genre[map.size()];
        int index = 0;
        int size = 0;
        for(int i = 0; i < n;){
            Music now = musics[i];
            String nowTitle = now.title;
            int[] nowCount = map.get(nowTitle);
            if(nowCount[0] == 1){
                result[index++] = new Genre(nowCount[1], new int[] {now.id}, 1);
                size++;
            }else{
                Music next = musics[i+1];
                result[index++] = new Genre(nowCount[1], new int[] {now.id, next.id}, 2);
                size += 2;
            }
            i += nowCount[0];
        }
        Arrays.sort(result, (r1, r2) -> {
            return r2.count - r1.count;
        });

        
        int[] answer = new int[size];
        int answerIndex = 0;
        for(int i = 0; i < result.length; i++){
            Genre now = result[i];
            if(now.song == 1){
                answer[answerIndex++] = now.best[0];    
            }else{
                answer[answerIndex++] = now.best[0];
                answer[answerIndex++] = now.best[1];
            }
            
        }
        return answer;
    }
}
class Music{
    String title;
    int play;
    int id;
    Music(String title, int play, int id){
        this.title = title;
        this.play = play;
        this.id = id;
    }
}
class Genre{
    int count;
    int[] best;
    int song;
    Genre(int count, int[] best, int song){
        this.count = count;
        this.best = best;
        this.song = song;
    }
}