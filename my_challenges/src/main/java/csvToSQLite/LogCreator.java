package csvToSQLite;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * The Class use for creating a Log file to save the journal.
 * @author shuoqiaoliu
 *
 */
public class LogCreator {
	
	private int numberRecord = 0;
	private int numberFailed = 0;
	private int numberSuccessful = 0;

	public LogCreator(int totalRecord, int numberOfFailed, int numberOfSuccessful) {
		numberRecord = totalRecord;
		numberFailed = numberOfFailed;
		numberSuccessful = numberOfSuccessful;
	}
	
	public void createLog(String NewfileName, String savePath){
		Logger logger = Logger.getLogger(ChallengesSqlite.class.getName());
		FileHandler fh = null;
		try {
			fh = new FileHandler(savePath + NewfileName + ".log");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.addHandler(fh);
		fh.setFormatter(new SimpleFormatter());
		logger.info("\nTotal Received: " + numberRecord
				+"\nNumber of Failed: " + numberFailed
				+"\nNumber of Successful: " + numberSuccessful);
	}
}
