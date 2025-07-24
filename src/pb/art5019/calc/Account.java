package pb.art5019.calc;

import java.util.HashMap;

public class Account {
	public int index;
	private Numbers numbers;
	private Operators operators;
	private HashMap<Integer, Account> bracketsAccount;
	public AccountState state;
	private Account owner;

	public Account() {
		state = AccountState.ORIGINAL;
		numbers = new Numbers();
		operators = new Operators();
	}

	public Account(boolean subAccount) {
		if (subAccount) {
			state = AccountState.OPEN;
		}
	}

	public boolean isComplete() {
		return numbers.size() + bracketsAccount.size() > operators.size();
	}

	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		int operatorDisplacement = 0;
		for (int i = 0; i < numbers.size(); i++) {
			// toReturn.append("["+i+"]");
			toReturn.append(numbers.getDisplay(i));
			if (operators.exists(i + operatorDisplacement)) {
				toReturn.append(operators.getOperator(i + operatorDisplacement));
				if (bracketsAccount != null) {
					if (bracketsAccount.containsKey(i)) {
						toReturn.append("(" + bracketsAccount.get(i));
						if (state == AccountState.CLOSED) {
							toReturn.append(")");
						}
						toReturn.append(operators.getOperator(i + 1));
						operatorDisplacement++;
					}
				}
			}
		}
		return toReturn.toString();

	}

	public void addNumber(String number) {
		if (!isCreatingNumber()) {
			getCurrentAccount().numbers.add(number);
			return;
		}
		getCurrentAccount().numbers.insert(numbersSize() - 1, number);
	}

	public void addOperator(String operator) {
		if (numbers.size() > operators.size()) {
			operators.add(operator);
			return;
		}
		operators.set(operators.size(), operator);
	}
	

	public void delete() {
		if (getCurrentAccount().size() == 0 && state != AccountState.ORIGINAL) {
			deleteSelf();
			return;
		}
		if (isCreatingNumber()) {
			numbers.trimming();
			return;
		}
		operators.remove(operators.size() - 1);
	}
	

	public void setFloat() {
		numbers.setFloat(numbersSize()-1);
	}
	

	public Account getCurrentAccount() {
		// By definition, there can't be two open parenthesis at the same level, and also
		// you won't be halfway at a close Account,
		// you're always at the first OPEN bracketsAccount
		if (bracketsAccount != null) {
			for (int i = 0; i < numbers.size(); i++) {
				if (bracketsAccount.get(i).state == AccountState.OPEN) {
					return bracketsAccount.get(i).getCurrentAccount();
				}
			}
		}
		return this;
	}
	
	public void AddOpeningBrackets() {
		
	}
	

	public String calculate() {
		while (operators.size() > 0) {
			if (bracketsAccount == null || bracketsAccount.size() == 0) {
				System.out.println(toString());
				performCalculations();

			} else {
				for (int i = 0; i < numbers.size(); i++) {
					if (bracketsAccount.containsKey(i)) {
						Double result = Double.valueOf(bracketsAccount.get(i).calculate());
						numbers.insertReorder(i + 1, result);
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

	private boolean isCreatingNumber() {
		return numbers.size() > operators.size();
	}

}
