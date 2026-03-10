import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int year1 = Integer.parseInt(st.nextToken());
        int month1 = Integer.parseInt(st.nextToken());
        int day1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int year2 = Integer.parseInt(st.nextToken());
        int month2 = Integer.parseInt(st.nextToken());
        int day2 = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        boolean isGG = false;
        if (year1 + 1000 < year2) {
            isGG = true;
        } else if (year1 + 1000 == year2){
            if(month1 < month2){
                isGG = true;
            }else if(month1 == month2){
                if(day1 <= day2) {
                    isGG = true;
                }
            }
        }
        
        if(!isGG){
            int total1 = calTotal(year1, month1, day1);
            int total2 = calTotal(year2, month2, day2);
            int x = total2 - total1;
            sb.append("D-").append(x);
        }else{
            sb.append("gg");
        }
        bw.write(sb.toString());
        bw.flush();


    }
    static int calTotal(int year, int month, int day){
        int result = 0;
        for(int i = 1; i < year; i++){
            result += calYear(i);
        }
        for(int i = 1; i < month; i++){
            result += calMonth(i, isYoon(year));
        }
        result += day;
        return result;
    }
    static int calYear(int year){
        if(isYoon(year)){
            return 366;
        }
        return 365;
    }
    static int calMonth(int month, boolean yoon){
        if(yoon && month == 2){
            return 29;
        }
        switch(month){
            case 1:
                return 31;
            case 2:
                return 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
        }
        return 30;
    }
    static boolean isYoon(int year){
        if(year % 4 == 0){
            if(year % 100 == 0){
                if(year % 400 == 0){
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }
}
