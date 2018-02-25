<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");  
    if(user==null)
    {    	
    	response.sendRedirect("Login.jsp?flag=login");    }
DBHandle dbh=new DBHandle();
String Title_2=request.getParameter("Title_2");
String Title_4=request.getParameter("Title_4");
String Title_8=request.getParameter("Title_8");
int Title_22=Integer.parseInt(Title_2);
int Title_44=Integer.parseInt(Title_4);
int Title_88=Integer.parseInt(Title_8);
int j=1;
int total=0;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
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
  <div align="center">
  <table width="860" height="700" border="0" background="images/background.jpg" >
    <tr>
      <td>
      <jsp:include page="S_Top.jsp"></jsp:include>
      </td>
    </tr>
    <tr>      
<td width="100%">
<center>
<h1>测试结果表</h1>
<table width="100%" border="1" bgcolor="#CCCCCC">
  <tr align="center">
    <td align="center" width="20%">题号</td>
    <td align="center" width="20%">所选答案</td>
    <td align="center" width="20%">正确答案</td>
    <td align="center" width="20%">分值</td>
    <td align="center" width="20%">得分</td>
  </tr>
  <% 
  	for(int i=0;i<Title_22;i++){  	
  		String answer1=request.getParameter("bot"+j);
  		if(answer1==null) answer1="未做";
  		String answer2=request.getParameter("answer"+j);
  		int fenshu=0;
  	%>
  	<tr>
    <td align="center"><%=j %></td>
    <td align="center"><%=answer1 %></td>
    <td align="center"><%=answer2 %></td>
    <td align="center">2</td>
    <%
    	if(!answer1.equals(answer2)){
    		fenshu=0;
    	}else fenshu=2;
     %>
    <td align="center"><%=fenshu %></td>
  </tr>
  	<%
  		total+=fenshu;
  		j++;
  	}
   %>
  <% 
  	for(int i=0;i<Title_44;i++){
  	
  		String answer1=request.getParameter("bot"+j);
  		if(answer1==null) answer1="未做";
  		String answer2=request.getParameter("answer"+j);
  		int fenshu=0;
  	%>
  	<tr>
    <td align="center"><%=j %></td>
    <td align="center"><%=answer1 %></td>
    <td align="center"><%=answer2 %></td>
    <td align="center">4</td>
    <%
    	if(!answer1.equals(answer2)){
    		fenshu=0;
    	}else fenshu=4;
     %>
    <td align="center"><%=fenshu %></td>
  </tr>
  	<%
  		total+=fenshu;
  		j++;
  	}
   %>
  <% 
  	for(int i=0;i<Title_88;i++){
  	
  		String answer1=request.getParameter("bot"+j);
  		if(answer1==null) answer1="未做";
  		String answer2=request.getParameter("answer"+j);
  		int fenshu=0;
  	%>
  	<tr>
    <td align="center"><%=j %></td>
    <td align="center"><%=answer1 %></td>
    <td align="center"><%=answer2 %></td>
    <td align="center">8</td>
    <%
    	if(!answer1.equals(answer2)){
    		fenshu=0;
    	}else fenshu=8;
     %>
    <td align="center"><%=fenshu %></td>
  </tr>
  	<%
  		total+=fenshu;
  		j++;
  	}
   %>
   <tr><td colspan="5" align="center"><h3>你的总分是：<%=total %></h3></td></tr>
   <%
   				
   		int Cou_ID=Integer.parseInt(request.getParameter("Cou_ID")); 		
   		int Stu_ID=dbh.getID("select Stu_ID from Stu_tb where Stu_name='"+user+"'");
   		int Class_ID=dbh.getID("select Stu_class from Stu_tb where Stu_name='"+user+"'");
   		int Tea_ID=dbh.getID("select Tea_ID from Tea_Cou_Class_tb where Class_ID='"+Class_ID+"' and Cou_ID='"+Cou_ID+"'");
   		String sql="insert into Score_tb(Stu_ID,Class_ID,Tea_ID,Cou_ID,Score_score) values("+Stu_ID+","+Class_ID+","+Tea_ID+","+Cou_ID+","+total+")";
   		boolean b=dbh.Update(sql);
    %>
</table>
</center>
</td>
</tr>
</table>
</div>
</body>
</html>