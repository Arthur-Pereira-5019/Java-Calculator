package pb.art5019.calc;

import java.util.ArrayList;
import java.util.List;

public class Operators {
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

}
