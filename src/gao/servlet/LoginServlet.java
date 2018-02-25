package gao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import gao.java.*;
import java.util.*;


public class LoginServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html"); 
		response.setCharacterEncoding("UTF-8");
		DBHandle dbh=new DBHandle();
		String user=request.getParameter("user");//得到用户名
		String psd=request.getParameter("psd");//得到用户的密码
		String role=request.getParameter("role");//得到用户的角色
		String flag=request.getParameter("flag"); 
		boolean b=false;
		HttpSession sess=request.getSession();
		sess.setAttribute("user", user);
		ArrayList<String> password=new ArrayList<String>();
		if(flag.equals("login")){
			if(role.equals("admin")){
				sess.setAttribute("role", "管理员");
				password=dbh.getPassword("select Man_psd from Man_tb where Man_name='"+user+"'");
				for(int i=0;i<password.size();i++){				
					if(password.get(i).equals(psd)){						
						b=true;
						break;
					}	
				}
				if(b==true){
					response.sendRedirect("Manager.jsp");
				}else{
					response.sendRedirect("Login.jsp?flag=error");
				}
							
			}else if(role.equals("student")){
				sess.setAttribute("role", "学生");
				password=dbh.getPassword("select Stu_psd from Stu_tb where Stu_name='"+user+"'");
				for(int i=0;i<password.size();i++){
					if(password.get(i).equals(psd)){
						b=true;
						break;	
					}
				}
				if(b==true){
					response.sendRedirect("Student.jsp");
				}else{
					response.sendRedirect("Login.jsp?flag=error");
				}							
			}else if(role.equals("teacher")){
				sess.setAttribute("role", "教师");
				password=dbh.getPassword("select Tea_psd from Tea_tb where Tea_name='"+user+"'");
				for(int i=0;i<password.size();i++){				
					if(password.get(i).equals(psd)){
						b=true;
						break;				
					}						
				}
				if(b==true){
					response.sendRedirect("Teacher.jsp");				
				}else{
					response.sendRedirect("Login.jsp?flag=error");
				}			
			}
			
		}else if(flag.equals("invalidate")){
			sess.invalidate();
			response.sendRedirect("Login.jsp");
		}
		
	}	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
		
	}

}
