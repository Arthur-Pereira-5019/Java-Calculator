package pb.art5019.calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends Sistema implements ActionListener{
	JFrame calc = new JFrame();
	List<JButton> buttons = new ArrayList<>();
	int nextJump = 0;
	int linha = 0;
	int coluna = 0;
	int operacao = 0;
	String toCalc = "";
	boolean criandoNumero = false;
	
	List<Character> operands = new ArrayList<>();

	
	void abricACalc() {
		for(int i = 0; i < refoperadores.length();i++) {
			operands.add(refoperadores.charAt(i));
		}
		
		for(int i = 0;i<10;i++) {
			buttons.add(new JButton("" + i));
		}
		for(int i = 0;i<operands.size();i++) {
			buttons.add(new JButton(""+operands.get(i)));
		}
		buttons.add(new JButton("="));
		
		for(int i = 0;i<buttons.size();i++) {
			coluna++;
			if(nextJump == 0) {
				linha++;
				coluna = 0;
				nextJump = 4;
			}
			buttons.get(i).setBounds(20+coluna*90,80+linha*80,80,75);
			buttons.get(i).addActionListener(this);
			calc.add(buttons.get(i));
			nextJump--;
		}
		JLabel mostrador = new JLabel();
		mostrador.setText(toCalc);
		mostrador.setBounds(100,0,200,200);
		calc.add(mostrador);
		
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calc.setSize(400,600);
		calc.setLayout(null);
		calc.setVisible(true);
		calc.setResizable(false);
		calc.setLocationRelativeTo(null);
		
		for (JButton botao : buttons) {
			botao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e ) {
					if(refoperadores.contains(botao.getText()) && refoperadores.contains(toCalc.substring(toCalc.length()-1))) {
						toCalc = toCalc.substring(0,toCalc.length()-1) + botao.getText();
					}else if(botao.getText() == "=") {
						System.out.println("Operação finalizada, enviar para o módulo de calcular.");
						calcula();
					}
					else {
						if(algarismos.contains(botao.getText())) {
							if(criandoNumero == false) {
								criandoNumero = true;
								numeros.add(Integer.parseInt(botao.getText()));
								operacao++;
							}else {
								numeros.set(operacao, Integer.parseInt(numeros.get(operacao)+botao.getText()));
							}
						}else {
							if(criandoNumero = true) {
								criandoNumero = false;
							}
							operadores.add(botao.getText());
						}
						toCalc = toCalc+botao.getText();
					}
					System.out.println(toCalc);
					mostrador.setText(toCalc);
					calc.repaint();
				}
			});
		}
	
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
