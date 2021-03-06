package SiyuanPeng;

import java.util.ArrayList;
import java.util.Arrays;

import Siyuan.Zheng.MF_Structure;
import Siyuan.Zheng.Parameters;
import Siyuan.Zheng.sBean;

public class ParseMF {
	public static MF_Structure mfParase(Siyuan.Zheng.Parameters pa){
		MF_Structure temp = new MF_Structure();
		//have n tuples
		ArrayList<AggrateFunction> temp_tuples = new ArrayList<>();
		temp.grouping_attributes = pa.getV();
		try{
			for (sBean k : pa.getF()) {
				AggrateFunction t = new AggrateFunction();
				//temp_tuples[i].grouping_variable = (String)pa.getV().get(0);
				if(k.name.contains("__")){
					String [] sp = k.name.split("_");
					t.funcName = sp[1]+"(*)";
					t.Label = sp[0];
					t.func = sp[1];
					t.funcColum = "_";
					temp_tuples.add(t);
				}
				else{
					String [] sp = k.name.split("_");
					t.funcName = sp[1]+"("+sp[2]+")";
					t.Label = sp[0];
					t.func = sp[1];
					t.funcColum = sp[2];
					temp_tuples.add(t);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		temp.aggrate_function.addAll(temp_tuples);
		return temp;	
	}
}
