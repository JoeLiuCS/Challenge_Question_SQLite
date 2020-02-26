package csvToSQLite;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Arrays;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class ChallengesSqlite {
	
	private static String[] fileHeader;
	
	//Path for save result
	final private static String savePath = "/Users/shuoqiaoliu/git/Challenge_Question_SQLite/my_challenges/src/main/resources/";
	
	//Change source files path if you need.
	final private static String sourceFilePath = 
			"/Users/shuoqiaoliu/git/Challenge_Question_SQLite/my_challenges/src/main/resources/Entry Level Coding Challenge Page 2.csv";
	
	final private static String fileName = getFileName();
	
	
	public static void main(String[] args) throws IOException, SQLException {
		
		CSVReader reader = new CSVReader(new FileReader(sourceFilePath));
		fileHeader = reader.readNext();
		String [] nextLine;
		
		//Write all the bad records 
		CsvCreator myCsvCreator = new CsvCreator(fileName,savePath,fileHeader);
		
		int totalOfRecord = 0;
		int numberOfFailed = 0;
		
		while((nextLine=reader.readNext())!=null) {
			if(! isEndLine(nextLine)) {
				if(isBadRecord(nextLine)) {
					
				}
				else {
					
				}
			}
		}
		
		
		

//		
//		//Create SQLite database
//		SQLiteCreator my_sqlite = new SQLiteCreator(fileName,savePath);
//		my_sqlite.createNewDatabase();
//		
//		while((nextLine = reader.readNext()) != null) {
//			CsvUser user = csvUserIterator.next();
//			if(!user.isEndLine()) {
//				
//				if(user.isBad()) { //If it is bad record, CSV will copy this to file.
//					myCsvCreator.writeIn(user.getInfo());
//					numberFailed +=1;
//				}
//				else {// If it is good record, database file will take it
//					my_sqlite.insert(user.getInfo());
//				}
//				numberRecord += 1;
//			}
//
//		}
//		
//		myCsvCreator.closeCsv();
//		
//		LogCreator lgCreator = new LogCreator(numberRecord,numberFailed,numberRecord - numberFailed);
//		lgCreator.createLog(fileName, savePath);
//		
	}
	
	/* 
	 * Get file name by given sourceFilePath
	 */
	private static String getFileName() {
		//Check it is MAC path or Windows Path
		String cutter = sourceFilePath.lastIndexOf("/") != -1 ? "/":"\\";
		String[] temp = sourceFilePath.split(cutter);
		String fileName = temp[temp.length-1];
		//Return String -> "Entry Level Coding Challenge Page 2"
		return fileName.substring(0, fileName.length()-4);
	}
	
	private static boolean isBadRecord(String[] line) {
		boolean answer = false;
		
		if(line.length != fileHeader.length) return answer;
		
		for(String column:line) {
			if(column.isEmpty()) {
				answer = true;
				break;
			}
		}
		return answer;
	}
	
	private static boolean isEndLine(String[] line) {
		return line.length == 1 && line[0].isEmpty();
	}

}
