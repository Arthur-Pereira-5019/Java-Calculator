
package pb.art5019.calc;

import java.awt.Desktop;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Calculator implements Mathematics {

	List<String> operators = new ArrayList<>();
	Numbers numbers = new Numbers();
	boolean isCreatingNumber = false;
	int operation = 0;
	static HistoryRepository hr = new HistoryRepository("data/history.txt");
	Desktop desktop = Desktop.getDesktop();
	
	Account baseAccount;

	public Calculator() {
		baseAccount = new Account(false,null);
	}


	public String showTheCalculation() {
		return baseAccount.toString();
	}

	public void manipulateNumbers(String button) {
		if (operatorsReference.contains(button)) {
			baseAccount.addOperator(button);
		} else if (button == "=") {
			baseAccount.calculate();
		} else if (button == "<-") {
			baseAccount.delete();
		} else if(button == ".") {
			baseAccount.setFloat();
		} else if(button == "(") {
			baseAccount.AddOpeningBrackets();
		} else if(button == ")") {
			baseAccount.AddClosingBrackets();
		} else if(button == "C") {
			baseAccount.clear();
		}else {
			baseAccount.addNumber(button);			
		}
		
	}
	
	public void openHistory() {
		try {
			desktop.open(hr.getFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
