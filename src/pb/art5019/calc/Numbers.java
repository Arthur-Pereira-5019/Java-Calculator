package pb.art5019.calc;

import java.util.ArrayList;
import java.util.List;

public class Numbers implements AccountDataInterface{
	private List<Double> numbers = new ArrayList<>();
	
	private List<Boolean> dotMarkers = new ArrayList<>(); 
	private List<Boolean> firstDecimal = new ArrayList<>(); //Used for better displaying the number.
	
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
	
	public void set(int pos, String set) {
		Double newSet = Double.valueOf(set);
		numbers.set(pos, newSet);
	}
	
	public void set(int pos, String set, boolean isCalculation) {
		Double newSet = Double.valueOf(set);
		if(isCalculation && numbers.size() == 1) {
			resetFloatStructure();
			dotMarkers.add(false);
			firstDecimal.add(false);
			if(isInteger(newSet)) {
				dotMarkers.add(true);
				firstDecimal.add(true);
			}
		}
		numbers.set(pos, newSet);
	}
	
	public void getList() {
		for (Double double1 : numbers) {
			System.out.println(double1 + ";\n");
		}
	}
	
	public Double get(int pos) {
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
		dotMarkers.add(false);
		firstDecimal.add(false);
	}
	
	public void add(String toAdd) {
		numbers.add(Double.parseDouble(toAdd));
		dotMarkers.add(false);
		firstDecimal.add(false);
	}
	
	public boolean isInteger(double test) {
		return (test % 1 == 0);
	}
	
	public void insertBetween(int pos, String toAdd) {
		set(pos, getBeforeDot(pos) + toAdd + getAfterDot(pos));
	}
	
	public void insert(int pos, String toAdd) {
		if(dotMarkers.get(pos) == true) {
			if(firstDecimal.get(pos) == false) {
				numbers.set(pos, Double.parseDouble(getBeforeDot(pos) + "." + toAdd));
				firstDecimal.set(pos, true);
			}else {
				insertAfter(pos, toAdd);
			}
			
		}else {
			insertBetween(pos, toAdd);
		}
	}
	
	public void setFloat(int pos) {
		dotMarkers.set(pos, true);
	}
	
	public void insertAfter(int pos, String toAdd) {
		set(pos, getBeforeDot(pos) + getAfterDot(pos) + toAdd);
	}
	
	public String getLastElementString() {
		return getString(size()-1);
	}
	
	public void trimElement(int pos) {
		set(pos, getBeforeDot(pos).substring(0,getBeforeDot(pos).length()-1));
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
	
	public boolean isFloat(int pos) {
		return dotMarkers.get(pos);
	}
	
	public String getDisplay(int pos) {
		if(isFloat(pos)) {
			if(firstDecimal.get(pos)) {
				return getString(pos);
			}
			return getBeforeDot(pos)+".";
		}
		return getBeforeDot(pos);
	}
	
	public void resetFloatStructure() {
		dotMarkers.clear();
		firstDecimal.clear();
	}
	
	public void insertReorder(int pos, double toAdd) {
		numbers.add(0.0);
		dotMarkers.add(false);
		firstDecimal.add(false);
		for(int i = numbers.size()-2; i>=pos;i--) {
			numbers.set(i+1, numbers.get(i));
			dotMarkers.set(i+1, dotMarkers.get(i));
			firstDecimal.set(i+1, firstDecimal.get(i));
		}
		numbers.set(pos, toAdd);
	}


}
