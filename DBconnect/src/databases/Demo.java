package databases;
import java.sql.*;
import java.util.*;
public class Demo {
static final String DATABASE_URL="jdbc:mysql://localhost/javademos";

// see: http://www.science.smith.edu/dftwiki/index.php/Tutorial:_Accessing_a_MySql_database_in_Java_(Eclipse)
// for a tutorial on importing mysql drivers to a project

	public static void main(String[] args) {
		Connection conn = null;
		Statement statement=null;
		PreparedStatement pStatement=null;
		ResultSet resultset=null;
		ResultSetMetaData meta=null;
		final String name="Michael";
		
		System.out.println("<<STATEMENT>>");
		try {
			conn=DriverManager.getConnection(DATABASE_URL,"root","");
			statement=conn.createStatement();
			resultset=statement.executeQuery("SELECT * FROM users");
			ResultSetMetaData metadata=resultset.getMetaData();
			int numberOfColumns = metadata.getColumnCount();
			
			System.out.println("users in Demo tbl: ");
			
			for(int i =1;i<=numberOfColumns;i++) {
				System.out.print(metadata.getColumnName(i) +"\t");
			}
			System.out.println("");
			
			while(resultset.next()) {
				System.out.println(resultset.getObject("userID")+"\t"+resultset.getObject(2)+"\t");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				resultset.close();
				statement.close();
				conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("<<PREPARED STATEMENT>>");
		
		try {
			conn=DriverManager.getConnection(DATABASE_URL,"root","");
			pStatement=conn.prepareStatement("SELECT * FROM users WHERE username =?");
			pStatement.setString(1,name);
			resultset=pStatement.executeQuery();
			ResultSetMetaData metadata=resultset.getMetaData();
			int numberOfColumns = metadata.getColumnCount();
			
			System.out.println("users in Demo tbl: ");
			
			for(int i =1;i<=numberOfColumns;i++) {
				System.out.print(metadata.getColumnName(i) +"\t");
			}
			System.out.println("");
			
			while(resultset.next()) {
				System.out.println(resultset.getObject("userID")+"\t"+resultset.getObject(2)+"\t");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				resultset.close();
				statement.close();
				conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
