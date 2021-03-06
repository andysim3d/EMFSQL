/**
 * Singleton class to connect postgres database through JDBC
 * call .getInstance() to get the singleton object and call .close() to disconnect.
 */

package SiyuanPeng;
import java.sql.*;

public class DBUtil {

	private String usr ="postgres";
	private String pwd ="zhang";
	private String url ="jdbc:postgresql://localhost:5432/Test";

	private static DBUtil instance=null;
	private Connection con=null;

	/**
	 * private constructor in order to utilize the singleton design patter
	 */
	private DBUtil(){
	}
	
	/**
	 * Singleton design pattern to save the resource
	 * @return
	 */
	public static DBUtil getInstance(){
		if(null==instance)
			instance=new DBUtil();
		return instance;
	}

	/**
	 * connect the database
	 */
	private void open(){
		try {
			Class.forName("org.postgresql.Driver");     //Loads the required driver
			System.out.println("Success loading Driver!");
		} catch(Exception exception) {
			System.out.println("Fail loading Driver!");
			exception.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(url, usr, pwd);    //connect to the database using the password and username
			System.out.println("Success connecting server!");
			System.out.println();
		}catch(SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
	}

	/**
	 * get the connection through the only one object
	 */
	public Connection getConnection(){
		if(con==null)
			open();
		return con;
	}

	/**
	 * disconnect the database
	 */
	public void close(){
		try {
			con.close();
			con=null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
