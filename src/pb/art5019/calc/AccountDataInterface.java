package pb.art5019.calc;

public interface AccountDataInterface extends Mathematics {		

		
		public String getString(int pos);
		
		public void set(int pos, String set);
				
		public void getList();
		
		public Object get(int pos);
		
		public void remove(int pos);
		
		public int size();
		
		
		public void add(String toAdd);
		
		
		public void insert(int pos, String toAdd);
		
		
		public String getLastElementString();
		
		public boolean isNull();
		
		public boolean isSize(int size);


	}
