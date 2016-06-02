package com.health.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.annotation.Resource;
//返回json数据到客户端，跟传统的web应用一样，存在两种方式
//一：在action中输出json数据 二：在视图资源中输出json数据
//输出json数据又分为两种：一：使用传统方式输出自己包装后的json数据 二：使用struts自带的json数据封装功能来自动包装并返回json数据
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
//import org.json.JSONArray;
//import org.json.JSONObject;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Component;

import com.health.entity.User;
import com.health.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
@Component("login_action")
public class LoginAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	//private String json;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String userName;
	private String password;
	private IUserService userService;
	@Resource(name="user_service")
	public void setUserService(IUserService userService){
		this.userService=userService;
	}
	/*public String getUserName() {
		return userName;
	}*/
/*	public void setUserName(String userName) {
		this.userName = userName;
	}*/
/*	public String getPassword() {
		return password;
	}*/
/*	public void setPassword(String password) {
		this.password = password;
	}*/
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	
	public void login() throws Exception{
		String admin = null;
		String respMessage = null;
		String reqMessage = null;
		JSONArray reqObject = null;
		JSONArray respObject = null;
		try{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			reqMessage = sb.toString();
			System.out.println("请求报文:" + reqMessage);
			reqObject = JSONArray.fromObject(reqMessage);
			String userName = reqObject.getJSONObject(0).getString("userName");
			System.out.println("用户名："+userName);
			String password = reqObject.getJSONObject(0).getString("password");
			System.out.println("密码："+password);
			User user = userService.login(userName, password);
			if(user!=null){
				  admin = user.getUserName();
			}
			else
				admin=null;
			 respObject = new JSONArray();
			 JSONObject obj2 = new JSONObject();
			 obj2.put("admin", admin);
			 respObject.add(obj2);
			 //respObject.add(new JSONObject().put("userId", id));
			 System.out.println(admin);
			
			//PrintWriter pw = response.getWriter();
			//pw.write(id);
			//pw.flush();
			//pw.close();
			/*String reqValue = request.getParameter("json");
			JSONObject obj = JSONObject.fromObject(reqValue);
			userName = obj.getString("userName");
			password = obj.getString("password");
			User user = userService.login(userName, password);
			if(user!=null){
				 id = user.getId();
			}
			PrintWriter pw = response.getWriter();
			pw.write(id);
			pw.flush();
			pw.close();*/
		}
		catch(Exception e){
			throw e;
		}
		finally{
			respMessage = respObject == null ? "" : respObject.toString();
			System.out.println("返回报文:" + respMessage);
			PrintWriter pw = response.getWriter();
			pw.write(respMessage);
			pw.flush();
			pw.close();
		}
	}

}
