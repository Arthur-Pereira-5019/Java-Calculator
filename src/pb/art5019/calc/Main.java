package pb.art5019.calc;

import java.util.HashMap;

public class Main {
	
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		
		menu.openTheCalc();
		
		Numbers numbers = new Numbers();
		numbers.add("0");
		numbers.add("2");
		numbers.add("3");
		numbers.add("4");
		numbers.add("5");
		
		Operators operators = new Operators();
		operators.add("+");
		operators.add("-");
		operators.add("/");
		operators.add("*");
		operators.add("*");
		
		Numbers numbers2 = new Numbers();
		numbers2.add("11");
		numbers2.add("15");
		
		Operators operators2 = new Operators();
		operators2.add("*");


		Account subaccount1 = new Account();
		subaccount1.operators = operators2;
		subaccount1.numbers = numbers2;

		Account account = new Account();
		account.operators = operators;
		account.numbers = numbers;
		account.bracketsAccount = new HashMap<>();
		account.bracketsAccount.put(1, subaccount1);
		
		
		
		System.out.println(account.toString());
		
		
	}

}
