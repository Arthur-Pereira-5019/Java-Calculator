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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void set(int pos, String set) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void set(int pos, String set, boolean isCalculation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String get(int pos) {
		return operators.get(pos);
	}

	@Override
	public void remove(int pos) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSize(int size) {
		// TODO Auto-generated method stub
		return false;
	}

}
