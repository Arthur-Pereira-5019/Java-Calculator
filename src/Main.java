import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static String operacao[] = {"4","/","2","*","3","*","11","*","5","-","4"};
	static List<String> operadores = new ArrayList<>();
	static List<Integer> numeros = new ArrayList<>();
	static List<Boolean> checks = new ArrayList<>();
	static String refoperadores = "+-*/";
	
	public static void main(String[] args) {
		
		//Garante que todos os checks são falsos
		for(int i = 0;i<4;i++) {
			checks.add(false);
		}
		
		//Separa a Operação em Operadores e Números
		for (String atual : operacao) {
			if(refoperadores.contains(atual)) {
				operadores.add(atual);
			}else {
				numeros.add(Integer.parseInt(atual));
			}
		}
		
		System.out.println(mostraAConta());
		
		//Realiza as operações
		while(operadores.size() > 0) {
			int dis = 0;
			for(int i = 0;i<operadores.size();i++) {
				dis = 0;
				int tempcalc = 0;
				if(operadores.get(i).equals("/")) {
					tempcalc = numeros.get(i)/numeros.get(i+1);
					dis = calcula(i,tempcalc,dis);
				}else if(operadores.get(i).equals("*")) {
					tempcalc = numeros.get(i)*numeros.get(i+1);
					dis = calcula(i,tempcalc,dis);
				}
				i = i - dis;
			}
			for(int i = 0;i<operadores.size();i++) {
				int tempcalc = 0;
				dis = 0;
				if(operadores.get(i).equals("+")) {
					tempcalc = numeros.get(i)+numeros.get(i+1);
					dis = calcula(i,tempcalc,dis);
				}else if(operadores.get(i).equals("-")) {
					tempcalc = numeros.get(i)-numeros.get(i+1);
					dis = calcula(i,tempcalc,dis);
				}
				i = i - dis;
			}
		}
		
	}
	
	public static int calcula(int pos, int res, int dis) {
		numeros.set(pos, res);
		numeros.remove(pos+1);
		operadores.remove(pos);
		System.out.println(mostraAConta());
		return dis+1;
	}
	public static String mostraAConta() {
		String conta;
		conta = numeros.get(0).toString();
		for (int i = 0;i<operadores.size();i++) {
			conta = conta + operadores.get(i);
			conta = conta + numeros.get(i+1).toString();
		}
		return conta;
		
	}

}
