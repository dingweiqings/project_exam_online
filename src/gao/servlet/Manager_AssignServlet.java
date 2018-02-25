package gao.servlet;

import java.io.IOException;

import gao.java.*;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Manager_AssignServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf_8");
		String flag=request.getParameter("flag");		
		HttpSession sess=request.getSession();
		DBHandle dbh=new DBHandle();
		String sql="";
		if(flag.equals("add")){			
			String Tea_name=request.getParameter("Tea_name");
			String Cou_name=request.getParameter("Cou_name");
			String Class_name=request.getParameter("Class_name");
			int Tea_ID=dbh.getID("select Tea_ID from Tea_tb where Tea_name='"+Tea_name+"'");
			int Cou_ID=dbh.getID("select Cou_ID from Cou_tb where Cou_name='"+Cou_name+"'");
			int Class_ID=dbh.getID("select Class_ID from Class_tb where Class_name='"+Class_name+"'");
			
			sql="insert Tea_Cou_Class_tb values('"+Tea_ID+"','"+Cou_ID+"','"+Class_ID+"')";
			if(dbh.Update(sql)){
				sess.setAttribute("message","添加教师成功！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Teacher_Add.jsp");
		}else if(flag.equals("delete")){
			int Tea_ID=Integer.parseInt(request.getParameter("Tea_ID"));
			int Class_ID=Integer.parseInt(request.getParameter("Class_ID"));
			int Cou_ID=Integer.parseInt(request.getParameter("Cou_ID"));
			sql="delete from Tea_Cou_Class_tb where Tea_ID='"+Tea_ID+"' and Cou_ID='"+Cou_ID+"' and Class_ID='"+Class_ID+"'";
			if(dbh.Update(sql)){
				sess.setAttribute("message","成功删除该分配！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Assign.jsp");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}