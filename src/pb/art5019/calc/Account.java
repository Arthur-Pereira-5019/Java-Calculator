package pb.art5019.calc;

import java.util.HashMap;

public class Account {
	public int index;
	public Numbers numbers;
	public Operators operators;
	public HashMap<Integer,Account> bracketsAccount;
	
	
	public boolean isComplete() {
		return numbers.size() + bracketsAccount.size() > operators.size();
	}
	
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		int operatorDisplacement = 0;
		for(int i = 0; i < numbers.size(); i++) {
			//toReturn.append("["+i+"]");
			toReturn.append(numbers.get(i));
			if(operators.exists(i+operatorDisplacement)) {
				toReturn.append(operators.getOperator(i+operatorDisplacement));
				if(bracketsAccount != null) {
					if(bracketsAccount.containsKey(i)) {
						toReturn.append("("+bracketsAccount.get(i)+")");
						toReturn.append(operators.getOperator(i+1));
						operatorDisplacement++;
					}
				}
			}
		}
		return toReturn.toString();
		
	}
	
	public void addNumber(String number) {
		numbers.add(number);
	}
	
	public void addOperator(String operator) {
		operators.add(operator);
	}
	
	public String calculate() {
		boolean canReturn = false;
		while(!canReturn) {
			if(operators.size() > 0) {
				
			}
			return numbers.getString(0);
		}
			
		return "a";
	}

}
