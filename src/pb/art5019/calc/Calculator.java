
package pb.art5019.calc;

import java.util.ArrayList;
import java.util.List;

public class Calculator implements Mathematics {

	List<String> operators = new ArrayList<>();
	Numbers numbers = new Numbers();
	boolean isCreatingNumber = false;
	int operation = 0;
	HistoryRepository hr = new HistoryRepository("data/history.txt");


	public int updateTheCalculation(int pos, double res, int dis) {
		System.out.println(res);
		numbers.set(pos, String.valueOf(res), true);
		numbers.remove(pos + 1);
		operators.remove(pos);
		System.out.println(showTheCalculation());
		return dis + 1;
	}

	public String showTheCalculation() {
		String calculation;
		if (numbers.size() <= 0) {
			return "";
		}
		calculation = numbers.getDisplay(0);
		for (int i = 0; i < operators.size(); i++) {
			calculation = calculation + operators.get(i);
			if (numbers.size() > i + 1) {
				if(numbers.isFloat(i + 1)) {
					calculation = calculation + numbers.getDisplay(i + 1);
				}else {
					calculation = calculation + numbers.getDisplay(i + 1);
				}				
			} else {
				break;
			}
		}
		return calculation;
	}

	public void manipulateNumbers(String button) {
		if (operatorsReference.contains(button)) {
			addOperator(button);
		} else if (button == "=") {
			calculate();
		} else if (button == "<-") {
			delete(button);
		} else if(button == ".") {
			numbers.setFloat(operation-1);
		} else if(brackets.contains(button)) {
			addBrackets(button);
		}
		else {
			addNumber(button);
		}
	}
	
	public double calculate() {
		String preCalculation = showTheCalculation();
		while (operators.size() > 0) {
			int dis = 0;
			for (int i = 0; i < operators.size(); i++) {
				dis = 0;
				double tempcalc = 0;
				if (operators.get(i).equals("/")) {
					tempcalc = numbers.get(i) / numbers.get(i + 1);
					System.out.println(tempcalc);
					dis = updateTheCalculation(i, tempcalc, dis);
				} else if (operators.get(i).equals("*")) {
					tempcalc = numbers.get(i) * numbers.get(i + 1);
					dis = updateTheCalculation(i, tempcalc, dis);
				}
				i = i - dis;
			}
			for (int i = 0; i < operators.size(); i++) {
				double tempcalc = 0;
				dis = 0;
				if (operators.get(i).equals("+")) {
					tempcalc = numbers.get(i) + numbers.get(i + 1);
					dis = updateTheCalculation(i, tempcalc, dis);
				} else if (operators.get(i).equals("-")) {
					tempcalc = numbers.get(i) - numbers.get(i + 1);
					dis = updateTheCalculation(i, tempcalc, dis);
				}
				i = i - dis;
			}
		}
		operation = 1; // Grants that the next calculation won't try to edit the array out of the last place
		hr.write(preCalculation, String.valueOf(numbers.get(0)));
		return numbers.get(0);

	}

	
	private void addOperator(String button) {
		if (numbers.size() > operators.size()) {
			operators.add(button);
			isCreatingNumber = false;
		} else {
			operators.set(operators.size(), button);
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
		if (isCreatingNumber == false) {
			isCreatingNumber = true;
			numbers.add(button);
			operation++;
		} else {
			numbers.insert(operation - 1, button);
		}
	}
	
	
}
