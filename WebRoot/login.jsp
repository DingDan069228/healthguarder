<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();//获得项目名称
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";//http+主机名+端口号
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><!--
    <base href="<%=basePath%>">
     --><!--标签为页面上的所有链接规定默认地址或默认目录,这是所有#都跳转到该页面的原因吗？-->
    <title>设备监控登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<!--定义浏览器不对页面进行缓存-->
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<!--设定网页的过期时间，一旦过期就必须从服务器上重新加载，时间必须使用GMT格式-->    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function check(){
			if(document.getElementById("userName").value==""){
				alert("请输入用户名");
				document.getElementById("userName").focus();
				return false;
			}
			if(document.getElementById("password").value==""){
				alert("请输入密码")；
				document.getElementById("password").focus();
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body>
    <form name="loginForm" action="loginAction!login.action" method="post">
    <center>
     <table border="5" align="center">
      <tr>
       <td>用户名：</td>
       <td><input type="text" name="userName" id="userName"></td>
      </tr>	
      <tr>
       <td>密码:</td>
       <td><input type="password" name="password" id="password"></td>
      </tr>
      <tr>
       <td></td>
       <td><input type="Submit" name="submit" value="确定" onclick="return check();">
       &nbsp;&nbsp;&nbsp;
       <input type="reset" name="reset" value="重置">
       </td>
      </tr>
     </table>
    </center>
    </form>
  </body>
</html>
