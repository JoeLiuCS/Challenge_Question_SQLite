package my_challenges;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ChallengesSqlite {
	
	
	final private static String[] header = {"A", "B", "C", "D","E","F","G","H","I","J"};
	
	//Path for save result
	final private static String savePath = "/Users/shuoqiaoliu/git/Challenge_Question_SQLite/my_challenges/src/main/resources/";
	
	//Change source files path if you need.
	final private static String sourceFilePath = 
			"/Users/shuoqiaoliu/git/Challenge_Question_SQLite/my_challenges/src/main/resources/Entry Level Coding Challenge Page 2.csv";
	
	final private static String fileName = getFileName();
	/* 
	 * Get file name by given sourceFilePath
	 */
	private static String getFileName() {
		//Get String -> "Entry Level Coding Challenge Page 2.csv"
		String[] temp = sourceFilePath.split("/");
		String fileName = temp[temp.length-1];
		//Return String -> "Entry Level Coding Challenge Page 2"
		return fileName.substring(0, fileName.length()-4);
	}
	
	
	public static void main(String[] args) throws IOException, SQLException {
		
		
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
		
		//Create Log file
		Logger logger = Logger.getLogger(ChallengesSqlite.class.getName());
		FileHandler fh = new FileHandler(savePath + fileName + ".log");
		logger.addHandler(fh);
		fh.setFormatter(new SimpleFormatter());
		int numberRecord = 0;
		int numberFailed = 0;
		int numberSuccessful = 0;
		
		//Create SQLite database file
		String my_database_url = "jdbc:sqlite:" + savePath + fileName +".db";
		Connection conn = DriverManager.getConnection(my_database_url);
		
//		while(csvUserIterator.hasNext()) {
//			CsvUser user = csvUserIterator.next();
//			if(user.isEndLine()==false) {
//				if(user.isBad()) {
//					csvWriter.writeNext(user.getInfo());
//					numberFailed +=1;
//				}
//				numberRecord +=1;
//			}
//		}
		
		numberSuccessful = numberRecord - numberFailed;
		
		logger.info("\nTotal Received: "+numberRecord+"\nNumber of Failed: "+numberFailed+"\nNumber of Successful: "+numberSuccessful);


		csvWriter.close();

	}
	

}
