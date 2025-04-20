package pb.art5019.calc;

import java.util.ArrayList;
import java.util.List;

public class Numbers implements Mathematics{
	private List<Double> numbers = new ArrayList<>();
	
	public String getBeforeDot(int pos) {
		String t = getString(pos);
		return t.substring(0,t.indexOf("."));
	}
	
	public String getAfterDot(int pos) {
		String t = getString(pos);
		return t.substring(t.indexOf("."),t.length());
	}
	
	public String getString(int pos) {
		if(isNull() == false) {
			return numbers.get(pos).toString();
		}
		return "";
		
	}
	
	public void set(int pos, double set) {
		numbers.set(pos, set);
	}
	
	public void getList() {
		for (Double double1 : numbers) {
			System.out.println(double1 + ";\n");
		}
	}
	
	public double get(int pos) {
		return numbers.get(pos);
	}
	
	public void remove(int pos) {
		numbers.remove(pos);
	}
	
	public int size() {
		return numbers.size();
	}
	
	public void add(Double toAdd) {
		numbers.add(toAdd);
	}
	
	public void add(String toAdd) {
		numbers.add(Double.parseDouble(toAdd));
	}
	
	public void insertBetween(int pos, String toAdd) {
		set(pos, Double.parseDouble(getBeforeDot(pos) + toAdd + getAfterDot(pos)));
	}
	
	public String getLastElementString() {
		return getString(size()-1);
	}
	
	public void trimElement(int pos) {
		set(pos, Double.parseDouble(getBeforeDot(pos).substring(0,getBeforeDot(pos).length()-1)));
	}
	
	public void trimming() {
		if(getBeforeDot((size()-1)).length() > 1) {
			trimElement(size()-1);
		}else {
			remove(size()-1);
		}
	}
	
	public boolean isNull() {
		return numbers.isEmpty();
	}
	
	public boolean isSize(int size) {
		return size == size();
	}

}
