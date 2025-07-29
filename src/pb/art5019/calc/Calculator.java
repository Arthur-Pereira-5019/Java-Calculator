
package pb.art5019.calc;

import java.util.ArrayList;
import java.util.List;

public class Calculator implements Mathematics {

	List<String> operators = new ArrayList<>();
	Numbers numbers = new Numbers();
	boolean isCreatingNumber = false;
	int operation = 0;
	HistoryRepository hr = new HistoryRepository("data/history.txt");
	
	Account baseAccount;

	public Calculator() {
		baseAccount = new Account(false,null);
	}


	public String showTheCalculation() {
		return baseAccount.toString();
	}

	public void manipulateNumbers(String button) {
		if (operatorsReference.contains(button)) {
			baseAccount.getCurrentAccount().addOperator(button);
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
			baseAccount.debugBrackets();
		}else {
			baseAccount.getCurrentAccount().addNumber(button);			
		}
		
	}

	private void addBrackets(String button) {
		if(operators.size() == numbers.size()) {
			if(button == "(") {
				operators.add(button);
				return;
			}
		}else if(operators.size() < numbers.size()) {
			if(button == ")") {
				operators.add(button);
				return;
			}
		}
	}
	
	
	
}
