<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<% 
	String user=(String)session.getAttribute("user");
	if(user==null){
		response.sendRedirect("Login.jsp?flag=login");
	}
	String name=(String)request.getAttribute("name");
	DBHandle dbHandle=new DBHandle();

	ArrayList<Banji> al=(ArrayList<Banji>)request.getAttribute("Banji");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>班级管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  function check(){
  	return window.confirm("你确定要删除该班级吗？");
  }
  </script>
  
  <body>
<table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
  <tr height="260">
    <td ><jsp:include page="M_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="440"><td><form action="ManagerServlet?flag=class" method="post">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="30" valign="top" ><h4>当前位置：<a href="Manager.jsp">管理员</a>——>班级管理</h4></td>
		  </tr>
		 
	  	  <tr>
		      <td align="center" height="70"><h2>班级信息表</h2></td>
		  </tr>
		  <tr >
			    <td align="center" height="30">
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 班级名：
			    <%
			    	if(name!=null){
			    %>
			    	<input name="name" type="text" id="name" value="<%=name %>" size="15" />
			    <%
			    }else{
			    %>
			    <input name="name" type="text" id="name" value="" size="15" />
			    <%
			    }
			    %>
			    <input type="submit" name="query" id="query" value="查询" />
			   </td>
		  </tr>
		  <tr>
		    <td height="280"><table width="60%" border="1" align="center">
		      <tr bgcolor="#FFFFCC">
		        <td align="center" height="30">ID号</td>		        
		        <td align="center">班级名</td>		                
		        <td align="center"><a href="Manager_Class_Add.jsp">添加班级</a></td>		        
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
		      		Banji bj=al.get(i);%>
		      	<tr bgcolor="<%=color[i%3] %>">
		        <td align="center" height="25"><%=bj.getClass_ID() %></td>
		        <td align="center"><%=bj.getClass_name() %></td>		            
		        <td align="center"><a href="Manager_ClassServlet?flag=delete&Class_ID=<%=bj.getClass_ID() %>" onClick="javascript:return check()">删除班级</a></td>
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
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=class&pageNow=1&name=<%=name %>">首页</a>		
		    <% if(pageNow!=1){		    
		    %>
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=class&pageNow=<%=pageNow-1 %>&name=<%=name %>">上一页</a>
		    <%
		     }		    
		    %>
		      <% if(pageNow!=pageCount){		    
		    %>
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=class&pageNow=<%=pageNow+1 %>&name=<%=name %>">下一页</a>
		    <%
		     }		    
		    %>   
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=class&pageNow=<%=pageCount %>&name=<%=name %>">尾页</a>
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
