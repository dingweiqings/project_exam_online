package gao.servlet;

import java.io.IOException;
import gao.java.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Manager_StudentServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		String Stu_name=request.getParameter("Stu_name");
		String Stu_num=request.getParameter("Stu_num");
		String Stu_class_name=request.getParameter("Stu_class");
		String Stu_ID_Str=request.getParameter("Stu_ID");
		HttpSession sess=request.getSession();
		DBHandle dbh=new DBHandle();
		String sql="";
		if(flag.equals("add")){
			int Stu_class=dbh.getID("select Class_ID from Class_tb where Class_name='"+Stu_class_name+"'");
			sql="insert Stu_tb values('"+Stu_num+"','"+Stu_name+"','"+123456+"',"+Stu_class+")";
			if(dbh.Update(sql)){
				sess.setAttribute("message","添加学生成功！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Student_Add.jsp");
		}else if(flag.equals("delete")){
			int Stu_ID=Integer.parseInt(Stu_ID_Str);
			sql="delete from Stu_tb where Stu_ID='"+Stu_ID+"'";
			if(dbh.Update(sql)){
				sess.setAttribute("message","成功删除该学生！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Student_Add.jsp");
		}else if(flag.equals("alter")){
			int Stu_ID=Integer.parseInt(Stu_ID_Str);
			Stu_name=dbh.getName("select Stu_name from Stu_tb where Stu_ID='"+Stu_ID+"'");
			Stu_num=dbh.getName("select Stu_num from Stu_tb where Stu_ID='"+Stu_ID+"'");
			int Stu_class=dbh.getID("select Stu_class from Stu_tb where Stu_ID='"+Stu_ID+"'");
			Stu_class_name=dbh.getName("select Class_name from Class_tb where Class_ID='"+Stu_class+"'");
			ArrayList<String> al=dbh.getAttribute("select Class_name from Class_tb ");
			request.setAttribute("Stu_ID", Stu_ID+"");
			request.setAttribute("Stu_name", Stu_name);
			request.setAttribute("Stu_num", Stu_num);
			request.setAttribute("Stu_class", al);
			request.setAttribute("Stu_class_name", Stu_class_name);
			request.getRequestDispatcher("Manager_Student_Alter.jsp").forward(request, response);			
		}else if(flag.equals("altercl")){
			System.out.println(Stu_ID_Str+"  "+"    "+Stu_name+Stu_num+"   "+Stu_class_name);
			int Stu_ID=Integer.parseInt(Stu_ID_Str);
			int Stu_class=dbh.getID("select Class_ID from Class_tb where Class_name='"+Stu_class_name+"'");
			sql="update Stu_tb set Stu_name='"+Stu_name+"',Stu_num='"+Stu_num+"',Stu_class='"+Stu_class+"' where Stu_ID='"+Stu_ID+"'";
			if(dbh.Update(sql)){
				sess.setAttribute("message","修改信息成功！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Student_Alter.jsp");
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
