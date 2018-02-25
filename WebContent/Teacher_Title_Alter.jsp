<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");  
String role=(String)session.getAttribute("role");
    if(user==null||!"教师".equals(role))
    {    	
    	response.sendRedirect("Login.jsp?flag=login");
    }

ArrayList<String> Cou_name=(ArrayList<String>)request.getAttribute("Course_name");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript">
		function check()
			{
				if((document.form1.Title_name.value)=='')
				{
					window.alert('题目不能为空，请输入题目');	
					document.form1.Title_name.focus();
					return false;
				}else if((document.form1.Cou_name.value)=='')
				{
					window.alert('课程不能为空，请选择课程');
					document.form1.Cou_name.focus();
					return false;
				}else if((document.form1.Title_opt_a.value)=='')
				{
					window.alert('选项A不能为空，请输入选项A');
					document.form1.Title_opt_a.focus();
					return false;
				}else if((document.form1.Title_opt_b.value)=='')
				{
					window.alert('选项B不能为空，请输入选项B');
					document.form1.Title_opt_b.focus();
					return false;
				}else if((document.form1.Title_opt_c.value)=='')
				{
					window.alert('选项C不能为空，请输入选项C');
					document.form1.Title_opt_c.focus();
					return false;
				}else if((document.form1.Title_opt_d.value)=='')
				{
					window.alert('选项D不能为空，请输入选项D');
					document.form1.Title_opt_d.focus();
					return false;
				}else if((document.form1.Title_answer.value)=='')
				{
					window.alert('答案不能为空，请选择答案');
					document.form1.Title_answer.focus();
					return false;
				}else if((document.form1.Title_score.value)=='')
				{
					window.alert('分值不能为空，请选择分值');
					document.form1.Title_score.focus();
					return false;
				}else{
					return true;
				}
			}
	</script>

  </head>
  
  <body>
   <table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="900">
  <tr height="260">
    <td ><jsp:include page="T_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="540"><td><form action="Teacher_TitleServlet?flag=altercl" method="post" name="form1">
	<table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="50" valign="top" ><h4>当前位置：<a href="Teacher.jsp">教师</a>——><a href="TeacherServlet?flag=title">教师题库</a>——>修改题目</h4></td>
		  </tr>
		  <tr align="center">
				<td height="50"><h1>修改题目</h1></td>
		  </tr>
		  
		  <tr>
		    <td align="center" height="440">
		    
		    	<table background="images/background.jpg" width="80%" height="440" border="1" bgcolor="#CCCCCC">
  <tr><td align="right" height="35" width="30%">课程&nbsp;&nbsp;&nbsp;:</td>
            <td align="left"><label>
            <input type="hidden" name="id" value="<%=request.getAttribute("Title_ID") %>" />
              <select name="Cou_name">
               <option value="<%=request.getAttribute("Cou_name") %>"><%=request.getAttribute("Cou_name") %></option>
		             <% 
		             for(int i=0;i<Cou_name.size();i++)
		              {
		              	
		              %>
		              	<option value="<%=Cou_name.get(i)%>"><%=Cou_name.get(i)%></option>
		             <%
		              }
		             %>
              </select>
              </label>
       </td>
  </tr>
  <tr>
    <td align="right">题&nbsp;&nbsp;&nbsp;&nbsp;干&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><label>
      <textarea name="Title_name" id="Title_name" cols="40" rows="3" ><%=request.getAttribute("Title_name") %></textarea>
    </label></td>
  </tr>
  <tr>
    <td align="right">选&nbsp;&nbsp;&nbsp;&nbsp;项&nbsp;&nbsp;&nbsp;&nbsp;A</td>
    <td><label>
      <textarea name="Title_opt_a" id="Title_opt_a" cols="40" rows="3"><%=request.getAttribute("Title_opt_a") %></textarea>
    </label></td>
  </tr>
  <tr>
    <td align="right">选&nbsp;&nbsp;&nbsp;&nbsp;项&nbsp;&nbsp;&nbsp;&nbsp;B</td>
    <td><label>
      <textarea name="Title_opt_b" id="Title_opt_b" cols="40" rows="3"><%=request.getAttribute("Title_opt_b") %></textarea>
    </label></td>
  </tr>
  <tr>
    <td align="right">选&nbsp;&nbsp;&nbsp;&nbsp;项&nbsp;&nbsp;&nbsp;&nbsp;C</td>
    <td><label>
      <textarea name="Title_opt_c" id="Title_opt_c" cols="40" rows="3"><%=request.getAttribute("Title_opt_c") %></textarea>
    </label></td>
  </tr>
  <tr>
    <td align="right">选&nbsp;&nbsp;&nbsp;&nbsp;项&nbsp;&nbsp;&nbsp;&nbsp;D</td>
    <td><label>
      <textarea name="Title_opt_d" id="Title_opt_d" cols="40" rows="3"><%=request.getAttribute("Title_opt_d") %></textarea>
    </label></td>
  </tr>
  <tr>
    <td align="right">分&nbsp;&nbsp;&nbsp;&nbsp;值&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td><label>
      <select name="Title_score" id="Title_score">
        <option value="<%=request.getAttribute("Title_score") %>"><%=request.getAttribute("Title_score") %></option>
        <option value="2">2</option>
        <option value="4">4</option>
        <option value="8">8</option>
      </select>
    </label></td>
  </tr>
  <tr>
    <td align="right">正&nbsp;确&nbsp;答&nbsp;案</td>
    <td><label>
      <select name="Title_answer" id="Title_score">
        <option value="<%=request.getAttribute("Title_answer") %>"><%=request.getAttribute("Title_answer") %></option>
        <option value="A">A</option>
        <option value="B">B</option>
        <option value="C">C</option>
        <option value="D">D</option>
      </select>
    </label></td>
  </tr>
  <tr>
    <td align="right"><label>
      <input type="submit" name="button" id="button" value="提交" onclick="javascript:return check();" />&nbsp;&nbsp;&nbsp;&nbsp;
    </label></td>
    <td><label>
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
