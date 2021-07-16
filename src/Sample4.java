import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sample4 {
	static Scanner sc;
	static boolean feedbackReq;
	public static void process(boolean feedReq) {
		feedbackReq = feedReq;
		Path currentDir = Paths.get("");
		String dir =  currentDir.toAbsolutePath().toString();
		File file = new File(dir+"/files/Sample-4-Input.txt");
		if(dir.contains("\\"))
			file = new File(dir+"\\files\\Sample-4-Input.txt");
		try {
			sc = new Scanner(file);
			fetch();
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Input Files Missing");
		}
	}
	
	private static void fetch() {
		List<String> questions=new ArrayList<>();
		List<String> optionsIndividual = new ArrayList<>();
		List<List<String>> options = new ArrayList<>();
		List<String> answers = new ArrayList<>();
		String answer="",line="",option="",firstLine="",question="";
		while(sc.hasNextLine()){
			line = sc.nextLine();
			if(!line.trim().equals("")){
				if(line.equals("Answers")){
					if(!option.equals("")){
						//add options of last question
						optionsIndividual.add(option);
						List<String> temp = new ArrayList<>(optionsIndividual);
						optionsIndividual.clear();
						option="";
						options.add(temp);
					}
					firstLine="Answers";
					break;
				}else if(line.charAt(0)>=49 && line.charAt(0)<=57){
					if(!question.equals("")){
						//question is not appended so this line is a part of question starting with a digit
						question+="  "+line;
					}
					else{
						if(!option.equals("")){
							//add last option on encounter of new question
							optionsIndividual.add(option);
							List<String> temp = new ArrayList<>(optionsIndividual);
							optionsIndividual.clear();
							option="";
							options.add(temp);
						}
						int pos = line.indexOf(".");
						question = line.substring(pos+2);
						firstLine = "Question";
					}
				}else if(line.charAt(0)>=65 && line.charAt(0)<=90 && line.charAt(1)==')'){
					if(!question.equals("")){
						//add question on first option encounter
						questions.add(question);
						question="";
					}
					if(!option.equals("")){
						optionsIndividual.add(option);
						option="";
					}
					option=line.charAt(0)+". "+line.substring(3);
					firstLine = "Option";
				}else{
					if(firstLine.equals("Option")){
						if(!option.equals(""))
							option+="\n";
						option+=line;
					}else if(firstLine.equals("Question")){
						if(!question.equals(""))
							question+="  ";
						question+=line;
					}
				}
			}
		} //end of while loop

		while(sc.hasNextLine()) {
			//all answers are added at the end
			line= sc.nextLine();
			if(firstLine.equals("Answers")) {
				int pos = line.indexOf(")");
				answer = "ANSWER: "+line.charAt(pos-1);
				answers.add(answer);
				answer="";
			}
		}
		List<String> feedbacks = new ArrayList<>();
		for(int i=0;i<questions.size();i++){
			feedbacks.add("Explanation: None");
		}

		
		OutputFile.writeToFile(questions, options, answers, feedbacks,feedbackReq);

	}
}
