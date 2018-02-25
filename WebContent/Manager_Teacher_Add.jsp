<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");
if(user==null){
		response.sendRedirect("Login.jsp?flag=login");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教师管理</title>
    
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
			if((document.form1.Tea_name.value)==""){
				alert("姓名不能为空,请输入姓名！");
				document.form1.Tea_name.focus();
				return false;
			}else return true;
		}
	</script>

  </head>
  
  <body>
   <table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
  <tr height="260">
    <td ><jsp:include page="M_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="440"><td><form action="Manager_TeacherServlet?flag=add" method="post" name="form1">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="50" valign="top" ><h4>当前位置：<a href="Manager.jsp">管理员</a>——><a href="ManagerServlet?flag=teacher">教师管理</a>——>添加教师</h4></td>
		  </tr>
		  <tr align="center">
				    <td colspan="2" height="100"><h1>添加教师</h1></td>
				  </tr>
		  <tr>
		    <td align="center" height="50">			
		    <font size="3">
		           姓名：<input type="text" name="Tea_name" id="Tea_name" size="20"/>
		    <input type="submit" name="button" id="button" value="添加" onClick="javascript:return check()"/>
		    </font></td>
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
