import java.io.*;
import java.util.*;

public class Main {
    static Wheel[] wheels;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        wheels = new Wheel[4];
        for(int i = 0; i < 4; i++){
            char[] s = br.readLine().toCharArray();
            wheels[i] = new Wheel(s[0] - '0', s[1]- '0', s[2]- '0', s[3]- '0', s[4]- '0', s[5]- '0', s[6]- '0', s[7]- '0');
        }
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            total(index, direction);
        }
        int result = 0;
        int now = 1;
        for(int i = 0; i < 4; i++){
            if(wheels[i].N == 1){
                result += now;
            }
            now *= 2;
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
    static void total(int index ,int direction){
        boolean[] checkSameList = new boolean[3];
        for(int i = 0; i <= 2; i++){
            checkSameList[i] = checkSame(i, i+1);
        }
        ArrayList<int[]> rotationList = new ArrayList<>();
        rotationList.add(new int[] {index, direction});

        if(index == 0){
            if(!checkSameList[0]){
                rotationList.add(new int[] {1, direction * -1});
                if(!checkSameList[1]){
                    rotationList.add(new int[] {2, direction});
                    if(!checkSameList[2]){
                        rotationList.add(new int[] {3, direction * -1});
                    }
                }
            }
        }else if(index == 1){
            if(!checkSameList[0]){
                rotationList.add(new int[] {0, direction * -1});
            }
            if(!checkSameList[1]){
                rotationList.add(new int[] {2, direction * -1});
                if(!checkSameList[2]){
                    rotationList.add(new int[] {3, direction});
                }
            }
        }else if(index == 2){
            if(!checkSameList[1]){
                rotationList.add(new int[] {1, direction * -1});
                if(!checkSameList[0]){
                    rotationList.add(new int[] {0, direction});
                }
            }
            if(!checkSameList[2]){
                rotationList.add(new int[] {3, direction * -1});
            }
        }else{
            if(!checkSameList[2]){
                rotationList.add(new int[] {2, direction * -1});
                if(!checkSameList[1]){
                    rotationList.add(new int[] {1, direction});
                    if(!checkSameList[0]){
                        rotationList.add(new int[] {0, direction * -1});
                    }
                }
            }
        }
        for(int i = 0; i < rotationList.size(); i++){
            int[] now = rotationList.get(i);
            rotation(now[0], now[1]);
        }
    }
    static boolean checkSame(int left, int right){
        Wheel leftWheel = wheels[left];
        Wheel rightWheel = wheels[right];
        return leftWheel.E == rightWheel.W;
    }
    // 시계 방향
    static void rotation(int index, int direction){
        Wheel now = wheels[index];
        int tmpN = now.N;
        int tmpNE = now.NE;
        int tmpE = now.E;
        int tmpSE = now.SE;
        int tmpS = now.S;
        int tmpSW = now.SW;
        int tmpW = now.W;
        int tmpNW = now.NW;
        // 시계
        if(direction == 1){
            now.N = tmpNW;
            now.NE = tmpN;
            now.E = tmpNE;
            now.SE = tmpE;
            now.S = tmpSE;
            now.SW = tmpS;
            now.W = tmpSW;
            now.NW = tmpW;
            return;
        }
        // 반시계
        now.N = tmpNE;
        now.NE = tmpE;
        now.E = tmpSE;
        now.SE = tmpS;
        now.S = tmpSW;
        now.SW = tmpW;
        now.W = tmpNW;
        now.NW = tmpN;
    }
}
class Wheel{
    int N;
    int NE;
    int E;
    int SE;
    int S;
    int SW;
    int W;
    int NW;
    Wheel(int N, int NE, int E, int SE, int S, int SW, int W, int NW){
        this.N = N;
        this.NE = NE;
        this.E = E;
        this.SE = SE;
        this.S = S;
        this.SW = SW;
        this.W = W;
        this.NW = NW;
    }
}
