package csvToSQLite;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.opencsv.CSVWriter;

public class CsvCreator {

	final private static String[] header = {"A", "B", "C", "D","E","F","G","H","I","J"};
	private Writer writer;
	private CSVWriter csvWriter;
	
	public CsvCreator(String NewfileName,String savePath) {
		//Create writer to store all the bad records 
		try {
			writer = Files.newBufferedWriter(Paths.get(savePath + NewfileName + "-bad.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		csvWriter = new CSVWriter(writer);
		//Writer header
		csvWriter.writeNext(header);
	}
	
	public void closeCsv() {
		try {
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeIn(String[] info) {
		csvWriter.writeNext(info);
	}
}
