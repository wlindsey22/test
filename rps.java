package test;
import java.util.Scanner;
public class rps {
	private static int playerCount;
	private static int computerCount;
	private static boolean win;
	private static boolean catchEnd = true;
	private static int max = 2;
	public static void main(String[] args) {
		runGame();
	}

	static int choice(){
		double min = 1;
		double max = 3;
		double range = (max - min) + 1;     
		return ((int)((Math.random() * range) + min));
	}
	// 1 = paper
	// 2 = scissors
	// 3 = rock
	public static void catchEnd() {
		if(playerCount >= 3) {
			catchEnd = false;
		}
		if(computerCount >= 3) {
			catchEnd = false;
		}
		else {
			catchEnd = true;
		}
		
	}
	static boolean winner(int a, int b) {
		System.out.println("Player choice is: " + a);
		System.out.println("Computer choice is: " + b);
		if(a == b) {
			//win = false;
		}
		if(a == 1 && b == 3 || a == 2 && b == 1 || a == 3 && b == 2 ) {
			playerCount++;
			System.out.println("Player wins! Player score: " + playerCount + " Computer score: " + computerCount);
			win = true;
		}
		else {
			computerCount++;
			System.out.println("Computer wins! Player score: " + playerCount + " Computer score: " + computerCount);
			win = false;
		}
		catchEnd();
		return win;
	}
	private static void runGame() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to rps! First to 3 score wins. In the case of tie, the computer wins.");
		while( catchEnd ) {
			System.out.println("Please make a choice: 1 = paper, 2 = scissors, 3 = rock");
			int choice = input.nextInt();
			winner(choice, choice());
		}
		System.out.println("Thanks for playing rps");
	}
	
}
