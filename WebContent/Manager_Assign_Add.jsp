<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");
if(user==null){
		response.sendRedirect("Login.jsp?flag=login");
	}
DBHandle dbh=new DBHandle();
ArrayList<String> Class_name=dbh.getAttribute("select Class_name from Class_tb");
ArrayList<String> Tea_name=dbh.getAttribute("select Tea_name from Tea_tb");
ArrayList<String> Cou_name=dbh.getAttribute("select Cou_name from Cou_tb");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分配课程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function check(){
			var Class_name=document.getElementById("Class_name").value;
			var Tea_name=document.getElementById("Tea_name").value;
			var Cou_name=document.getElementById("Cou_name").value;			
			if(Class_name==""){
				window.alert("班级不能为空,请选择班级！");				
				return false;
			}else if(Cou_name==""){
				window.alert("课程不能为空,请选择课程！");				
				return false;
			}else if(Tea_name==""){
				window.alert("教师不能为空,请选择教师！");				
				return false;
			}
			else return true;
		}
	</script>

  </head>
  
  <body>
   <table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
  <tr height="260">
    <td ><jsp:include page="M_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="440"><td><form action="Manager_AssignServlet?flag=add" method="post" name="form1">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="50" valign="top" ><h4>当前位置：<a href="Manager.jsp">管理员</a>——><a href="ManagerServlet?flag=assign">教师-课程-班级管理</a>——>添加教师-课程-班级分配</h4></td>
		  </tr>
		  <tr align="center">
				    <td colspan="2" height="100"><h1>添加教师-课程-班级分配</h1></td>
		</tr>
		  <tr>
		    <td align="center" height="30">班级：				    
			    <label>
			      <select name="Class_name" id="Class_name">
			      <option value=""></option>
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
			      <select name="Cou_name" id="Cou_name">
			      <option value=""></option>
			        <%				       
			        for(int i=0;i<Cou_name.size();i++){				        
			        %>
			        <option value="<%=Cou_name.get(i) %>"><%=Cou_name.get(i) %></option>
			        <%
			        }
			        %>		        
			      </select>
			      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 	 教师：			    
			      <select name="Tea_name" id="Tea_name">
			      <option value=""></option>
			        <%				       
			        for(int i=0;i<Tea_name.size();i++){				        
			        %>
			        <option value="<%=Tea_name.get(i) %>"><%=Tea_name.get(i) %></option>
			        <%
			        }
			        %>		        
			      </select>	
			        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;		   
			    <input type="submit" name="query" id="query" value="提交" onclick="javascript:return check()"/>
			    </label></td>
		  </tr>	
		  <tr height="240"><td>&nbsp;</td></tr>	 
		</table>
		</form></td>
  </tr>
  <tr height="100">
    <td align="center"><jsp:include page="Tail.jsp"></jsp:include><br></td>
  </tr>
</table>
  </body>
</html>

