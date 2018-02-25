package gao.servlet;

import gao.java.DBHandle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Manager_TeacherServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf_8");
		String flag=request.getParameter("flag");
		String Tea_name=request.getParameter("Tea_name");		
		String Tea_ID_Str=request.getParameter("Tea_ID");
		HttpSession sess=request.getSession();
		DBHandle dbh=new DBHandle();
		String sql="";
		if(flag.equals("add")){			
			sql="insert Tea_tb values('"+Tea_name+"','"+123456+"')";
			if(dbh.Update(sql)){
				sess.setAttribute("message","添加教师成功！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Teacher_Add.jsp");
		}else if(flag.equals("delete")){
			int Tea_ID=Integer.parseInt(Tea_ID_Str);
			sql="delete from Tea_tb where Tea_ID='"+Tea_ID+"'";
			if(dbh.Update(sql)){
				sess.setAttribute("message","成功删除该老师！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Teacher_Add.jsp");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
