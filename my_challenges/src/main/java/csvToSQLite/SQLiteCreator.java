package csvToSQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class SQLiteCreator {
	
	private String savePath;
	private String fileName;
	private String my_database_url;
	private String[] header;
	
	public SQLiteCreator(String newFileName , String path , String[] newHeader){
		fileName = newFileName;
		savePath = path;
		header = newHeader;
		my_database_url = "jdbc:sqlite:" + this.savePath + this.fileName + ".db";;
	}
	
	public void createNewDatabase() {
		//Here does not set primary key because the table has duplicate value
		String my_table = "CREATE TABLE mytable (" 
							+ String.join(" TEXT NOT NULL, ", header) 
								+ " TEXT NOT NULL) ";
		
		try (Connection conn = DriverManager.getConnection(my_database_url);
				Statement stmt = conn.createStatement()) {
			stmt.execute(my_table);
			stmt.close();
			conn.close();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
    public void insert(String[] info) {
    	String[] unknowValues = new String[header.length];
    	Arrays.fill(unknowValues, "?");
    	
        String sql = "INSERT INTO mytable("
        				+String.join(",", header)
        					+") VALUES("
        						+String.join(",", unknowValues)
        							+")";
 
        try (Connection conn = DriverManager.getConnection(my_database_url);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	// Only record the information that match to header.
        	// Extra Columns will ignore.
            for(int i=0;i<header.length;i++) { 
            	pstmt.setString(i+1, info[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"(Insert)");
        }
    }
}
