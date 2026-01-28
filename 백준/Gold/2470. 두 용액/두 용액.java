import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] array = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int positiveCount = 0;
        int negativeCount = 0;

        for(int i = 0; i < N; i++){
            array[i] = Integer.parseInt(st.nextToken());
            if(array[i] > 0) positiveCount++;
            else negativeCount++;
        }
        Arrays.sort(array);

        int positiveStartIndex = 0;
        int negativeEndIndex = 0;
        for(int i = 0; i < N; i++){
            if(array[i] > 0){
                positiveStartIndex = i;
                break;
            }
            negativeEndIndex = i;
        }

        int leftResult = 0;
        int rightResult = N-1;
        int min = Integer.MAX_VALUE;

        if(positiveCount >= 2){
            int sum = array[positiveStartIndex] + array[positiveStartIndex + 1];
            if(Math.abs(min) > Math.abs(sum)){
                min = sum;
                leftResult = array[positiveStartIndex];
                rightResult = array[positiveStartIndex + 1];
            }
        }
        if(negativeCount >= 2){
            int sum = array[negativeEndIndex - 1] + array[negativeEndIndex];
            if(Math.abs(min) > Math.abs(sum)){
                min = sum;
                leftResult = array[negativeEndIndex - 1];
                rightResult = array[negativeEndIndex];
            }
        }

        int left = 0;
        int right = N-1;
        while(left < right){
            int leftNumber = array[left];
            int rightNumber = array[right];
            int sum = 0;
            if(leftNumber < 0 && rightNumber > 0){
                sum = (leftNumber + rightNumber);
                if(sum == 0) {
                    leftResult = leftNumber;
                    rightResult = rightNumber;
                    break;
                }
                if(Math.abs(min) > Math.abs(sum)){
                    min = sum;
                    leftResult = leftNumber;
                    rightResult = rightNumber;
                }
                if(sum > 0){
                    right--;
                }else{
                    left++;
                }
            }else if(leftNumber < 0 && rightNumber < 0){
                break;
            }else if(leftNumber > 0 && rightNumber > 0){
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(leftResult).append(" ").append(rightResult);
        bw.write(sb.toString());
        bw.flush();
    }
}
