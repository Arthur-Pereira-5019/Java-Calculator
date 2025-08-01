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
	
	public boolean exists(int pos) {
		return size() >= pos + 1;
	}
	
	public void set(int pos, String set) {
		if(set.isEmpty()) {
			numbers.remove(pos);
			return;
		}
		Double newSet = Double.valueOf(set);
		numbers.set(pos, newSet);
	}
	
	public void set(int pos, String set, boolean isCalculation) {
		Double newSet = Double.valueOf(set);
		System.out.println(newSet);
		if(isCalculation && size() == 2) {
			
		}
		if(!isInteger(newSet)) {
			dotMarkers.set(pos, true);
			firstDecimal.set(pos, true);
			newSet = treatDouble(newSet);
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
		dotMarkers.remove(pos);
		firstDecimal.remove(pos);
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
		String base = getString(pos);
		String toSet;
		if(base.substring(base.length()-2,base.length()).equals(".0")) {
			toSet = base.substring(0,base.length()-3);
		} else {
			toSet = base.substring(0,base.length()-1);
		}
		if(toSet.equals("-")) {
			toSet = toSet.replace("-", "");
		}
		if(isInteger(Double.valueOf(toSet))) {
			dotMarkers.set(pos, false);
			firstDecimal.set(pos, false);
		}
		set(pos, toSet);
		
	}
	
	public void trimming() {
		int p = size()-1;
		if(getString(p).length() > 3) {
			trimElement(p);
		}else {
			remove(p);
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
		dotMarkers.add(false);
		firstDecimal.add(false);
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
	
	private Double treatDouble(Double d) {
		String dS = d.toString();
		int dP = dS.indexOf(dS);
		if(dS.substring(dP,dS.length()).length() > 6) {
			return Double.valueOf(dS.substring(dP,dP+7));
		}
		return d;
	}


}
