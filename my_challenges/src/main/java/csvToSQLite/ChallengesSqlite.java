package csvToSQLite;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class ChallengesSqlite {
	
	private static String[] fileHeader;
	
	//Path for save result
	private static String savePath = "";
	
	//Change source files path if you need.
	private static String sourceFilePath = "";
	
	private static String fileName = "";
		
	public static void main(String[] args) throws IOException, SQLException {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("---Please type absolute path from source files---");
		System.out.println("For Example: /User/Source File/myFile.csv");
		sourceFilePath += scan.nextLine();
		System.out.println("---Please type absolute path for saving files---");
		System.out.println("For Example: /User/Download/");
		savePath += scan.nextLine();
		scan.close();
		
		fileName += getFileName();
		
		CSVReader reader = new CSVReader(new FileReader(sourceFilePath));
		fileHeader = reader.readNext(); //Set header
		String [] nextLine;
		
		//Write all the bad records 
		CsvCreator myCsvCreator = new CsvCreator(fileName, savePath, fileHeader);
		
		//Create SQLite database
		SQLiteCreator mySQLite = new SQLiteCreator(fileName, savePath, fileHeader);
		mySQLite.createNewDatabase();
		
		int totalOfRecord = 0;
		int numberOfFailed = 0;
		
		System.out.println("Code is runging...");
		while((nextLine=reader.readNext())!=null) {
			if(! isEndLine(nextLine)) {
				if(isBadRecord(nextLine)) {
					myCsvCreator.writeIn(nextLine);
					numberOfFailed += 1;
				}
				else {
					mySQLite.insert(nextLine);
				}
				totalOfRecord += 1;
			}
		}
		
		myCsvCreator.closeCsv();
		reader.close();
		System.out.println("Finished!");
		
		int numberOfSuccessful = totalOfRecord - numberOfFailed;
		LogCreator lgCreator = new LogCreator(totalOfRecord, numberOfFailed, numberOfSuccessful);
		lgCreator.createLog(fileName, savePath);
				
	}
	
	private static String getFileName() {
		//Check it is MAC path or Windows Path.
		String cutter = sourceFilePath.lastIndexOf("/") != -1? "/":"\\"+"\\";
		String[] temp = sourceFilePath.split(cutter);
		String fileName = temp[temp.length-1];
		//Return String -> "File Name".
		return fileName.substring(0 , fileName.length()-4);
	}
	
	private static boolean isBadRecord(String[] line) {
		boolean answer = false;
		//If record's number of column is not equal to header's number of column, it is bad record.
		if(line.length != fileHeader.length) return answer;
		//Check each column is not empty.
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
