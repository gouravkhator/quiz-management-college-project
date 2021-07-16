
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sample1 {
	static Scanner sc;
	static boolean feedbackReq;
	public static void process(boolean feedReq) {
		feedbackReq = feedReq;
		Path currentDir = Paths.get("");
		String dir =  currentDir.toAbsolutePath().toString();
		File file = new File(dir+"/files/Sample-1-Input.txt");
		if(dir.contains("\\"))
			file = new File(dir+"\\files\\Sample-1-Input.txt");
		try {
			sc = new Scanner(file);
			fetch();
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Input Files Missing");
		}
	}
	
	private static void fetch() {
		List<String> questions = new ArrayList<>();
		List<String> answers = new ArrayList<>();
		List<List<String>> options= new ArrayList<>();
		List<String> feedbacks = new ArrayList<>();
		List<String> optionsIndividual = new ArrayList<>();

		String question = "",firstLine="",explain="",option="",line="";
		char answer;
		while(sc.hasNextLine()) {
			line = sc.nextLine().trim();
			if(!line.startsWith("Java Questions & Answers") && !line.startsWith("This set of Java Multiple") 
					&& !line.equals("View Answer") && !line.equals("") && !line.equals("advertisement")) {
				if(line.startsWith("Answer: ")) {
					answer = line.charAt(line.length()-1);
					answers.add("ANSWER: "+(char)(answer-32));
					if(!option.equals("")) {  //option remaining of previous question
						optionsIndividual.add(option);
						List<String> temp = new ArrayList<>(optionsIndividual);
						options.add(temp);  
						//created new list as we cleared optionsIndividual and so it clears from options also as its passed by reference 
						optionsIndividual.clear();
						option="";
					}
				}
				else if(line.startsWith("Explanation: ")) {
					firstLine = "Explain";  //a flag
					explain = line;
				}
				else if(line.charAt(0)>=49 && line.charAt(0)<=57){
					int pos = line.indexOf('.');
					if(pos!=-1) {
						if(!explain.equals("")) { //explanation of previous question
							feedbacks.add(explain);
							explain="";
						}

						question = line.substring(pos+2);
						firstLine = "Question";
					}
				}
				else{ //it can be option or other lines
					if(line.charAt(1)==')') {
						//option
						if(!question.equals("")) { //question line is done
							questions.add(question);
							question="";
						}
						if(!option.equals("")) { //option is there then it is the previous question option
							optionsIndividual.add(option);
							option="";
						}
						option=(char)(line.charAt(0)-32)+". "+line.substring(3);
						firstLine="Option";

					}else {
						//same question or same explanation or same option on next line
						if(firstLine.equals("Explain")) {
							if(!explain.equals(""))
								explain+="\n";
							explain+=line;
						}else if(firstLine.equals("Question")){
							if(!question.equals(""))
								question+="  ";
							question+=line;
						}else {
							//option
							if(!option.equals(""))
								option+="\n";
							option+=line;
						}
					}
				}
			}

		}
		if(!explain.equals("")) { //explanation of last question
			feedbacks.add(explain);
			explain="";
		}
		OutputFile.writeToFile(questions,options,answers,feedbacks,feedbackReq);
	}

}
