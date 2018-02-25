<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");
if(user==null){
		response.sendRedirect("Login.jsp?flag=login");
	}
DBHandle dbh=new DBHandle();
ArrayList<String> bj=dbh.getAttribute("select Class_name from Class_tb");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加学生</title>
    
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
			if((document.form1.Stu_name.value)==""){
				alert("姓名不能为空,请输入姓名！");
				document.form1.Stu_name.focus();
				return false;
			}else if((document.form1.Stu_num.value)==""){
				alert("学号不能为空，请输入学号!")
				document.form1.Stu_num.focus();
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
  <tr height="440"><td><form action="Manager_StudentServlet?flag=add" method="post" name="form1">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="30" valign="top" ><h4>当前位置：<a href="Manager.jsp">管理员</a>——><a href="ManagerServlet?flag=student">学生管理</a>——>添加学生</h4></td>
		  </tr>
		  <tr>
		    <td align="center" height="400">
			<table width="60%" border="1" align="center" bgcolor="#DDDDDD">
				  <tr align="center">
				    <td colspan="2" height="100" bgcolor="#BBBBBB"><h3>添加学生</h3></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40" bgcolor="#DDDDDD">姓名：</td>
				    <td><label>
				      <input type="text" name="Stu_name" id="Stu_name" size="10"/>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40" >学号：</td>
				    <td><label>
				      <input type="text" name="Stu_num" id="Stu_num" size="10"/>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40">密码：</td>
				    <td><label>
				      <input type="password" name="Stu_psd" id="Stu_psd" value="123456" disabled="disabled" size="12"/>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40">班级：</td>
				    <td><label>
				      <select name="Stu_class" id="select">
				        <% for(int i=0;i<bj.size();i++){
				        %>
				        <option value="<%=bj.get(i)%>"><%=bj.get(i) %></option>
				        <%
				        }
				         %>				        
				      </select>
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
