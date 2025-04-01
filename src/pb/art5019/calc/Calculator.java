package pb.art5019.calc;
import java.util.ArrayList;
import java.util.List;

public class Calculator implements Mathematics{
	
	List<String> operators = new ArrayList<>();
	List<Integer> numbers = new ArrayList<>();
	
	public int calculate() {
		System.out.println(showTheCalculation());
		while(operators.size() > 0) {
			int dis = 0;
			for(int i = 0;i<operators.size();i++) {
				dis = 0;
				int tempcalc = 0;
				if(operators.get(i).equals("/")) {
					tempcalc = numbers.get(i)/numbers.get(i+1);
					dis = updateTheCalculation(i,tempcalc,dis);
				}else if(operators.get(i).equals("*")) {
					tempcalc = numbers.get(i)*numbers.get(i+1);
					dis = updateTheCalculation(i,tempcalc,dis);
				}
				i = i - dis;
			}
			for(int i = 0;i<operators.size();i++) {
				int tempcalc = 0;
				dis = 0;
				if(operators.get(i).equals("+")) {
					tempcalc = numbers.get(i)+numbers.get(i+1);
					dis = updateTheCalculation(i,tempcalc,dis);
				}else if(operators.get(i).equals("-")) {
					tempcalc = numbers.get(i)-numbers.get(i+1);
					dis = updateTheCalculation(i,tempcalc,dis);
				}
				i = i - dis;
			}
		}
		return numbers.get(0);
		
	}
	
	public int updateTheCalculation(int pos, int res, int dis) {
		numbers.set(pos, res);
		numbers.remove(pos+1);
		operators.remove(pos);
		System.out.println(showTheCalculation());
		return dis+1;
	}
	
	public String showTheCalculation() {
		String calculation;
		if(numbers.size() > 0) {
			calculation = numbers.get(0).toString();
			if(operators.size() > 0) {
				calculation = numbers.get(0).toString();
				for (int i = 0;i<operators.size();i++) {
					calculation = calculation + operators.get(i);
					if (numbers.size() == i) {
						calculation = calculation + numbers.get(i+1).toString();
					}
				}
			}
			return calculation;
		}
		return "0";
		
	}

}
