import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sample2 {
	static Scanner sc;
	static boolean feedbackReq;
	public static void process(boolean feedReq) {
		feedbackReq = feedReq;
		Path currentDir = Paths.get("");
		String dir =  currentDir.toAbsolutePath().toString();
		File file =  new File(dir+"/files/Sample-2-Input.txt");
		if(dir.contains("\\"))
			file = new File(dir+"\\files\\Sample-2-Input.txt");
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
		List<List<String>> options= new ArrayList<>(); //list of list of option to separate each question
		List<String> feedbacks = new ArrayList<>();
		List<String> optionsIndividual = new ArrayList<>(); //options for each question
		
		String line="",firstLine="",explain="",option="",question="";
		char answer,optionChar=0;
		while(sc.hasNextLine()) {
			line = sc.nextLine().trim();
			if(!line.equals("") && !line.startsWith("Answer & Solution")) {
				if(line.startsWith("Answer: ")) {
					answer = line.charAt(line.indexOf("Option")+7); //last character was a space so character before it is answer
					answers.add("ANSWER: "+answer);
					
					if(!option.equals("")) {  //last option remaining of previous question
						optionsIndividual.add(optionChar+". "+option);
						List<String> temp = new ArrayList<>(optionsIndividual);
						options.add(temp);  
						//created new list as we cleared optionsIndividual and so it clears from options also as its passed by reference 
						optionsIndividual.clear();
						option="";
					}
				}else if(line.startsWith("No explanation is given")) {
					feedbacks.add("Explanation: None");
				}else if(line.startsWith("Solution:")) {
					firstLine = "Explain";
				}else if(line.charAt(0)>=49 && line.charAt(0)<=57){
					int pos = line.indexOf('.');
					if(pos!=-1) {
						if(!explain.equals("")) { //explanation of previous question
							feedbacks.add("Explanation: "+explain);
							explain="";
						}
						firstLine = "Question";
						optionsIndividual.clear();
					}else {
						//can be option starting with a digit
						if(!question.equals("")) { //question line is done
							questions.add(question);
							question="";
						}
						if(!option.equals(""))
							option+="\n";
						option+=line;
						firstLine="Option";
					}
				}else {
					if(line.charAt(0)>=65 && line.charAt(0)<=90 && line.charAt(1)=='.') {
						//option
						if(!question.equals("")) { //question line is done
							questions.add(question);
							question="";
						}
						if(!option.equals("")) { //option is there then it is the previous question option
							optionsIndividual.add(optionChar+". "+option);
							option="";
						}
						//optionChar is saved bcoz we have to check if real option is there so we segregate that option character
						optionChar = line.charAt(0);
						firstLine="Option";
						
					}else {
						//same question or explanation or option on next line
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
