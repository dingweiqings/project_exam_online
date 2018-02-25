package gao.servlet;

import gao.java.DBHandle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Manager_CourseServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("utf_8");
		String flag=request.getParameter("flag");
		String Cou_name=request.getParameter("Cou_name");		
		String Cou_ID_Str=request.getParameter("Cou_ID");
		HttpSession sess=request.getSession();
		DBHandle dbh=new DBHandle();
		String sql="";
		if(flag.equals("add")){			
			sql="insert Cou_tb values('"+Cou_name+"')";
			if(dbh.Update(sql)){
				sess.setAttribute("message","添加课程成功！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Course_Add.jsp");
		}else if(flag.equals("delete")){
			int Cou_ID=Integer.parseInt(Cou_ID_Str);
			sql="delete from Cou_tb where Cou_ID='"+Cou_ID+"'";
			if(dbh.Update(sql)){
				sess.setAttribute("message","成功删除该课程！");
				response.sendRedirect("Success.jsp");
			}else response.sendRedirect("Manager_Course.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
