package pb.art5019.calc;

public enum AccountState {
	OPEN(2),
	CLOSED(3),
	ORIGINAL(1);
	
	private int state;
	
	AccountState(int state) {
		this.state = state;
	}
	
	void update() {
		if(state == 3) {
			state = 1;
			return;
		}
		state++;
	}

}
