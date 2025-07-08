package pb.art5019.calc;

import java.util.HashMap;

public class Account {
	public int index;
	public Numbers numbers;
	public Operators operators;
	public HashMap<Integer,Account> bracketsAccount;
	public AccountState state;
	
	public Account() {
		state = AccountState.ORIGINAL;
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
	
	public void addNumber(String number) {
		numbers.add(number);
	}
	
	public void addOperator(String operator) {
		operators.add(operator);
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
	
	public Double performCalculations() {

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
	
	public int updateTheArrays(int pos, double res, int dis) {
		numbers.set(pos, String.valueOf(res), true);
		numbers.remove(pos + 1);
		operators.remove(pos);
		return dis + 1;
	}

}
