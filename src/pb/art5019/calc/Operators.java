package pb.art5019.calc;

import java.util.ArrayList;
import java.util.List;

public class Operators implements AccountDataInterface{
	private List<String> operators = new ArrayList<>();
	
	public int size() {
		return operators.size();
	}
	
	public boolean exists(int pos) {
		return size() >= pos + 1;
	}
	
	public String getOperator(int pos) {
		return operators.get(pos);
	}

	@Override
	public String getString(int pos) {
		return operators.get(pos);
	}

	@Override
	public void set(int pos, String set) {
		operators.set(pos, set);
	}

	@Override
	public void getList() {
		for(String operator: operators) {
			System.out.println(operator+"\n");
		}
	}

	@Override
	public String get(int pos) {
		return operators.get(pos);
	}

	@Override
	public void remove(int pos) {
		operators.remove(pos);
		
	}

	@Override
	public void add(String toAdd) {
		operators.add(toAdd);
		
	}

	@Override
	public void insert(int pos, String toAdd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastElementString() {
		return operators.get(operators.size()-1);
	}

	@Override
	public boolean isNull() {
		return operators.isEmpty();
	}

	@Override
	public boolean isSize(int size) {
		return size == operators.size();
	}

}
