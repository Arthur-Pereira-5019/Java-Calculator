package pb.art5019.calc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HistoryRepository {
	
	private FileWriter writer;
	private File file;

	public HistoryRepository(String path) {
			file = new File(path);
	}
	
	public void write(String calculation, String result) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
		writer = new FileWriter(file);
			writer.append(calculation + "=" + result + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
