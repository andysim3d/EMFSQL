import java.sql.*;
import java.util.ArrayList;
import SiyuanPeng.*;

class mfTableBean{
	public String cust;
	public String prod;
	public int sum_0_quant;
	public int count_0_quant;
	public int max_0_quant;
}
public class programGenerated {
	Connection conn=null;
	ArrayList<mfTableBean> al=null;
	public static void main(String[] args) {
		programGenerated main=new programGenerated();
		main.mfTableGenerator();
		main.print();
	}
	public void print(){
		for (int i = 0; i < al.size(); i++) {
			System.out.print(al.get(i).cust+"....."+al.get(i).prod+"....."+al.get(i).sum_0_quant/al.get(i).count_0_quant+"....."+al.get(i).max_0_quant);
			System.out.println();
		}
	}
	public void mfTableGenerator(){
		al=new ArrayList<mfTableBean>();
		conn=DBUtil.getInstance().getConnection();
		Statement st=null;
		ResultSet rs=null;
		try {
			st=conn.createStatement();
			rs=st.executeQuery("select * from sales;");
			while(rs.next()){
				String temp1=rs.getString("cust");
				String temp2=rs.getString("prod");
				boolean existed=false;
				for (int i = 0; i < al.size(); i++) {
					if(temp1.equals(al.get(i).cust)&&temp2.equals(al.get(i).prod)){
						al.get(i).sum_0_quant+=rs.getInt("quant");
						al.get(i).count_0_quant++;
						if(al.get(i).max_0_quant<rs.getInt("quant")){
							al.get(i).max_0_quant=rs.getInt("quant");
						}
						existed=true;
						break;
					}
				}
				if(!existed){
					mfTableBean tempbean=new mfTableBean();
					tempbean.prod=rs.getString("prod");
					tempbean.cust=rs.getString("cust");
					tempbean.sum_0_quant=rs.getInt("quant");
					tempbean.count_0_quant++;
					tempbean.max_0_quant=rs.getInt("quant");
					al.add(tempbean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
		}
	}
}
