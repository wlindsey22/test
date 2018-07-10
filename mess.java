package test;
import java.util.Scanner;
public class mess {
	private static String response;
	private static double endTime;
	private static Scanner userResponse;
	private static double startTime;
	private static boolean timerStart;
	private static boolean timerEnd;
	private static boolean timerRunning;
	public static void main(String[] args) {
		timerRunning = true;
		userResponse = new Scanner(System.in);
		System.out.println("Please enter quit to exit the program");
		while(timerRunning) {
			timerStart = false;
			timerEnd = true;
			if(timerStart == false) {
				System.out.println("Please enter start to start the timer");
				response = userResponse.nextLine();
				if(checkForQuit(response) == 1) {
					break;
				}
				if(checkForStart(response) == 1) {
					startTime = System.currentTimeMillis();
					timerStart = true;
				}
				if(timerStart == true) {
					System.out.println("Please enter end when done with timer");
					while(timerEnd) {
						response = userResponse.nextLine();
						checkForQuit(response);
							timerEnd = false;
							
						
						if(checkForEnd(response) == 1) {
							endTime = System.currentTimeMillis();
							timerEnd = false;
							System.out.println("Timer done");
							System.out.println((endTime - startTime)/1000 + " seconds");
						}
					}
				}
			}
		}
			System.out.println("Thanks for using the timer");
		
				
	}
	
	public static int checkForEnd(String e) {
		int rc = 0;
		if(response.toLowerCase().equals("end")) {
			timerEnd = !timerEnd;
			rc = 1;
		}
		return rc;
	}
	public static int checkForStart(String e) {
		int rc = 0;
		if(response.toLowerCase().equals("start")) {
			timerStart = !timerStart;
			rc = 1;
		}
		return rc;
	}
	public static int checkForQuit(String e) {
		int rc = 0;
		if(response.toLowerCase().equals("quit")) {
			timerRunning = !timerRunning;
			rc = 1;
		}
		return rc;
	}
}
