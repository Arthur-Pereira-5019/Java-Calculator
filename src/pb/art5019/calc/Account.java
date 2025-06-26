package pb.art5019.calc;

import java.util.HashMap;

public class Account {
	private int index;
	private Numbers numbers;
	private Operators operators;
	private HashMap<Integer,Account> bracketsAccount;
	
	public boolean isComplete() {
		return numbers.size() + bracketsAccount.size() > operators.size();
	}
	
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		for(int i = 0; i < numbers.size(); i++) {
			toReturn.append(numbers.get(0));
			if(operators.exists(i)) {
				toReturn.append(operators.getOperator(i));
				if(bracketsAccount.size() > 0) {
					bracketsAccount.get(i);
				}
			}
		}
		return toReturn.toString();
		
	}
	
	public void addNumber() {
		
	}
	
	public void addOperator() {
		
	}

}
