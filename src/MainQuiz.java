
import java.util.Scanner;

public class MainQuiz {

	static boolean feedbackReq;
	public static void main(String[] args) {
		Scanner scanner= new Scanner(System.in);
		String command = scanner.nextLine();
		feedbackReq= false;
		String filename="";
		String[] commandArgs = command.split(" ");
		if(commandArgs[0].equals("qconvert")) {
			if(commandArgs[1].equals("-f")) {
				filename = commandArgs[2];
			}else if(commandArgs[1].startsWith("-")){
				System.out.println("Wrong option given should be -f");
				System.exit(0);
			}else {
				feedbackReq = true;
				filename= commandArgs[1];
			}
			
			switch(filename) {
			case "sample1.txt":
				Sample1.process(feedbackReq);
				break;
			case "sample2.txt":
				Sample2.process(feedbackReq);
				break;
			case "sample3.txt":
				Sample3.process(feedbackReq);
				break;
			case "sample4.txt":
				Sample4.process(feedbackReq);
				break;
			default:
				System.out.println("Wrong filename found");
				System.exit(0);
			}
			new QuizGUI();
		}else {
			System.out.println("Wrong command is given");
		}
		
		scanner.close();

	}

}
