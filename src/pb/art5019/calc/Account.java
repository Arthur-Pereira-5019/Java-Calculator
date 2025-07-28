package pb.art5019.calc;

import java.util.HashMap;

public class Account {
	public int index;
	private Numbers numbers;
	private Operators operators;
	public HashMap<Integer, Account> bracketsAccount;
	public AccountState state;
	private Account owner;

	public Account(boolean subAccount, Account owner) {
		state = AccountState.ORIGINAL;
		if (subAccount) {
			state = AccountState.OPEN;
			this.owner = owner;
			index = this.owner.accountSize()-1;
		}
		numbers = new Numbers();
		operators = new Operators();
		bracketsAccount = new HashMap<>();
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
						if (bracketsAccount.get(i).state == AccountState.CLOSED) {
							toReturn.append(")");
						}
						if(operators.exists(i+1)) {
							toReturn.append(operators.getOperator(i+i));
						}
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
		int i = bracketsAccount.size();;
		if (numbers.size() + i > operators.size()) {
			operators.add(operator);
			return;
		}
		operators.set(operators.size(), operator);
	}
	

	public void delete() {
		if(getCurrentAccount().state == AccountState.ORIGINAL && numbers.isNull() && bracketsAccount.isEmpty()) {
			return;
		}
		if(getSubAccountAt() != null) {
			getSubAccountAt().state = AccountState.OPEN;
			return;
		}
		if (getCurrentAccount().size() == 0 && getCurrentAccount().state != AccountState.ORIGINAL) {
			getCurrentAccount().deleteSelf();
			return;
		}
		if (isCreatingNumber()) {
			getNumbers().trimming();
			return;
		}
		getOperators().removeLast();
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
				if(bracketsAccount.get(i) != null) {
				if (bracketsAccount.get(i).state == AccountState.OPEN) {
					return bracketsAccount.get(i).getCurrentAccount();
				}
				}
			}
		}
		return this;
	}
	
	private Account getSubAccountAt() {
		//Used to work with Closed Account
		return getCurrentAccount().bracketsAccount.get(getCurrentAccount().size()-1);
	}
	
	
	public void AddOpeningBrackets() {
		//Maybe size should consider the number of brackets too
		getCurrentAccount().bracketsAccount.put(accountSize()-1, new Account(true,this));
	}
	
	public void AddClosingBrackets() {
		if(getCurrentAccount().state == AccountState.OPEN) {
			getCurrentAccount().state = AccountState.CLOSED;
		}
		
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
	
	private int accountSize() {
		return numbers.size() + bracketsAccount.size();
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
		return getNumbers().size() > getOperators().size();
	}
	
	private Numbers getNumbers() {
		return getCurrentAccount().numbers;
	}
	
	private Operators getOperators() {
		return getCurrentAccount().operators;
	}

}
