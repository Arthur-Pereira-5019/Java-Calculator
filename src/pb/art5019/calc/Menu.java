package pb.art5019.calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends Calculator implements ActionListener{
	JFrame calc = new JFrame("Art5019's Calculator");
	List<JButton> buttons = new ArrayList<>();
	int nextJump = 0;
	int row = 0;
	int column = 0;
	int operation = 0;
	String toDisplay = "Welcome to Art5019's Calculator";
	boolean isCreatingNumber = false;
	JLabel display = new JLabel();
	
	List<Character> operands = new ArrayList<>();

	
	void openTheCalc() {
		buildTheCalculator();
		buildTheButtons();
	}

	
	void buildTheCalculator() {
		for(int i = 0; i < operatorsReference.length();i++) {
			operands.add(operatorsReference.charAt(i));
		}
		
		for(int i = 0;i<10;i++) {
			buttons.add(new JButton("" + i));
		}
		for(int i = 0;i<operands.size();i++) {
			buttons.add(new JButton(""+operands.get(i)));
		}
		buttons.add(new JButton("="));
		buttons.add(new JButton("<-"));
		
		for(int i = 0;i<buttons.size();i++) {
			column++;
			if(nextJump == 0) {
				row++;
				column = 0;
				nextJump = 4;
			}
			buttons.get(i).setBounds(20+column*90,80+row*80,80,75);
			buttons.get(i).addActionListener(this);
			calc.add(buttons.get(i));
			nextJump--;
		}
		display.setText(toDisplay);
		display.setBounds(100,0,200,200);
		calc.add(display);	
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calc.setSize(400,600);
		calc.setLayout(null);
		calc.setVisible(true);
		calc.setResizable(false);
		calc.setLocationRelativeTo(null);
	}
	
	void buildTheButtons() {
		for (JButton botao : buttons) {
			botao.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e ) {
					manipulateNumbers(botao.getText());
					display.setText(showTheCalculation());
					calc.repaint();
				}
			});
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
