package pb.art5019.calc;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.filechooser.FileView;

public class HistoryRepository {

	private FileWriter writer;
	private File file;
	private LocalDateTime time;
	private DateTimeFormatter formatter;

	public HistoryRepository(String path) {
		file = new File(path);
		if (!file.getParentFile().exists()) {
			File parentFile = new File(file.getParent());
			parentFile.mkdir();
		}
		formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	}

	public void write(String calculation, String result) {
		if (!file.exists()) {
			try {
				System.out.println(file.getPath());
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			writer = new FileWriter(file, true);
			writer.append("(" + buildDate() + ") " + calculation + "=" + result + "\n");
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

	private String buildDate() {
		return LocalDateTime.now().format(formatter);
	}
	
	public File getFile() {
		return file;
	}
	
	

}
