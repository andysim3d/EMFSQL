import java.sql.*;
import java.util.ArrayList;
import SiyuanPeng.*;

public class programGenerated {
	class mfTableBean{
		int sumforavg_2_quant;
		int avg_2_quant;
		String _2_state;
		int avg_3_quant;
		int countforavg_2_quant;
		int avg_1_quant;
		int sumforavg_3_quant;
		int countforavg_1_quant;
		int sumforavg_1_quant;
		String _1_state;
		String _3_state;
		int countforavg_3_quant;
		String cust;
	}
	ArrayList<mfTableBean> al=new ArrayList<>();
	public static void main(String[] args) {
		programGenerated main=new programGenerated();
		main.mfTableGenerator();
		main.print();
	}
	public void mfTableGenerator(){
		ResultSet rs=null;
		try(Connection conn=DBUtil.getInstance().getConnection();
			 Statement st=conn.createStatement();) {
			rs=st.executeQuery("select * from sales;");
			while(rs.next()){
				boolean existed=false;
				for (int i = 0; i < al.size(); i++) {
					if(rs.getString("cust").equals(al.get(i).cust)){
						existed=true;
						break;
					}
				}
				if(!existed){
					mfTableBean tempbean=new mfTableBean();
					tempbean.cust=rs.getString("cust");
					al.add(tempbean);
				}
			}
			rs=st.executeQuery("select * from sales;");
			while(rs.next()){
				for (int i = 0; i < al.size(); i++) {
					if(rs.getString("cust").equals(al.get(i).cust)){
					if(rs.getString("state").equals("NY")){
						al.get(i).countforavg_1_quant++;
						al.get(i).sumforavg_1_quant+=rs.getInt("quant");
						al.get(i)._1_state=rs.getString("state");
					}
					}
				}
			}
			for (mfTableBean bean : al) {
				if(bean.countforavg_1_quant!=0)
					bean.avg_1_quant=bean.sumforavg_1_quant/bean.countforavg_1_quant;
				else
					bean.avg_1_quant=0;
			}
			rs=st.executeQuery("select * from sales;");
			while(rs.next()){
				for (int i = 0; i < al.size(); i++) {
					if(rs.getString("cust").equals(al.get(i).cust)){
					if(rs.getString("state").equals("NJ")){
						al.get(i).countforavg_2_quant++;
						al.get(i).sumforavg_2_quant+=rs.getInt("quant");
						al.get(i)._2_state=rs.getString("state");
					}
					}
				}
			}
			for (mfTableBean bean : al) {
				if(bean.countforavg_2_quant!=0)
					bean.avg_2_quant=bean.sumforavg_2_quant/bean.countforavg_2_quant;
				else
					bean.avg_2_quant=0;
			}
			rs=st.executeQuery("select * from sales;");
			while(rs.next()){
				for (int i = 0; i < al.size(); i++) {
					if(rs.getString("cust").equals(al.get(i).cust)){
					if(rs.getString("state").equals("CT")){
						al.get(i).countforavg_3_quant++;
						al.get(i).sumforavg_3_quant+=rs.getInt("quant");
						al.get(i)._3_state=rs.getString("state");
					}
					}
				}
			}
			for (mfTableBean bean : al) {
				if(bean.countforavg_3_quant!=0)
					bean.avg_3_quant=bean.sumforavg_3_quant/bean.countforavg_3_quant;
				else
					bean.avg_3_quant=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void print(){
			System.out.print("cust.....");
			System.out.print("avg_1_quant.....");
			System.out.print("_1_state.....");
			System.out.print("avg_2_quant.....");
			System.out.print("_2_state.....");
			System.out.print("avg_3_quant.....");
			System.out.print("_3_state.....");
			System.out.println();
		for (int i = 0; i < al.size(); i++) {
			System.out.print(al.get(i).cust+".....");
			System.out.print(al.get(i).avg_1_quant+".....");
			System.out.print(al.get(i)._1_state+".....");
			System.out.print(al.get(i).avg_2_quant+".....");
			System.out.print(al.get(i)._2_state+".....");
			System.out.print(al.get(i).avg_3_quant+".....");
			System.out.print(al.get(i)._3_state+".....");
			System.out.println();
		}
	}
}
