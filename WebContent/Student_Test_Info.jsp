<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");
String role=(String)session.getAttribute("role");
if(user==null||!"学生".equals(role)){
		response.sendRedirect("Login.jsp?flag=login");
	}
DBHandle dbh=new DBHandle();
int stu_ID=dbh.getID("select Stu_ID from Stu_tb where Stu_name='"+user+"'");
ArrayList<Course> course=dbh.getCourse("select * from db_exam.cou_tb");
ArrayList<String> Cou_name=new ArrayList<String>();
Iterator<Course> it=course.iterator();
while(it.hasNext())
Cou_name.add(it.next().getCou_name());
String cou_name=(String)request.getAttribute("Cou_name");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>设置试卷参数</title>
    
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
				s2=document.form1.Title_2.value;				
				s4=document.form1.Title_4.value;				
				s8=document.form1.Title_8.value;				
				a2=parseInt(s2);
				a4=parseInt(s4);
				a8=parseInt(s8);
				s=a8*8+a2*2+a4*4;
				
			if((document.form1.Cou_name.value)==""){
				alert("课程不能为空,请选择课程！");
				document.form1.Cou_name.focus();
				return false;
			}else if(s!=100)
				{
					//window.alert("参数设置不正确，请重新设置，当前总分为"+s+",总分须等于100");						
					return true;
				}else return true;
		}
	</script>

  </head>
  
  <body>
   <table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
  <tr height="260">
    <td ><jsp:include page="S_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="440"><td><form action="StudentServlet?flag=test" method="post" name="form1">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="30" valign="top" ><h4>当前位置：<a href="Student.jsp">学生</a>——>试卷参数设置</h4></td>
		  </tr>
		  <tr>
		    <td align="center" height="400">
			<table width="60%" border="1" align="center" bgcolor="#DDDDDD">
				  <tr align="center">
				    <td colspan="2" height="100" bgcolor="#BBBBBB">
				    <%
				    	String flag=request.getParameter("flag");
				    	if(flag!=null){
				    		if(flag.equals("error")){
				    		%>
				    		<<font color="red"><h2 align="center">对不起,题库中没有足够的题，不能进行测试！</h2></font>
				    		<hr>
				    		<%
				    		}
				    	}
				     %>
				    <h3>试卷参数设置</h3></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40" bgcolor="#DDDDDD">课程：</td>
				    <td><label>
				       <select name="Cou_name" id="Cou_name">
				      <%  if(cou_name!=null){
				      		%>
				      		<option value="<%=cou_name %>"><%=cou_name %></option>
				      		<%}       
				       %>
				      <option></option>
				      <% 				      
				      for(int i=0;i<Cou_name.size();i++){
				        %>
				        <option value="<%=Cou_name.get(i)%>"><%=Cou_name.get(i) %></option>
				        <%
				        }
				         %>	
				    </select>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40" >2分的题：</td>
				    <td><label>
				      <select name="Title_2" id="Title_2">
				      <option value="1" selected="selected">1</option>
				      <% 
				      int i=1;
				      for(i=1;i<=10;i=i+1){
				        %>
				        <option value="<%=i%>"><%=i %></option>
				        <%
				        }
				         %>	
				    </select>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40">4分的题：</td>
				    <td><label>
				       <select name="Title_4" id="Title_4">
				      <option value="1" selected="selected">1</option>
				      <% 
				      int j=1;
				      for(j=1;j<=10;j=j+1){
				        %>
				        <option value="<%=j%>"><%=j %></option>
				        <%
				        }
				         %>	
				    </select>
				    </label></td>
				  </tr>
				  <tr>
				    <td align="right" width="50%" height="40">8分的题：</td>
				    <td><label>
				       <select name="Title_8" id="Title_8">
				      <option value="1" selected="selected">1</option>
				      <% 
				      int k=1;
				      for(k=1;k<=5;k=k+1){
				        %>
				        <option value="<%=k %>"><%=k %></option>
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
