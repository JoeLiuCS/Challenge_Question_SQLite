package my_challenges;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

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
	
	
	
	
	
	
	private Connection connect() {
		//Create SQLite database file
		String my_database_url = "jdbc:sqlite:" + savePath + fileName +".db";
		
		String sql = "CREATE TABLE IF NOT EXISTS mychallengetable (\n"
				+"    A text PRIMARY KEY,\n"	
				+ "    B text NOT NULL,\n"
				+ "    C text NOT NULL,\n"
				+ "    D text NOT NULL,\n"
				+ "    E text NOT NULL,\n"
				+ "    F text NOT NULL,\n"
				+ "    G text NOT NULL,\n"
				+ "    H text NOT NULL,\n"
				+ "    I text NOT NULL,\n"
				+ "    J text NOT NULL,\n"
				+ ");";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(my_database_url);
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public void insertToDatabase(String[] info) {
		String sql = "INSERT INTO" + "(A,B,C,D,E,F,G,H,I,J) VALUES(?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			for(int i=0;i<info.length;i++) {
				pstmt.setString(i+1, info[i]);
			}
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		
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
//		Writer writer = Files.newBufferedWriter(Paths.get(savePath + fileName + "-bad.csv"));
//		CSVWriter csvWriter = new CSVWriter(writer);
//		//Writer header
//		csvWriter.writeNext(header);
		
		CsvCreator myCsvCreator = new CsvCreator(fileName,savePath);
		
		int numberRecord = 0;
		int numberFailed = 0;
		int numberSuccessful = 0;
//		
		//Create SQLite database
//		ChallengesSqlite runSQL = new ChallengesSqlite();
		
		while(csvUserIterator.hasNext()) {
			CsvUser user = csvUserIterator.next();
			if(user.isEndLine()==false) {
				
				if(user.isBad()) { //If it is bad record, csv will copy this to file.
//					csvWriter.writeNext(user.getInfo());
					myCsvCreator.writeIn(user.getInfo());
					numberFailed +=1;
				}
//				else {// If it is good record, database fille will take it
//					runSQL.insertToDatabase(user.getInfo());
//				}
				numberRecord +=1;
			}

		}
		
		numberSuccessful = numberRecord - numberFailed;
		
		LogCreator lgCreator = new LogCreator(numberRecord,numberFailed,numberSuccessful);
		lgCreator.createLog(fileName, savePath);
		
//		csvWriter.close();
		
		myCsvCreator.closeCsv();

	}
	

}
