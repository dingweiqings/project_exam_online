<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript">
		function check()
		{
			if((document.login.user.value)=='')
			{
				window.alert('用户名称不能为空，请输入用户名');	
				document.login.user.focus();
				return false;
			}else if((document.login.psd.value)=='')
			{
				window.alert('密码不能为空，请输入密码');
				document.login.psd.focus();
				return false;
			}else 
			{
				return true;
			}
		}
	
	   </script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	<script src="Scripts/swfobject_modified.js" type="text/javascript"></script>
	
	-->
  </head>
  
  
<body topmargin="0">
<table width="920" height="800" border="0" align="center">
  <tr>
    <td height="200">
    <table width="920" height="200" border="0">
      <tr>
        <td height="160" colspan="4" background="/images/top.jpg"><object id="FlashID" classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="920" height="160">
          <param name="movie" value="bg.swf" />
          <param name="quality" value="high" />
          <param name="wmode" value="transparent"/>
          <param name="swfversion" value="6.0.65.0" />
          <!-- 此 param 标签提示使用 Flash Player 6.0 r65 和更高版本的用户下载最新版本的 Flash Player。如果您不想让用户看到该提示，请将其删除。 -->
          <param name="expressinstall" value="Scripts/expressInstall.swf" />
          <!-- 下一个对象标签用于非 IE 浏览器。所以使用 IECC 将其从 IE 隐藏。 -->
          <!--[if !IE]>-->
          <object type="application/x-shockwave-flash" data="bg.swf" width="920" height="160">
            <!--<![endif]-->
            <param name="quality" value="high" />
            <param name="wmode" value="opaque" />
            <param name="swfversion" value="6.0.65.0" />
            <param name="expressinstall" value="Scripts/expressInstall.swf" />
            <!-- 浏览器将以下替代内容显示给使用 Flash Player 6.0 和更低版本的用户。 -->
            <div>
              <h4>此页面上的内容需要较新版本的 Adobe Flash Player。</h4>
              <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获取 Adobe Flash Player" width="112" height="33" /></a></p>
            </div>
            <!--[if !IE]>-->
          </object>
          <!--<![endif]-->
        </object></td>
        </tr>
      <tr>
        <td width="80">&nbsp;</td>
        <td width="580"><font color="#0000FF">      
        <marquee id="scrollArea1" scrollamount="5"  onmouseout="this.start()" onMouseOver="this.stop()"
              scrolldelay="130" direction="left" width="100%" >
                         欢迎来到安徽科技学院----在线考试系统
       </marquee>  
    </font></td>
        <td width="60">&nbsp;</td>
        <td width="200" align="center"><font color="#000000">
       <script language="JavaScript" type="text/javascript">    
	        d = new Date();
            document.write((d.getYear()+1900)+"年"+(d.getMonth()+1)+"月"+d.getDate()+ "日");			
       </script>
    </font><font color="#000000">
          <script language="JavaScript" type="text/javascript">
			d = new Date();
			switch (d.getDay()) {
			case 0:
			strweek="日";
			break;
			case 1:
			strweek="一";
			break;
			case 2:
			strweek="二";
			break;
			case 3:
			strweek="三";
			break;
			case 4:
			strweek="四";
			break;
			case 5:
			strweek="五";
			break;
			case 6:
			strweek="六";
			break;
			}
	document.write("星期"+ strweek);
	</script>
    </font></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="500" background="images/bg.jpg"><div align="center">
    <%
    	String flag=request.getParameter("flag");
    	if(flag!=null){
    		if(flag.equals("error")){
    			%>
    			<h2><font color="red">对不起，密码与用户名不合，请重新输入！</font></h2>
    			<hr/>
    			<%
    		}else if(flag.equals("login")){
    			%>
    			<h2><font color="red">对不起，你还没有登录，无法访问该页面！</font></h2>
    			<hr/>
    			<%
    		}
    	}
     %>
      <form action="LoginServlet?flag=login" method="post" name="login" target="_self" id="login">
        <table width="44%" border="1">
          <tr>
            
            <td height="33" bgcolor="#99FFFF"><div align="center" class="title"><strong>用户登录</strong></div></td>
          </tr>
          <tr>
            <td height="35"><div align="center">
              <label>
                用户名：
                  <input name="user" type="text" id="user" size="20" />
              </label>
            </div></td>
          </tr>
          <tr>
            <td height="30"><div align="center">密码：
              <label>
                <input name="psd" type="password" id="psd" size="22" />
              </label>
            </div></td>
          </tr>
          <tr>
            <td height="38"><div align="center">
              <label>
                <input name="role" type="radio" id="radio" value="admin" checked="checked" />
              </label>
              <label>
                管理员&nbsp;&nbsp;&nbsp;&nbsp;
                   <input type="radio" name="role" id="radio2" value="teacher" />
                  教师&nbsp;&nbsp;&nbsp;&nbsp;
              </label>
              <label>
                <input type="radio" name="role" id="radio3" value="student" />
                学生
              </label>
</div></td>
          </tr>
          <tr>
            <td height="40"><div align="center">
              <label>
                <input type="submit" name="submit" id="submit" value="提交" onClick="javascript:return check()"/>   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              </label>
              <label>
                <input type="reset" name="reset" id="reset" value="重置" />
              </label>
            </div></td>
          </tr>
      <tr><td align="center">管理员用户名:ding 密码:1234</td></tr>
      <tr><td align="center">学生:陈莉莉  密码:123456</td></tr>
      <tr><td align="center">教师:付求宝 密码:123456</td></tr>    
        </table>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>        
      </form>
    </div></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<script type="text/javascript">
<!--
swfobject.registerObject("FlashID");
//-->
</script>
</body>
</html>
  
  
