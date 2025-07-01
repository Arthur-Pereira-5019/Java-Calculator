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
			if(operators.size() == 0) {
				canReturn = true;
			}else {
				if(bracketsAccount != null) {
					for(int i = 0; i < numbers.size(); i++) {
						if(bracketsAccount.containsKey(i)) {
							Double.valueOf(bracketsAccount.get(i).calculate());
							
						}
					}
				}
				
			}
			
			return numbers.getString(0);
		}
			
		return "a";
	}
	
	public double performCalculations() {
		while (operators.size() > 0) {
			int dis = 0;
			for (int i = 0; i < operators.size(); i++) {
				dis = 0;
				double tempcalc = 0;
				if (operators.get(i).equals("/")) {
					tempcalc = numbers.get(i) / numbers.get(i + 1);
					System.out.println(tempcalc);
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
		System.out.println(res);
		numbers.set(pos, String.valueOf(res), true);
		numbers.remove(pos + 1);
		operators.remove(pos);
		return dis + 1;
	}

}
