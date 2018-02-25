<%@ page language="java" import="java.util.*,gao.java.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String user=(String)session.getAttribute("user");  
    if(user==null)
    {    	
    	response.sendRedirect("Login.jsp?flag=login");
    }
request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
DBHandle dbh=new DBHandle();
ArrayList<Title> a22=(ArrayList<Title>)request.getAttribute("a22");
ArrayList<Title> a44=(ArrayList<Title>)request.getAttribute("a44");
ArrayList<Title> a88=(ArrayList<Title>)request.getAttribute("a88");
String Cou_name=(String)request.getAttribute("Cou_name");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>在线考试</title>
    
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
  <table width="860" height="700" border="0">
    
    <tr>      
<td width="100%">
<center>
<form action="Student_Result.jsp" method="post" id="form1" target="_blank">
<table width="100%" border="0">
  <tr>
    <td align="center" class="title"> <h2>  <%=request.getAttribute("Cou_name") %>课程测试题</h2> </td>
  </tr>
  <tr><td><input type="hidden" name="Cou_ID" value="<%=request.getAttribute("Cou_ID")%>" ></td></tr>
  <tr><td><input type="hidden" name="Title_2" value="<%=a22.size()%>" ></td></tr>
  <tr><td><input type="hidden" name="Title_4" value="<%=a44.size()%>" ></td></tr>
  <tr><td><input type="hidden" name="Title_8" value="<%=a88.size()%>" ></td></tr>
  <tr>
    <td><h3>一、2分题，以下每题都有A、B、C、D四个选项，只有一个正确选项，选择正确的得2分，选错或不选不扣分。</h3></td>
  </tr>
 <% 
  int j=1;
  for(int i=0;i<a22.size();i++){
  	  Title tl=(Title)a22.get(i);
  
  %>
  <tr><td align="center"><label></label>     
      <table width="100%" border="0">
       <tr><td colspan="2"><%=i+1 %>、<%=tl.getTitle_name() %>(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)</td></tr>
       <tr>
          <td width="50%"><label><input type="radio"  name="bot<%=j%>" value="A" id="RadioGroup1_0" />A</label>、<%=tl.getTitle_opt_a() %></td>
          <td width="50%"><label><input type="radio" name="bot<%=j%>" value="B" id="RadioGroup1_1" />B</label>、<%=tl.getTitle_opt_b() %></td>
        </tr>
        <tr>
          <td><label><input type="radio" name="bot<%=j%>" value="C" id="RadioGroup1_2" />C</label>、<%=tl.getTitle_opt_c() %></td>
          <td><label><input type="radio" name="bot<%=j%>" value="D" id="RadioGroup1_3" />D</label>、<%=tl.getTitle_opt_d() %></td>          
        </tr>
         <tr><td><input type="hidden" name="answer<%=j%>" value="<%=tl.getTitle_answer() %>" ></td></tr>
      </table>
    </td>
  </tr>
  <%
  	j++;
  	}
  %>
  <tr>
    <td><h3>二、4分题，以下每题都有A、B、C、D四个选项，只有一个正确选项，选择正确的得4分，选错或不选不扣分。</h3></td>
  </tr>
  <% 
  
  for(int i=0;i<a44.size();i++){
  	  Title tl=(Title)a44.get(i);
  
  %>
  <tr><td align="center"><label></label>     
      <table width="100%" border="0">
       <tr><td colspan="2"><%=i+1 %>、<%=tl.getTitle_name() %>(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)</td></tr>
       <tr>
          <td width="50%"><label><input type="radio"  name="bot<%=j%>" value="A" id="RadioGroup1_0" />A</label>、<%=tl.getTitle_opt_a() %></td>
          <td width="50%"><label><input type="radio" name="bot<%=j%>" value="B" id="RadioGroup1_1" />B</label>、<%=tl.getTitle_opt_b() %></td>
        </tr>
        <tr>
          <td><label><input type="radio" name="bot<%=j%>" value="C" id="RadioGroup1_2" />C</label>、<%=tl.getTitle_opt_c() %></td>
          <td><label><input type="radio" name="bot<%=j%>" value="D" id="RadioGroup1_3" />D</label>、<%=tl.getTitle_opt_d() %></td>
         </tr>
         <tr><td><input type="hidden" name="answer<%=j%>" value="<%=tl.getTitle_answer() %>" ></td></tr>
      </table>
    </td>
  </tr>
  <%
  	j++;
  	}
  %>
  <tr>
    <td><h3>三、8分题，以下每题都有A、B、C、D四个选项，只有一个正确选项，选择正确的得8分，选错或不选不扣分。</h3></td>
  </tr>
 <% 
  
  for(int i=0;i<a88.size();i++){
  	  Title tl=(Title)a88.get(i);
  
  %>
  <tr><td align="center"><label></label>     
      <table width="100%" border="0">
       <tr><td colspan="2"><%=i+1 %>、<%=tl.getTitle_name() %>(&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;)</td></tr>
       <tr>
          <td width="50%"><label><input type="radio"  name="bot<%=j%>" value="A" id="RadioGroup1_0" />A</label>、<%=tl.getTitle_opt_a() %></td>
          <td width="50%"><label><input type="radio" name="bot<%=j%>" value="B" id="RadioGroup1_1" />B</label>、<%=tl.getTitle_opt_b() %></td>
        </tr>
        <tr>
          <td><label><input type="radio" name="bot<%=j%>" value="C" id="RadioGroup1_2" />C</label>、<%=tl.getTitle_opt_c() %></td>
          <td><label><input type="radio" name="bot<%=j%>" value="D" id="RadioGroup1_3" />D</label>、<%=tl.getTitle_opt_d() %></td>          
        </tr>
        <tr><td><input type="hidden" name="answer<%=j%>" value="<%=tl.getTitle_answer() %>" ></td></tr>
      </table>
    </td>
  </tr>
  <%
  	j++;
  	}
  %>
 <tr><td><input type="submit" value="交卷" name="submit"></td></tr>
</table>
</form>


  
</center>
</td>
</tr>
</table>
</div>
</body>
</html>
