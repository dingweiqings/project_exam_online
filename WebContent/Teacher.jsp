<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>教师主页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="1000">
  <tr height="260">
    <td ><jsp:include page="T_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="640">
    <td align="center"><img src="./images/Login.jpg" width="920" height="640" /></td>
  </tr>
  <tr height="100">
    <td align="center"><jsp:include page="Tail.jsp"></jsp:include></td>
  </tr>
</table>
</body>
</html>