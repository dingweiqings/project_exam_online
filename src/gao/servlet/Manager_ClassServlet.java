package gao.servlet;

import gao.java.DBHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Manager_ClassServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf_8");
		String flag=request.getParameter("flag");
		System.out.println("flag="+flag);
		String Class_name=request.getParameter("Class_name");		
		String Class_ID_Str=request.getParameter("Class_ID");
		HttpSession sess=request.getSession();
		DBHandle dbh=new DBHandle();
		String sql="";
		if(flag.equals("add")){			
			sql="insert Class_tb values('"+Class_name+"')";
			System.out.println("sql="+sql);
			if(dbh.Update(sql)){
				sess.setAttribute("message","添加班级成功！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Class_Add.jsp");
		}else if(flag.equals("delete")){
			int Class_ID=Integer.parseInt(Class_ID_Str);
			sql="delete from Class_tb where Class_ID='"+Class_ID+"'";
			if(dbh.Update(sql)){
				sess.setAttribute("message","成功删除该班级！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Class.jsp");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
