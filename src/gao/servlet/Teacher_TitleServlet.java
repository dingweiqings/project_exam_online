package gao.servlet;

import java.io.IOException;

import gao.java.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Teacher_TitleServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		String Title_name=request.getParameter("Title_name");
		String Title_opt_a=request.getParameter("Title_opt_a");
		String Title_opt_b=request.getParameter("Title_opt_b");
		String Title_opt_c=request.getParameter("Title_opt_c");
		String Title_opt_d=request.getParameter("Title_opt_d");
		String Title_score_str=request.getParameter("Title_score");
		String Title_answer=request.getParameter("Title_answer");
		String Cou_name=request.getParameter("Cou_name");		
		HttpSession sess=request.getSession();
		String user=(String)sess.getAttribute("user");
		DBHandle dbh=new DBHandle();
		int Tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+user+"'");
		String sql="";		
		if(flag.equals("add")){
			int Title_score=Integer.parseInt(Title_score_str);			
			int Cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+Cou_name+"'");	
			
			sql="insert into Title_tb (Title_name,Title_opt_a,Title_opt_b,Title_opt_c,Title_opt_d,Title_answer,Title_score,Cou_ID,Tea_ID) " +
					"values('"+Title_name+"','"+Title_opt_a+"','"+Title_opt_b+"','"+Title_opt_c+"','"+Title_opt_d+"','"+Title_answer+"',"+Title_score+","+Cou_ID+","+Tea_ID+")";
			
			//System.out.println(sql);
			if(dbh.Update(sql))
			{
				sess.setAttribute("message", "添加题目成功");
				request.getRequestDispatcher("T_Success.jsp").forward(request, response);				
			}
			else response.sendRedirect("Teacher_Title_Add.jsp");		
			
		}else if(flag.equals("delete")){
			int Title_ID=Integer.parseInt(request.getParameter("id"));
			sql="delete from Title_tb where Title_ID='"+Title_ID+"'";
			if(dbh.Update(sql))
			{
				sess.setAttribute("message", "删除题目成功");
				request.getRequestDispatcher("T_Success.jsp").forward(request, response);				
			}
			else response.sendRedirect("Teacher_Title.jsp");
		}else if(flag.equals("alter")){
			int Title_ID=Integer.parseInt(request.getParameter("id"));
			Title_name=dbh.getName("select Title_name from Title_tb where Title_ID='"+Title_ID+"'");
			Title_opt_a=dbh.getName("select Title_opt_a from Title_tb where Title_ID='"+Title_ID+"'");
			Title_opt_b=dbh.getName("select Title_opt_b from Title_tb where Title_ID='"+Title_ID+"'");
			Title_opt_c=dbh.getName("select Title_opt_c from Title_tb where Title_ID='"+Title_ID+"'");
			Title_opt_d=dbh.getName("select Title_opt_d from Title_tb where Title_ID='"+Title_ID+"'");
			int Title_score=dbh.getID("select Title_score from Title_tb where Title_ID='"+Title_ID+"'");
			Title_answer=dbh.getName("select Title_answer from Title_tb where Title_ID='"+Title_ID+"'");
			int Cou_ID=dbh.getID("select Cou_ID from Title_tb where Title_ID='"+Title_ID+"'");
			Cou_name=dbh.getName("select Cou_name from Cou_tb where Cou_ID='"+Cou_ID+"'");
			
			ArrayList<String> Course_name=dbh.getAttribute("select distinct Cou_tb.Cou_name from Tea_tb,Cou_tb,Tea_Cou_Class_tb where Tea_tb.Tea_ID='"+Tea_ID+
		    "' and Tea_tb.Tea_ID=Tea_Cou_Class_tb.Tea_ID and Tea_Cou_Class_tb.Cou_ID=Cou_tb.Cou_ID");
			request.setAttribute("Title_ID",Title_ID );
			request.setAttribute("Title_name",Title_name );
			request.setAttribute("Title_opt_a",Title_opt_a );
			request.setAttribute("Title_opt_b",Title_opt_b );
			request.setAttribute("Title_opt_c",Title_opt_c );
			request.setAttribute("Title_opt_d",Title_opt_d );
			request.setAttribute("Title_score",Title_score );
			request.setAttribute("Title_answer",Title_answer );
			request.setAttribute("Cou_name",Cou_name );
			request.setAttribute("Course_name",Course_name);
			request.getRequestDispatcher("Teacher_Title_Alter.jsp").forward(request,response);			
		}else if(flag.equals("altercl")){
			int Title_score=Integer.parseInt(Title_score_str);			
			int Cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+Cou_name+"'");
			int Title_ID=Integer.parseInt(request.getParameter("id"));
			
			sql="update Title_tb set Title_name='"+Title_name+"',Title_opt_a='"+Title_opt_a+"',Title_opt_b='"+Title_opt_b+"',Title_opt_c='"+Title_opt_c+"',Title_opt_d='"+Title_opt_d+"'," +
					"Title_score='"+Title_score+"',Title_answer='"+Title_answer+"',Cou_ID='"+Cou_ID+"' where Title_ID='"+Title_ID+"'";
			
			//System.out.println(sql);
			if(dbh.Update(sql))
			{
				sess.setAttribute("message", "修改题目成功");
				request.getRequestDispatcher("T_Success.jsp").forward(request, response);				
			}
			else response.sendRedirect("Teacher_Title.jsp");
			
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
