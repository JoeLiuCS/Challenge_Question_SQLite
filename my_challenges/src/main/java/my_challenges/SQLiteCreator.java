package my_challenges;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteCreator {
	
	private String savePath;
	private String fileName;
	
	public SQLiteCreator(String newFileName,String path){
		fileName = newFileName;
		savePath = path;
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

}
