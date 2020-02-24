package my_challenges;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ChallengesSqlite {
	
	final private static String[] header = {"A", "B", "C", "D","E","F","G","H","I","J"};
	
	//Path for save result
	final private static String savePath = "/Users/shuoqiaoliu/eclipse-workspace/my_challenges/src/main/resources/";
	
	//Change source files path if you need.
	final private static String sourceFilePath = 
			"/Users/shuoqiaoliu/eclipse-workspace/my_challenges/src/main/resources/Entry Level Coding Challenge Page 2.csv";
	
	final private static String fileName = getFileName();
	/*
	 * Get file name by given sourceFilePath
	 */
	private static String getFileName() {
		String[] temp = sourceFilePath.split("/");
		String fileName = temp[temp.length-1];
		return fileName.substring(0, fileName.length()-4);
	}
	
	
	public static void main(String[] args) throws IOException {
		
		
		//Get read source file
		Reader reader = Files.newBufferedReader(Paths.get(sourceFilePath));
		CsvToBean<CsvUser> csvToBean = new CsvToBeanBuilder<CsvUser>(reader)
				.withType(CsvUser.class)
				.withIgnoreLeadingWhiteSpace(true)
				.build();
		
		//The source file will be extremely large, 
		//Use Iterator only read one bean at a time.
		Iterator<CsvUser> csvUserIterator = csvToBean.iterator();
		
		
		//Create writer to store all the bad records 
		Writer writer = Files.newBufferedWriter(Paths.get(savePath + fileName + "-bad.csv"));
		CSVWriter csvWriter = new CSVWriter(writer);
		
		//Writer header
		csvWriter.writeNext(header);
		
		while(csvUserIterator.hasNext()) {
			CsvUser user = csvUserIterator.next();
			
			if(user.isBad()) {
				System.out.println(Arrays.toString(user.getInfo()));
				csvWriter.writeNext(user.getInfo());
			
			}
			
		}
		
		csvWriter.close();

	}
	

}
