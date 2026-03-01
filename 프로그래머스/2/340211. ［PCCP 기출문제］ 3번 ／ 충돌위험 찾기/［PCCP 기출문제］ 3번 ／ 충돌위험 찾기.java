import java.io.*;
import java.util.*;

class Solution {
    int pointNumber, robotNumber;
    boolean[] finish;
    Robot[] robots;
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        pointNumber = points.length;
        robotNumber = routes.length;
        // 포인트 저장
        Coordinate[] point = new Coordinate[pointNumber];
        for(int i = 0; i < pointNumber; i++){
            int[] nowPoint = points[i];
            point[i] = new Coordinate(nowPoint[0], nowPoint[1]);
        }
        // 로봇 저장
        robots = new Robot[robotNumber];
        for(int i = 0; i < robotNumber; i++){
            int[] nowRobot = routes[i];
            
            Coordinate startRoute = point[nowRobot[0]-1];
            Coordinate start = new Coordinate(startRoute.x, startRoute.y);
            Coordinate[] robotRoutes = new Coordinate[nowRobot.length - 1];
            for(int j = 0; j < nowRobot.length - 1; j++){
                robotRoutes[j] = point[nowRobot[j+1]-1];
            }
            robots[i] = new Robot(start, robotRoutes);
        }
        finish = new boolean[robotNumber];
        //모든 로봇이 벗어날때까지
        while(!isOk()){
            // 충돌 가능성 찾기
            answer += find();
            // 벗어나는 로봇 체크하기
            for(int i = 0; i < robotNumber; i++){
                if(!finish[i] && robots[i].isFinish()){
                    finish[i] = true;
                }
            }
            // 이동하기
            for(int i = 0; i < robotNumber; i++){
                if(!finish[i]){
                    robots[i].move();
                }
            }
        }
        return answer;
    }
    int find(){
        int total = 0;
        int[][] map = new int[101][101];
        for(int i = 0; i < robotNumber; i++){
            if(finish[i]) continue;
            Robot now = robots[i];
            map[now.now.x][now.now.y]++;
        }
        for(int i = 1; i <= 100; i++){
            for(int j = 1; j <= 100; j++){
                if(map[i][j] > 1){
                    total++;
                }
            }
        }
        return total;
    }
    boolean isOk(){
        for(int i = 0; i < robotNumber; i++){
            if(!finish[i]) return false;
        }
        return true;
    }
}
class Robot{
    Coordinate now;
    Coordinate[] routes;
    int nowRouteIndex;
    Robot(Coordinate now, Coordinate[] routes){
        this.now = now;
        this.routes = routes;
        this.nowRouteIndex = 0;
    }
    
    boolean isFinish(){
        if(nowRouteIndex != routes.length - 1){
            return false;
        }
        Coordinate target = routes[nowRouteIndex];
        if(now.x == target.x && now.y == target.y){
            return true;
        }
        return false;
    }
    
    void move(){
        // 다음 인덱스로 넘어가는지 판단
        Coordinate target = routes[nowRouteIndex];
        if(nowRouteIndex < this.routes.length - 1 && now.x == target.x && now.y == target.y){
            nowRouteIndex++;
        }
        target = routes[nowRouteIndex];
        // 행 이동
        if(now.x != target.x){
            if(now.x > target.x){
                now.x--;
            }else{
                now.x++;
            }
            return;
        }
        // 열이동
        if(now.y > target.y){
            now.y--;
        }else{
            now.y++;
        } 
    }
}
class Coordinate{
    int x;
    int y;
    Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}