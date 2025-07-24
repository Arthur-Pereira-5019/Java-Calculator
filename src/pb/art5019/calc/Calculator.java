
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
		baseAccount = new Account();
	}


	public String showTheCalculation() {
		return baseAccount.toString();
	}

	public void manipulateNumbers(String button) {
		if (operatorsReference.contains(button)) {
			isCreatingNumber = baseAccount.addOperator(button, isCreatingNumber, operation);
		} else if (button == "=") {
			baseAccount.calculate();
		} else if (button == "<-") {
			operation = baseAccount.numbersSize() - 1;
			isCreatingNumber = baseAccount.delete(button, operation, isCreatingNumber);
			
		} else if(button == ".") {
			baseAccount.setFloat(operation-1);
		} else if(brackets.contains(button)) {
			addBrackets(button);
		}else {
			baseAccount.addNumber(button, isCreatingNumber, operation);
			if(!isCreatingNumber) {
				operation++;
			}
			isCreatingNumber = true;
			
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
	
	private void delete(String button) {
		operation = numbers.size() - 1;
		if (isCreatingNumber) {
			numbers.trimming();
			if (numbers.isNull() || numbers.isSize(operation)) {
				isCreatingNumber = false;
			}
		} else {
			operators.remove(operators.size() - 1);
			isCreatingNumber = true;
		}
	}
	
	private void addNumber(String button) {
		
	}
	
	
}
