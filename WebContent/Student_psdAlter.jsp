<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");
String role=(String)session.getAttribute("role");	
if(user==null||!"学生".equals(role)){
		response.sendRedirect("Login.jsp?flag=login");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改密码</title>
    
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
			if((document.form1.old_psd.value)==""){
				alert("原密码不能为空，请输入原密码！");
				document.form1.old_psd.focus();
				return false;
			}else if((document.form1.new_psd.value=="")){
				alert("请输入新密码，密码不能为空！");
				document.form1.new_psd.focus();
				return false;
			}else if(document.form1.new_psd_check.value==""){
				alert("请再次输入新密码！");
				document.form1.new_psd_check.focus();
				return false;
			}
			else if((document.form1.new_psd.value)!=(document.form1.new_psd_check.value)){
				alert("两次输入不一致，请重新检查!")
				document.form1.new_psd.value="";
				document.form1.new_psd_check.value="";
				document.form1.new_psd.focus();
				return false;
			}else return true;
		}
	</script>

  </head>
  
  <body>
   <table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
 <tr height="260">
    <td ><jsp:include page="S_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="440"><td><form action="StudentServlet?flag=psdAlter" method="post" name="form1">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="30" valign="top" ><h4>当前位置：<a href="Student.jsp">学生</a>——>修改密码</h4></td>
		  </tr>
		  <tr>
		    <td align="center" height="400">
		    <%
		    	String flag=request.getParameter("flag");
		    	if(flag!=null){
			    	if(flag.equals("error")){
			    	%>
			    	<h2 align="center">对不起，你输入的原密码错误，无权进行修改！</h2><hr/>
			    	<%
			    	}
			    }
		     %>
			<table width="60%" border="1" align="center" bgcolor="#DDDDDD">
				  <tr align="center">
				    <td colspan="2" height="80" bgcolor="#BBBBBB"><h2>修改密码</h2></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40" bgcolor="#DDDDDD">原密码：</td>
				    <td><label>
				      <input type="password" name="old_psd" id="old_psd" size="12"/>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40" >新密码：</td>
				    <td><label>
				      <input type="password" name="new_psd" id="new_psd" size="12"/>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40">再次输入新密码：</td>
				    <td><label>
				      <input type="password" name="new_psd_check" id="new_psd_check" size="12"/>
				    </label></td>
				  </tr>				  
				  <tr>
				    <td align="right" width="50%" height="40"><label>
				      <input type="submit" name="button" id="button" value="提交" onClick="javascript:return check()"/>
				    </label>&nbsp;&nbsp;</td>
				    <td><label>&nbsp;&nbsp;
				      <input type="reset" name="button2" id="button2" value="重置" />
				    </label></td>
				  </tr>
				</table>
		    </td>
		  </tr>		 
		</table>
		</form></td>
  </tr>
  <tr height="100">
    <td align="center"><jsp:include page="Tail.jsp"></jsp:include><br></td>
  </tr>
</table>
  </body>
</html>