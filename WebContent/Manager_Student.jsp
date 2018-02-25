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
	DBHandle dbHandle=new DBHandle();
	ArrayList<Student> al=(ArrayList<Student>)request.getAttribute("Student");
	ArrayList<Banji> banji=(ArrayList<Banji>)request.getAttribute("Banji");
	ArrayList<String > Class_name=new ArrayList<String>();
	
	Iterator<Banji> it=banji.iterator();
	while(it.hasNext()){
	Class_name.add(it.next().getClass_name());
	}
	String name=(String)request.getAttribute("name");
	String class_name=(String)request.getAttribute("class");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学生管理</title>
    
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
  	return window.confirm("你确定要删除该学生吗？");
  }
  </script>
  
  <body>
<table width="920" border="0" align="center" cellpadding="0" cellspacing="0" height="800">
  <tr height="260">
    <td ><jsp:include page="M_Top.jsp"></jsp:include></td>
  </tr>
  <tr height="440"><td><form action="ManagerServlet?flag=student" method="post">
		    <table width="920" border="0" align="center">
		  <tr>
		    <td align="left" height="30" valign="top" ><h4>当前位置：<a href="Manager.jsp">管理员</a>——>学生管理</h4></td>
		  </tr>
		 
	  	  <tr>
		      <td align="center" height="70"><h2>学生信息表</h2></td>
		  </tr>
		  <tr >
			    <td align="center" height="30">班级：				    
			    <label>
			      <select name="Class_name" id="select">
			      <%
			    	if(class_name!=null&&class_name!=""){
			    %>
			    	<option selected="selected"><%=class_name %></option>
			    <%
			    }
			    %>
			   <option></option>
			    <%			     			       
			        for(int i=0;i<Class_name.size();i++){				        
			        %>
			        <option><%=Class_name.get(i) %></option>
			        <%
			        }
			        %>		        
			      </select>
			    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓名：
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
			    </label></td>
		  </tr>
		  <tr>
		    <td height="280"><table width="80%" border="1" align="center">
		      <tr bgcolor="#FFFFCC">
		        <td align="center" height="30">ID号</td>
		        <td align="center">学号</td>
		        <td align="center">姓名</td>
		        <td align="center">密码</td>
		        <td align="center">班级</td>
		        <td align="center"><a href="Manager_Student_Add.jsp">添加学生</a></td>
		        <td align="center"></td>
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
		      		Student st=al.get(i);%>
		      	<tr bgcolor="<%=color[i%3] %>">
		        <td align="center" height="25"><%=st.getStu_ID() %></td>
		        <td align="center"><%=st.getStu_num() %></td>
		        <td align="center"><%=st.getStu_name() %></td>
		        <td align="center"><%=st.getStu_psd() %></td>
		        <td align="center"><%=st.getStu_class() %></td>
		        <td align="center"><a href="Manager_StudentServlet?flag=alter&Stu_ID=<%=st.getStu_ID() %>">修改信息</a></td>
		        <td align="center"><a href="Manager_StudentServlet?flag=delete&Stu_ID=<%=st.getStu_ID() %>" onClick="javascript:return check()">删除学生</a></td>
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
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=student&pageNow=1&name=<%=name %>&Class_name=<%=class_name %>">首页</a>		
		    <% if(pageNow!=1){		    
		    %>
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=student&pageNow=<%=pageNow-1 %>&name=<%=name %>&Class_name=<%=class_name %>">上一页</a>
		    <%
		     }		    
		    %>
		      <% if(pageNow!=pageCount){		    
		    %>
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=student&pageNow=<%=pageNow+1 %>&name=<%=name %>&Class_name=<%=class_name %>">下一页</a>
		    <%
		     }		    
		    %>   
		    &nbsp;&nbsp;&nbsp;&nbsp;<a href="ManagerServlet?flag=student&pageNow=<%=pageCount %>&name=<%=name %>&Class_name=<%=class_name %>">尾页</a>
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