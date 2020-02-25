package my_challenges;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteCreator {
	
	private String savePath;
	private String fileName;
	private String my_database_url;
	
	public SQLiteCreator(String newFileName,String path){
		fileName = newFileName;
		savePath = path;
		my_database_url = "jdbc:sqlite:" + this.savePath + this.fileName + ".db";;
	}
	
	public void createNewDatabase() {
		//Here does not set primary key because the table has duplicate value
		String my_table = "CREATE TABLE mytable " +

		"(A TEXT NOT NULL," +
		" B TEXT NOT NULL, " +
		" C TEXT NOT NULL, " +
		" D TEXT NOT NULL, " +
		" E TEXT NOT NULL, " +
		" F TEXT NOT NULL, " +
		" G TEXT NOT NULL, " +
		" H TEXT NOT NULL, " +
		" I TEXT NOT NULL, " +
		" J TEXT NOT NULL) " ;
		
		try (Connection conn = DriverManager.getConnection(my_database_url);
				Statement stmt = conn.createStatement()) {
            //create new table
			stmt.execute(my_table);
			stmt.close();
			conn.close();
 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
    
    public void insert(String[] info) {
        String sql = "INSERT INTO mytable(A,B,C,D,E,F,G,H,I,J) VALUES(?,?,?,?,?,?,?,?,?,?)";
 
        try (Connection conn = DriverManager.getConnection(my_database_url);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for(int i=0;i<info.length;i++) {
            	pstmt.setString(i+1, info[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage()+"(Insert)");
        }
    }
	


}
