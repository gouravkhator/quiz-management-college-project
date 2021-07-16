import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class QuizGUI extends JFrame {

	static final long serialVersionUID = 1L;
	JButton answerBtn,submitBtn;
	JPanel panel,mainPanel;
	JLabel questionLabel;
	final int WINDOW_WIDTH  = 700;
	final int WINDOW_HEIGHT = 600;
	int c = 0,x=-1,y=-1;
	List<String> temp2;
	
	public QuizGUI() {
		
		//fetch all lists
		List<String> questions = OutputFile.questionsList;
		List<String> answers = OutputFile.answersList;
		List<List<String>> options = OutputFile.optionsList;
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));  //main panel contains each panel containing options
		List<String> temp=new ArrayList<>();
		setTitle("Quiz App");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		c=0;
		for(int i=0;i<questions.size();i++) {
			panel = new JPanel(); //contains each question options and the question itself
			panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
			questionLabel = new JLabel(questions.get(i));
			panel.add(questionLabel);
			temp = options.get(i);
			for(int j=0;j<temp.size();j++) {
				answerBtn = new JButton(temp.get(j));
				int t = i;
				int t2= j;
				answerBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String actionCommand = e.getActionCommand();
						String answer= answers.get(t);
						if(!(x==t && y==t2) && actionCommand.charAt(0) == answer.charAt(answer.length()-1)) {
							c++;
							x = t;
							y= t2;
						}
					}
				});
				panel.add(answerBtn);
			}
			mainPanel.add(panel);
		}

		panel = new JPanel();
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "You got "+c+"/"+questions.size()+" correct\n"+"The output file has all correct answers with feedbacks if you opt for");
				c=0;
			}
		});
		panel.add(submitBtn);
		mainPanel.add(panel);
		JScrollPane pane = new JScrollPane(mainPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getContentPane().add(pane);
		setVisible(true);
	}
}
