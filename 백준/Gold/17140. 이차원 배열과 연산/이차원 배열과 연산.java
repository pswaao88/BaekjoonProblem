import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> array = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            array.add(new ArrayList<>());
            for(int j = 0; j < 3; j++){
                array.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }
        int time = 0;
        while(time <= 100){
            int rowSize = array.size();
            int colSize = array.get(0).size();

            if(rowSize >= r + 1 && colSize >= c + 1) {
                if(array.get(r).get(c) == k) break;
            }

            if(rowSize >= colSize){
                calculateR();
            }else{
                calculateC();
            }
            time++;
        }
        if(time > 100){
            bw.write("-1");
        }else{
            bw.write(Integer.toString(time));
        }
        bw.flush();
    }
    static void calculateR(){
        ArrayList<ArrayList<Integer>> sortedArray = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < array.size(); i++){
            int[] count = new int[101];
            ArrayList<Integer> nowRow = array.get(i);
            for(int j = 0; j < nowRow.size(); j++){
                if(nowRow.get(j) != 0){
                    count[nowRow.get(j)]++;
                }
            }

            sortedArray.add(new ArrayList<>());

            ArrayList<int[]> row = new ArrayList<>();
            for(int j = 1; j <= 100; j++){
                if(count[j] > 0){
                    row.add(new int[] {j, count[j]});
                }
            }
            Collections.sort(row, (c1, c2) -> {
                if(c1[1] == c2[1]){
                    return Integer.compare(c1[0], c2[0]);
                }
                return Integer.compare(c1[1], c2[1]);
            });

            for(int j = 0; j < row.size(); j++){
                if(sortedArray.get(i).size() >= 100) break;
                int[] now = row.get(j);
                sortedArray.get(i).add(now[0]);
                sortedArray.get(i).add(now[1]);
            }
            max = Math.max(max, sortedArray.get(i).size());
        }

        max = Math.min(max, 100);
        for(int i = 0; i < sortedArray.size(); i++){
            ArrayList<Integer> now = sortedArray.get(i);

            if(now.size() < max){
                int end = max - now.size();
                for(int j = 0; j < end; j++){
                    now.add(0);
                }
            }
        }
        array = sortedArray;
    }
    static void calculateC(){
        ArrayList<ArrayList<Integer>> sortedArray = new ArrayList<>();
        int colSize = array.get(0).size();
        int rowSize = array.size();
        int maxRowSize = 0;
        for(int i = 0; i < 100; i++){
            sortedArray.add(new ArrayList<>());
            for(int j = 0; j < colSize; j++){
                sortedArray.get(i).add(0);
            }
        }
        for(int i = 0; i < colSize; i++){
            int[] count = new int[101];
            for(int j = 0; j < rowSize; j++){
                int now = array.get(j).get(i);
                if(now != 0){
                    count[now]++;
                }
            }

            int index = 0;
            ArrayList<int[]> column = new ArrayList<>();
            for(int j = 1; j <= 100; j++){
                if(count[j] > 0){
                    column.add(new int[] {j, count[j]});
                }
            }
            Collections.sort(column, (c1, c2) -> {
               if(c1[1] == c2[1]){
                   return Integer.compare(c1[0], c2[0]);
               }
               return Integer.compare(c1[1], c2[1]);
            });

            // count에 맞게 열에 추가
            for(int j = 0; j < column.size(); j++){
                if(index >= 100) break;
                int[] now = column.get(j);

                sortedArray.get(index).set(i, now[0]);
                sortedArray.get(index+1).set(i,now[1]);
                index+=2;
                maxRowSize = Math.max(maxRowSize, index);
            }
        }

        // 최대 행의 개수까지 자르기
        for(int i = 99; i >= maxRowSize; i--){
            sortedArray.remove(i);
        }
        array = sortedArray;
    }
}
