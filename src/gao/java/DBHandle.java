package gao.java;
import java.sql.*;
import java.util.*;


public class DBHandle {
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	public ArrayList<String> getPassword(String sql){
		ArrayList<String> psd=new ArrayList<String>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				psd.add(rs.getString(1));
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return psd;
	}
	public int getRowCount(String sql){
		int pageCount=0;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				pageCount=rs.getInt(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return pageCount;
	}
	public ArrayList<String> getClass_name(String sql){
		ArrayList<String> classes=new ArrayList<String>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				String s=rs.getString(1);
				
				classes.add(s);
			}
		}catch(Exception ex){
				ex.printStackTrace();
			}finally{				
				this.close();
			}
			return classes;
	}
	public ArrayList<Student> getStudent(String sql){
		ArrayList<Student> stu=new ArrayList<Student>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Student st=new Student();
				st.setStu_ID(rs.getInt(1));
				st.setStu_num(rs.getString(2));
				st.setStu_name(rs.getString(3));
				st.setStu_psd(rs.getString(4));
				int Class_ID=rs.getInt(5);
				st.setStu_class(this.getName("select Class_name from Class_tb where Class_ID='"+Class_ID+"'"));
				stu.add(st);
			}
		}catch(Exception ex){
				ex.printStackTrace();
			}finally{				
				this.close();
			}
			return stu;
	}
	public  ArrayList<Teacher> getTeacher(String sql){
		ArrayList<Teacher> teacher=new ArrayList<Teacher>();
		System.out.println(sql);
		
		try{
			
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Teacher tea=new Teacher();
				tea.setTea_ID(rs.getInt(1));
				tea.setTea_name(rs.getString(2));
				tea.setTea_psd(rs.getString(3));
				teacher.add(tea);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return teacher;
	}
	public ArrayList<Course> getCourse(String sql){
		ArrayList<Course> course=new ArrayList<Course>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Course cou=new Course();
				cou.setCou_ID(rs.getInt(1));
				cou.setCou_name(rs.getString(2));
				course.add(cou);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return course;
	}
	public ArrayList<Banji> getBanji(String sql){
		ArrayList<Banji> banji=new ArrayList<Banji>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Banji bj=new Banji();
				bj.setClass_ID(rs.getInt(1));
				bj.setClass_name(rs.getString(2));
				banji.add(bj);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();			
		}
		return banji;
	}
	
	public void  setString(String a,String b,String c,String d){
		if (a==null)
		a="";
		if (b==null)
	    b="";
		if (c==null)
			c="";
		if(d==null)
			d="";
		
		
	}
	
	
	
	
	
	
	
	
	public ArrayList<Score> getScore(String sql){
		ArrayList<Score> score=new ArrayList<Score>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()){
				System.out.println("add执行了");
				Score sco=new Score();
				sco.setScore_ID(rs.getInt(1));
				System.out.println("DBhandle"+"第1列");
				int Stu_ID=rs.getInt(2);
				sco.setStu_name(this.getName("select Stu_name from Stu_tb where Stu_ID='"+Stu_ID+"'"));
				int Class_ID=rs.getInt(3);
				sco.setClass_name(this.getName("select Class_name from Class_tb where Class_ID='"+Class_ID+"'"));
				int Tea_ID=rs.getInt(4);
				sco.setTea_name(this.getName("select Tea_name from Tea_tb where Tea_ID='"+Tea_ID+"'"));
				int Cou_ID=rs.getInt(5);
				sco.setCou_name(this.getName("select Cou_name from Cou_tb where Cou_ID='"+Cou_ID+"'"));
				sco.setScore_score(rs.getInt(6));
				System.out.println("add执行了");
				score.add(sco);
	
			}
			System.out.println("while结束了");
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return score;
	}
	public ArrayList<Tea_Cou_Cla> getTea_Cou_Cla(String sql){
		ArrayList<Tea_Cou_Cla> al=new ArrayList<Tea_Cou_Cla>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Tea_Cou_Cla tcc=new Tea_Cou_Cla();
				int Tea_ID=rs.getInt(1);
				int Cou_ID=rs.getInt(2);
				int Class_ID=rs.getInt(3);
				tcc.setTea_name(this.getName("select Tea_name from Tea_tb where Tea_ID='"+Tea_ID+"'"));
				tcc.setCou_name(this.getName("select Cou_name from Cou_tb where Cou_ID='"+Cou_ID+"'"));
				tcc.setCla_name(this.getName("select Class_name from Class_tb where Class_ID='"+Class_ID+"'"));
				al.add(tcc);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}return al;
	}
	public ArrayList<Title> getTitle(String sql){
		ArrayList<Title> title=new ArrayList<Title>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				Title tl=new Title();
				tl.setTitle_ID(rs.getInt(1));
				tl.setTitle_name(rs.getString(2));
				tl.setTitle_opt_a(rs.getString(3));
				tl.setTitle_opt_b(rs.getString(4));
				tl.setTitle_opt_c(rs.getString(5));
				tl.setTitle_opt_d(rs.getString(6));
				tl.setTitle_answer(rs.getString(7));
				tl.setTitle_score(rs.getInt(8));
				int Cou_ID=rs.getInt(9);
				tl.setCou_name(this.getName("select Cou_name from Cou_tb where Cou_ID='"+Cou_ID+"'"));
				int Tea_ID=rs.getInt(10);
				tl.setTea_name(this.getName("select Tea_name from Tea_tb where Tea_ID='"+Tea_ID+"'"));
				title.add(tl);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return title;
	}
	public ArrayList<String> getAttribute(String sql){
		ArrayList<String> att=new ArrayList<String>();
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				att.add(rs.getString(1));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return att;
	}
	public int getID(String sql){
		int ID=1;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){
				ID=rs.getInt(1);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
		}
		return ID;
	}
	public String getName(String sql){
		String name="";
		ResultSet rst=null;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			rst=ps.executeQuery();
			if(rst.next()){
				name=rst.getString(1);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(rst!=null){
					rst.close();
					rst=null;
				}if(ps!=null){
					ps.close();
					ps=null;
				}if(conn!=null){
					conn.close();
					conn=null;
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return name;
	}
	public boolean Update(String sql){
		boolean b=false;
		try{
			conn=new DBconn().getConn();
			ps=conn.prepareStatement(sql);
			int rs=ps.executeUpdate();
			if(rs!=0){
				b=true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	public void close(){
		try{
			if(rs!=null){
				rs.close();
				rs=null;
			}if(ps!=null){
				ps.close();
				ps=null;
			}if(conn!=null){
				conn.close();
				conn=null;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}
