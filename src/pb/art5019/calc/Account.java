package pb.art5019.calc;

import java.util.HashMap;

public class Account {
	public int index;
	private Numbers numbers;
	private Operators operators;
	private HashMap<Integer,Account> bracketsAccount;
	public AccountState state;
	private Account owner;
	
	public Account() {
		state = AccountState.ORIGINAL;
		numbers = new Numbers();
		operators = new Operators();
	}
	
	public Account(boolean subAccount) {
		if(subAccount) {
			state = AccountState.OPEN;
		}
	}
	
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
						toReturn.append("("+bracketsAccount.get(i));
						if(state == AccountState.CLOSED) {
							toReturn.append(")");
						}
						toReturn.append(operators.getOperator(i+1));
						operatorDisplacement++;
					}
				}
			}
		}
		return toReturn.toString();
		
	}
	
	public void addNumber(String number, boolean isCreatingNumber, int operation) {
		if (isCreatingNumber == false) {
			getAccountAtPos(size()).numbers.add(number);
			return;
		}
		getAccountAtPos(size()).numbers.insert(operation - 1, number);
	}
	
	public boolean addOperator(String operator, boolean isCreatingNumber, int operation) {
		if (numbers.size() > operators.size()) {
			operators.add(operator);
			return false;
		}
		operators.set(operators.size(), operator);
		return true;
	}
	
	
	public boolean delete(String button, int operation, boolean isCreatingNumber) {
		if(getAccountAtPos(operation).size() == 0 && state != AccountState.ORIGINAL) {
			deleteSelf();
		}
		if (isCreatingNumber) {
			numbers.trimming();
			if (numbers.isNull() || numbers.isSize(operation)) {
				return false;
			}
		}else {
			operators.remove(operators.size() - 1);
			return true;
		}
		return isCreatingNumber;
	}
	
	
	public void setFloat(int pos) {
		numbers.setFloat(pos);
	}
	
	public Account getAccountAtPos(int index) {
		//By definition, there can't be two open parenthesis at the same level, so you're always at the first OPEN bracketsAccount
		if(bracketsAccount != null) {
			for(int i = 0;i<numbers.size();i++) {
				if(bracketsAccount.get(i).state == AccountState.OPEN) {
					return bracketsAccount.get(i).getAccountAtPos(bracketsAccount.get(i).size());
				}
			}
		}
		return this;
	}
		
	public String calculate() {
		while(operators.size() > 0) {
				if(bracketsAccount == null || bracketsAccount.size() == 0) {
					System.out.println(toString());
					performCalculations();
					
				}else {
					for(int i = 0; i < numbers.size(); i++) {
						if(bracketsAccount.containsKey(i)) {
							Double result = Double.valueOf(bracketsAccount.get(i).calculate());
							numbers.insertReorder(i+1, result);
							bracketsAccount.remove(i);
						}
					}
				}
		}
		return numbers.getString(0);
	}
	
	private Double performCalculations() {

		while (operators.size() > 0) {
			int dis = 0;
			for (int i = 0; i < operators.size(); i++) {
				dis = 0;
				double tempcalc = 0;
				if (operators.get(i).equals("/")) {
					tempcalc = numbers.get(i) / numbers.get(i + 1);
					dis = updateTheArrays(i, tempcalc, dis);
				} else if (operators.get(i).equals("*")) {
					tempcalc = numbers.get(i) * numbers.get(i + 1);
					dis = updateTheArrays(i, tempcalc, dis);
				}
				i = i - dis;
			}
			for (int i = 0; i < operators.size(); i++) {
				double tempcalc = 0;
				dis = 0;
				if (operators.get(i).equals("+")) {
					tempcalc = numbers.get(i) + numbers.get(i + 1);
					dis = updateTheArrays(i, tempcalc, dis);
				} else if (operators.get(i).equals("-")) {
					tempcalc = numbers.get(i) - numbers.get(i + 1);
					dis = updateTheArrays(i, tempcalc, dis);
				}
				i = i - dis;
			}
		}
				
		return numbers.get(0);

	}
	
	private int updateTheArrays(int pos, double res, int dis) {
		numbers.set(pos, String.valueOf(res), true);
		numbers.remove(pos + 1);
		operators.remove(pos);
		return dis + 1;
	}
	
	private int size() {
		return numbers.size();
	}
	
	public int numbersSize() {
		return numbers.size();
	}
	
	public Account getOwner() {
		return owner;
	}
	
	public void deleteSelf() {
		getOwner().bracketsAccount.remove(index);
	}

}
