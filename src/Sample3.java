import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sample3 {
	static Scanner sc;
	static boolean feedbackReq;

	public static void process(boolean feedReq) {
		feedbackReq = feedReq;
		Path currentDir = Paths.get("");
		String dir =  currentDir.toAbsolutePath().toString();
		File file =  new File(dir+"/files/Sample-3-Input.txt");
		if(dir.contains("\\"))
			file = new File(dir+"\\files\\Sample-3-Input.txt");
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
		List<String> answers= new ArrayList<>();
		List<List<String>> options = new ArrayList<>();
		List<String> optionsIndividual= new ArrayList<>();
		String line="",firstLine="",answer="",prevOption="",question="";
		char optionChar=0;
		int count=1;
		while(sc.hasNextLine()) {
			line= sc.nextLine().trim();
			if(!line.startsWith("Java Multiple Choice")) {
				boolean singleCharAnswer = line.equals("a") || line.equals("b") || line.equals("c") || line.equals("d") || line.equals("e");
				if(singleCharAnswer || line.startsWith("A) ")) {
					//line contains d as answer for question 7 and its only one character in the line

					answer = "ANSWER: "+(char)(line.charAt(line.length()-1)-32);
					answers.add(answer);
					if(!prevOption.equals("")){
						optionsIndividual.add(optionChar+". "+prevOption);
						List<String> temp = new ArrayList<>(optionsIndividual);
						options.add(temp);
						optionsIndividual.clear();
						prevOption="";
					}
				}else if(line.charAt(0)>=49 && line.charAt(0)<=57) {
					int pos = line.indexOf(')');
					if(pos!=-1) {
						question = line.substring(pos+2);
						firstLine="Question";
						count=1;  //whether the previous option is there or not reset the count if question arrives
					}
				}else {
					if(line.charAt(0)=='(' && line.charAt(2)==')') {
						if(!question.equals("")) {
							questions.add(question);
							question="";
						}
						if(!prevOption.equals("")) {
							optionsIndividual.add(optionChar+". "+prevOption);
							prevOption="";
						}
						for(int i=0;i<line.length();i++) {
							char c = line.charAt(i);
							if(c=='(' && line.charAt(i+1)>=97 && line.charAt(i+1)<=122 && line.charAt(i+2)==')'  && count<=5) {
								if(!prevOption.equals("")) {
									//if new option arrived then previous option is added

									prevOption = prevOption.trim();//as extra spaces between each option should be removed

									optionsIndividual.add((char)(prevOption.charAt(1)-32) + ". "+prevOption.substring(4));
									prevOption="";
								}
								count++;
							}
							prevOption+= ""+c;
						}
						if(!prevOption.equals("")) {
							//last option on same line
							prevOption = prevOption.trim();
							optionChar = (char)(prevOption.charAt(1)-32);
							prevOption = prevOption.substring(4);
						}
						firstLine="Option";
					}
					else {
						if(firstLine.equals("Option")) {
							//then i didn't add it to list and a new line of same option came
							if(!prevOption.equals(""))
								prevOption+="\n";
							prevOption+=line;
						}else if(firstLine.equals("Question")) {
							if(!question.equals(""))
								question+="  ";
							question+=line;
						}
					}

				}
			}
		}//end of while

		List<String> feedbacks = new ArrayList<>();
		for(int i=0;i<questions.size();i++) {
			feedbacks.add("Explanation: None");
		}

		OutputFile.writeToFile(questions, options, answers,feedbacks,feedbackReq);

	}
}
