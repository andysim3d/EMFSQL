package SiyuanPeng;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Util {
	Connection con=null;
	public HashMap<String, String> map=new HashMap<>();

	/**
	 * Connect the database and retrieve the "information_schema" of the table.
	 * Put the result into an ArrayList to be easily used.
	 */
	public void mfStructureGenerator(){
		String sql = "SELECT table_name, column_name, is_nullable, data_type, character_maximum_length FROM INFORMATION_SCHEMA.Columns WHERE table_name = 'sales'";
		try(Connection con=DBUtil.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);) {
			while(rs.next())
			{
				map.put(rs.getString(2), rs.getString(4));
			}

		} catch(SQLException e) {
			System.out.println("Connection URL or username or password errors!");
			e.printStackTrace();
		}
	}
}
