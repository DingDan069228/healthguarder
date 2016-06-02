package com.health.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Component;

import com.health.dao.IUserDao;
import com.health.entity.User;
import com.health.service.IUserService;
import com.health.util.MD5Code;
import com.opensymphony.xwork2.ActionSupport;
@Component("register_action")
public class RegisterAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {
	private HttpServletRequest request;
	private HttpServletResponse response; 
	private IUserService userService;
	private IUserDao userDao;
	@Resource(name="user_service")
	public void setUserService(IUserService userService){
		this.userService=userService;
	}
	@Resource(name="user_dao")
	public void setUserDao(IUserDao userDao){
		this.userDao=userDao;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public void register()throws Exception{
		MD5Code md5 = new MD5Code();
		String reqMessage = null;
		JSONArray reqObject = null;
		String newUserName = null;
		JSONArray respObject = null;
		String respMessage = null;
		try{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) 
				sb.append(temp);
			br.close();
			reqMessage = sb.toString();
			System.out.println("请求报文:" + reqMessage);
			reqObject = JSONArray.fromObject(reqMessage);
			JSONObject obj = reqObject.getJSONObject(0);
			String userName = obj.getString("userName");
			String password = md5.getMD5ofStr(obj.getString("password"));
			String realName = obj.getString("realName");
			String gender = obj.getString("gender");
			String phoneNumber = obj.getString("phoneNumber");
			String date = obj.getString("date");
			List<User> list = userDao.findByUserName(userName);
			System.out.println(list.size());
			if(list.size()!=0){
				newUserName = "1";
			}
			else{
				User user = userService.createUser(userName, password, realName, phoneNumber, gender, date);
				if(user!=null)
					newUserName = user.getUserName();
				else
					newUserName=null;
			}
			respObject = new JSONArray();
			JSONObject obj2 = new JSONObject();
			obj2.put("admin", newUserName);
			respObject.add(obj2);
			System.out.println(newUserName);
		}
		catch(Exception e){
			e.printStackTrace();
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
