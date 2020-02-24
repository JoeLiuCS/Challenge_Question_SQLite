package my_challenges;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ChallengesSqlite {
	
	
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
		
		
		//Write all the bad records 
		CsvCreator myCsvCreator = new CsvCreator(fileName,savePath);
		
		int numberRecord = 0;
		int numberFailed = 0;
//		
		//Create SQLite database
//		ChallengesSqlite runSQL = new ChallengesSqlite();
		
		while(csvUserIterator.hasNext()) {
			CsvUser user = csvUserIterator.next();
			if(user.isEndLine()==false) {
				
				if(user.isBad()) { //If it is bad record, csv will copy this to file.
					myCsvCreator.writeIn(user.getInfo());
					numberFailed +=1;
				}
//				else {// If it is good record, database fille will take it
//					runSQL.insertToDatabase(user.getInfo());
//				}
				numberRecord +=1;
			}

		}
		
		
		LogCreator lgCreator = new LogCreator(numberRecord,numberFailed,numberRecord - numberFailed);
		lgCreator.createLog(fileName, savePath);
		
		
		myCsvCreator.closeCsv();

	}
	

}
