import java.io.*;
import java.util.*;

public class Main {
    static int maxX, maxY, targetX, targetY;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxX = Integer.parseInt(st.nextToken());
        maxY = Integer.parseInt(st.nextToken());

        targetX = Integer.parseInt(st.nextToken());
        targetY = Integer.parseInt(st.nextToken());

        visited = new boolean[maxX+1][maxY+1];
        int result = find();
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static int find(){
        Queue<Visit> q = new LinkedList<>();
        q.add(new Visit(0, 0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Visit now = q.remove();
            if(now.x == targetX && now.y == targetY){
                return now.count;
            }
            for(int i = 0; i < 6; i++){
                Visit tmp = new Visit(now.x, now.y, now.count);
                if(i == 0){
                    tmp.xF();
                }else if(i == 1){
                    tmp.yF();
                }else if(i == 2){
                    tmp.xE();
                }else if(i == 3){
                    tmp.yE();
                }else if(i == 4){
                    tmp.xToyM();
                }else{
                    tmp.yToxM();
                }
                tmp.count++;
                if(visited[tmp.x][tmp.y]) continue;
                q.add(tmp);
                visited[tmp.x][tmp.y] = true;
            }
        }
        return -1;
    }

    static class Visit{
        int x;
        int y;
        int count;
        Visit(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
        void xF(){
            this.x = maxX;
        }
        void yF(){
            this.y = maxY;
        }
        void xE(){
            this.x = 0;
        }
        void yE(){
            this.y = 0;
        }
        void xToyM(){
            int remain = maxY - this.y;
            if(remain >= this.x){
                this.y += this.x;
                this.x = 0;
                return;
            }
            this.y = maxY;
            this.x -= remain;
        }
        void yToxM(){
            int remain = maxX - this.x;
            if(remain >= this.y){
                this.x += this.y;
                this.y = 0;
                return;
            }
            this.x = maxX;
            this.y -= remain;
        }
    }

}
