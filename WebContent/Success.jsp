<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Success.jsp' starting page</title>
    
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
  <table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
  <tr height="260">
    <td ><jsp:include page="M_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="120">
    <td align="center"><h2>
       恭喜你！<font color="red"><%=session.getAttribute("role") %></font>，&nbsp;&nbsp;&nbsp;<%=session.getAttribute("user") %>！&nbsp;&nbsp;&nbsp;<%=session.getAttribute("message") %> <br>
   </h2> </td>
  </tr>
  <tr height="120">
  <td valign="top" align="center">
  <input type="button" name="button" value="继续" onclick="window.history.go(-1)" size="12"/>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <input type="button" name="button" value="返回" onclick="window.history.go(-2)" size="12"/>
  </td></tr>
  <tr height="200">
  <td></td>
  </tr>
  <tr height="100">
    <td align="center"><jsp:include page="Tail.jsp"></jsp:include></td>
  </tr>
</table>
 
  </body>
</html>
