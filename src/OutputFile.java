import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class OutputFile {

	public static List<String> questionsList,answersList,feedbacksList;
	public static List<List<String>> optionsList;
	public static void writeToFile(List<String> questions, List<List<String>> options, List<String> answers,
			List<String> feedbacks,boolean feedbackReq) {
		questionsList = questions;
		answersList = answers;
		feedbacksList = feedbacks;
		optionsList = options;
		Path currentDir = Paths.get("");
		String dir =  currentDir.toAbsolutePath().toString();
		try {
			FileWriter fwrite =  new FileWriter(dir+"/output.txt");;
			if(dir.contains("\\"))
				fwrite = new FileWriter(dir+"\\output.txt");
			
			for(int i=0;i<questions.size();i++) {
				fwrite.append(questions.get(i)+"\n");
				List<String> optionsTemp = options.get(i);
				for(int j=0;j<optionsTemp.size();j++) {
					fwrite.append(optionsTemp.get(j)+"\n");
				}
				fwrite.append(answers.get(i)+"\n");
				if(feedbackReq) {
					fwrite.append(feedbacks.get(i)+"\n");
				}
			}
			fwrite.close();
		} catch (Exception e) {
			System.out.println("Output File couldn't be written due to some problems");
		}
		
	}
	
	
}
