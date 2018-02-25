package gao.servlet;

import java.io.IOException;
import gao.java.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StudentServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");//设置编码方式避免中文乱码
		DBHandle dbh=new DBHandle();
		int pageSize=5;
		int pageCount=0;
		int pageNow=1;
		int rowCount=0;//声明四个局部变量,用于分页处理
		String flag=request.getParameter("flag");
		String pageNowStr=request.getParameter("pageNow");
		if(pageNowStr==null||pageNowStr=="") pageNowStr="1";//默认pageNow=1，避免一些不必要的异常，如null（空）对象异常
		pageNow=Integer.parseInt(pageNowStr);
		HttpSession sess=request.getSession();
		String user=(String)sess.getAttribute("user");
		if(flag.equals("query")){			
			String cou_name=request.getParameter("Cou_name");			
			int cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+cou_name+"'");
			int stu_ID=dbh.getID("select Stu_ID from Stu_tb where Stu_name='"+user+"'");
			ArrayList<Score> al=new ArrayList<Score>();
			if(cou_name!=null){//用于判断是不是第一次显示				
					if(cou_name!=""){
						al=dbh.getScore("select  * from Score_tb where Cou_ID='"+cou_ID+"' and Stu_ID='"+stu_ID+"' Score_ID limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Score_tb where Cou_ID='"+cou_ID+"' and Stu_ID='"+stu_ID+"'");
					}else {//按班级和教师来查询						
						al=dbh.getScore("select  * from Score_tb where Stu_ID='"+stu_ID+"' and Score_ID  limit "+pageSize*(pageNow-1)+","+pageSize);
						rowCount=dbh.getRowCount("select count(*) from Score_tb where Stu_ID='"+stu_ID+"'");
					}					
					request.setAttribute("cou_name", cou_name);
				}				
			else{
				al=dbh.getScore("select  * from Score_tb where Stu_ID='"+stu_ID+"' and Score_ID limit "+pageSize*(pageNow-1)+","+pageSize);
				rowCount=dbh.getRowCount("select count(*) from Score_tb where Stu_ID='"+stu_ID+"'");
				request.setAttribute("cou_name", "");				
			}			
		    if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
		    else pageCount=rowCount/pageSize+1;
		    //通过rowCount和pageSize的关系来计算pageCount的值
		    
		    //得到所有的班级的名称，并将他们放入一个集合中 	
		    ArrayList<String> Cou_name=dbh.getAttribute("select distinct Cou_tb.Cou_name from Stu_tb,Cou_tb,Tea_Cou_Class_tb where Stu_tb.Stu_ID='"+stu_ID+
		    "' and Stu_tb.stu_class=Tea_Cou_Class_tb.Class_ID and Tea_Cou_Class_tb.Cou_ID=Cou_tb.Cou_ID");
		    	    
		    request.setAttribute("Score", al);
			request.setAttribute("pageNow", pageNowStr);
			request.setAttribute("pageCount",pageCount+"");
			request.setAttribute("rowCount", rowCount+"");						
			request.setAttribute("Cou_name", Cou_name);
			request.getRequestDispatcher("Student_Query.jsp").forward(request,response );
		}else if(flag.equals("psdAlter")){
			 String old_psd=request.getParameter("old_psd");
			 String new_psd=request.getParameter("new_psd");			 
			 ArrayList<String> psd=dbh.getPassword("select Stu_psd from Stu_tb where Stu_name='"+user+"'");
			 boolean b=false;
			 for(int i=0;i<psd.size();i++){
				 if(old_psd.equals(psd.get(i))){
					 b=true;break;
				 }
			 }
			 if(b){
				 String sql="update Stu_tb set Stu_psd='"+new_psd+"' where Stu_name='"+user+"' and Stu_psd='"+old_psd+"'";
				 if(dbh.Update(sql)){
					 sess.setAttribute("message", "密码修改成功！");
					 request.getRequestDispatcher("S_Success.jsp").forward(request, response);
				 }
			 }else{
				 request.getRequestDispatcher("Student_psdAlter.jsp?flag=error").forward(request, response);
			 }
			 
		}else if(flag.equals("test")){
			ArrayList<Title> a2=null;
			ArrayList<Title> a4=null;
			ArrayList<Title> a8=null;
			String Cou_name=request.getParameter("Cou_name");			
			int Cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+Cou_name+"'");
			request.setAttribute("Cou_name", Cou_name);
			Random rand=new Random();
			ArrayList<Title> a22=new ArrayList<Title>();
			ArrayList<Title> a44=new ArrayList<Title>();
			ArrayList<Title> a88=new ArrayList<Title>();
			String Title_2=request.getParameter("Title_2");
			int num2=Integer.parseInt(Title_2);
			System.out.println(num2);
			String Title_4=request.getParameter("Title_4");
			int num4=Integer.parseInt(Title_4);
			String Title_8=request.getParameter("Title_8");
			int num8=Integer.parseInt(Title_8);
			a2=dbh.getTitle("select * from Title_tb where Title_score=2 and Cou_ID='"+Cou_ID+"'");
			
			a4=dbh.getTitle("select * from Title_tb where Title_score=4 and Cou_ID='"+Cou_ID+"'");
			
			a8=dbh.getTitle("select * from Title_tb where Title_score=8 and Cou_ID='"+Cou_ID+"'");
		
			if(num2>a2.size()||num4>a4.size()||num8>a8.size()){
				request.getRequestDispatcher("Student_Test_Info.jsp?flag=error").forward(request,response);
			}else{
				for(int i=0;i<num2;i++)
				{
					Title tl=(Title)a2.get(rand.nextInt(a2.size()));
					if(a22.contains(tl))
					{
						i=i-1;
					}else {
					  a22.add(tl);
					}
				}
				for(int i=0;i<num4;i++)
				{
					Title tl=a4.get(rand.nextInt(a4.size()));
					if(a44.contains(tl))
					{
						i=i-1;
					}else {
					  a44.add(tl);
					}
				}
				for(int i=0;i<num8;i++)
				{
					Title tl=a8.get(rand.nextInt(a8.size()));
					if(a88.contains(tl))
					{
						i=i-1;
					}else {
					  a88.add(tl);
					}
				}
				request.setAttribute("Cou_ID",Cou_ID);
				request.setAttribute("a22", a22);
				request.setAttribute("a44", a44);
				request.setAttribute("a88", a88);
				request.getRequestDispatcher("Student_Online_Exam.jsp").forward(request, response);
			}
			
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	
	}

}
