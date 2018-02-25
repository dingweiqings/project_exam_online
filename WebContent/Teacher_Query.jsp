<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	String user=(String)session.getAttribute("user");
	String role=(String)session.getAttribute("role");	
	if(user==null||!"教师".equals(role)){
		response.sendRedirect("Login.jsp?flag=login");
	}
	DBHandle dbh=new DBHandle();
	ArrayList<String> Class_name=(ArrayList<String>)request.getAttribute("Class_name");
	ArrayList<String> Cou_name=(ArrayList<String>)request.getAttribute("Cou_name");	
	ArrayList<Score> al=(ArrayList<Score>)request.getAttribute("Score");
	String class_name=(String)request.getAttribute("class_name");
	String cou_name=(String)request.getAttribute("cou_name");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
  </head>  
  
  <body>
<table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
  <tr height="260">
    <td ><jsp:include page="T_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="440"><td><form action="TeacherServlet?flag=query" method="post">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="30" valign="top" ><h4>当前位置：<a href="Teacher.jsp">教师</a>——>查询成绩</h4></td>
		  </tr>
		 
	  	  <tr>
		      <td align="center" height="70"><h2>学生成绩表</h2></td>
		  </tr>
		  <tr >
			    <td align="center" height="30">班级：				    
			    <label>
			      <select name="Class_name" id="select">
			     <%
			    	if(class_name!=null&&class_name!=""){
			    %>
			    	<option selected="selected" value="<%=class_name %>"><%=class_name %></option>
			    	
			    <%
			    }
			    %>
			   <option ></option>
			    <%		    		       
			        for(int i=0;i<Class_name.size();i++){				        
			        %>
			        <option value="<%=Class_name.get(i) %>"><%=Class_name.get(i) %></option>
			        <%
			        }
			        %>		        
			      </select>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 	 课程：			    
			      <select name="Cou_name" id="select">
			     <%
			    	if(cou_name!=null&&cou_name!=""){
			    %>
			    	<option selected="selected" value="<%=cou_name %>" ><%=cou_name %></option>
			    	
			    <%
			    }
			    %>
			   <option ></option>
			    <%		    				       
			        for(int i=0;i<Cou_name.size();i++){				        
			        %>
			        <option value="<%=Cou_name.get(i) %>"><%=Cou_name.get(i) %></option>
			        <%
			        }
			        %>		        
			      </select>
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			 	
			    <input type="submit" name="query" id="query" value="查询" />
			    </label></td>
		  </tr>
		  <tr>
		    <td height="280"><table width="100%" border="1" align="center">
		      <tr bgcolor="#FFFFCC">
		        <td align="center" height="30">ID号</td>		        
		        <td align="center">姓名</td>
		        <td align="center">班级</td>		        
		        <td align="center">课程</td>	
		        <td align="center">教师</td>
		        <td align="center">成绩</td>			        
		      </tr>
		      <% 
		       String[] color={"FFCCFF","#CC99FF","#FFFFCC"};
		       if(al==null||al.size()==0){
		      
		      %>
		      <tr bgcolor="#FFCCFF">
		        <td align="center" colspan="7">对不起，没有相关记录</td>		        
		      </tr>
		      <%
		      }else{
		      	for(int i=0;i<al.size();i++){
		      		Score score=al.get(i);%>
		      	<tr bgcolor="<%=color[i%3] %>">
		        <td align="center" height="25"><%=score.getScore_ID() %></td>
		        <td align="center"><%=score.getStu_name() %></td>
		        <td align="center"><%=score.getClass_name() %></td>  
		        <td align="center"><%=score.getCou_name() %></td>
		        <td align="center"><%=score.getTea_name() %></td>
		        <td align="center"><%=score.getScore_score() %></td>		        
		      </tr>
		      		<%
		      	}
		      }
		      %>        
		    </table></td>
		  </tr>
		  <tr>
		  	<%
		  	int pageCount=Integer.parseInt((String)request.getAttribute("pageCount"));
		  	int pageNow=Integer.parseInt((String)request.getAttribute("pageNow"));
		  	 %>
		    <td align="center" height="30">共有&nbsp;<font color="#FF0000"><%=request.getAttribute("rowCount") %></font>&nbsp;条记录，
		    &nbsp;共&nbsp;<font color="#FF0000"><%=pageCount%></font>&nbsp;页，
		    &nbsp;&nbsp;&nbsp;&nbsp;当前第&nbsp;<font color="#FF0000"><%=pageNow %></font>&nbsp;页
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="TeacherServlet?flag=query&pageNow=1&Class_name=<%=class_name %>&Cou_name=<%=cou_name %>">首页</a>		
		    <% if(pageNow!=1){		    
		    %>
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="TeacherServlet?flag=query&pageNow=<%=pageNow-1 %>&Class_name=<%=class_name %>&Cou_name=<%=cou_name %>">上一页</a>
		    <%
		     }		    
		    %>
		      <% if(pageNow!=pageCount){		    
		    %>
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="TeacherServlet?flag=query&pageNow=<%=pageNow+1 %>&Class_name=<%=class_name %>&Cou_name=<%=cou_name %>">下一页</a>
		    <%
		     }		    
		    %>   
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="TeacherServlet?flag=query&pageNow=<%=pageCount %>&Class_name=<%=class_name %>&Cou_name=<%=cou_name %>">尾页</a>
		    &nbsp;&nbsp;&nbsp;&nbsp;
		      <label>
		      <select name="pageNow" id="select">
		      <option></option>
		        <%
		        for(int i=1;i<=pageCount;i++){
		        
		        %>
		        <option><%=i %></option>
		        <%
		        }
		        %>		        
		      </select>
		      <input type="submit" name="button" id="button" value="go" />
		    </label></td>
		  </tr>	
		</table></form>
		</td>
  </tr>
  <tr height="100">
    <td align="center"><jsp:include page="Tail.jsp"></jsp:include><br></td>
  </tr>
</table>
</body>
</html>