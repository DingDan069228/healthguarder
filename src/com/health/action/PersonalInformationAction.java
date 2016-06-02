package com.health.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.health.dao.IUserDao;
import com.health.entity.User;
import com.health.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
@Component("personalInformation_action")
public class PersonalInformationAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private ServletRequest request;
	private ServletResponse response;
	private IUserService userService;
	private IUserDao userDao;
	@Resource(name="user_service")
	public void setUserService(IUserService userService){
		this.userService=userService;
	}
	public IUserService getUserService(){
		return userService;
	}
	@Resource(name="user_dao")
	public void setUserDao(IUserDao userDao){
		this.userDao=userDao;
	}
	public IUserDao getUserDao(){
		return userDao;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
	}
	public void loadInformation()throws Exception{
		JSONArray resp = null;
		String respMessage = null;
		try{
			response.setContentType("text/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp = null;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			String req = sb.toString();
			System.out.println("请求报文："+req);
			JSONArray array = JSONArray.fromObject(req);
			JSONObject obj = (JSONObject) array.get(0);
			User user = userService.loadOneUser(obj.getString("user"));
			resp = new JSONArray();
			//直接从对象中获取JSONObject对象，空值null将变成空字符串，而采用put方法为JSONObject一个一个赋值的话，将null值将不包含在JSONOnject对象中。
			JSONObject obj2 = JSONObject.fromObject(user);
			resp.add(obj2);
			//System.out.println("返回报文："+resp);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			respMessage = resp==null?null:resp.toString();
			System.out.println("返回报文:"+respMessage);
			PrintWriter writer = response.getWriter();
			writer.print(resp);
			writer.flush();
			writer.close();
		}
	}
	public void uploadInformation()throws Exception{
		String reqMessage = null;
		JSONObject obj = null;
		JSONArray respArray = new JSONArray();
		String respMessage = null;
		try{
			response.setContentType("text/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
			StringBuffer sb = new StringBuffer("");
			String temp = null;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			reqMessage = sb.toString();
			System.out.println("您请求的报文为："+reqMessage);
			JSONArray array = JSONArray.fromObject(reqMessage);
			obj = array.getJSONObject(0);
			String userName = obj.getString("userName");
			String phoneNumber = obj.getString("phoneNumber");
			String realPhoneNumber = phoneNumber.equals("-1")?null:phoneNumber;
			String email = obj.getString("email");
			String realEmail = email.equals("-1")?null:email;
			String realName = obj.getString("realName");
			String realRealName = realName.equals("-1")?null:realName;
			String gender = obj.getString("gender");
			String birthday = obj.getString("birthday");
			String realBirthday = birthday.equals("-1")?null:birthday;
			String originalUserName = obj.getString("originalUserName");
			String height = obj.getString("height");
			Integer realHeight = height.equals("-1")?0:Integer.parseInt(height.substring(0,height.length()-2));
			String weight = obj.getString("weight");
			Integer realWeight = weight.equals("-1")?0:Integer.parseInt(weight.substring(0,weight.length()-2));
			User user = new User(userName,null, realRealName, gender, realBirthday, realPhoneNumber, realEmail, realHeight, realWeight,null);
			System.out.println(user);
			User user2 = userDao.updateByUserName(user, originalUserName);
			JSONObject obj2 = JSONObject.fromObject(user2);
			respArray.add(obj2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			respMessage=respArray==null?null:respArray.toString();
			System.out.println("返回报文："+respMessage);
			PrintWriter writer = response.getWriter();
			writer.write(respMessage);
			writer.flush();
			writer.close();
		}
	}

}
