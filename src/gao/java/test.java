package gao.java;

import java.lang.reflect.Array;
import java.util.List;

public class test {

	public static void main(String[] args) {
		DBHandle dbHandle=new DBHandle();
		   int pageSize=2;
		    int pageNow=1;
		
		List<Teacher> list=dbHandle.getTeacher("select  * from Tea_tb  Tea_ID limit "+pageSize*(pageNow-1)+","+pageSize);
		//System.out.println(list.toArray());
		

	}

}
