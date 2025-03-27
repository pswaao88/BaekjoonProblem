import java.util.Scanner;
class Main {
  public static void main(String[] args) {
  	  
	  Scanner scan = new Scanner(System.in);
	  
	  int N = scan.nextInt();
	  
	  for(int i = 0; i < N; i++) {
		  
		  for(int x = N-i-1; x > 0; x--){
			  System.out.print(" ");
		  }
		  for(int x = 0; x < 2*(i+1)-1; x++){
			  System.out.print("*");
		  }
		  
		  System.out.println();
	  }
	  scan.close();
  }
}