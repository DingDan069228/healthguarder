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

import com.health.service.IBloodPressureService;
import com.health.service.IHeartRateService;
import com.health.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
@Component("parameters_action")
public class ParametersAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private IBloodPressureService bloodPressureService;
	private IHeartRateService heartRateService;
	private IUserService userService;
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	@Resource(name="bloodPressure_service")
	public void setBloodPressureService(IBloodPressureService bloodPressureService){
		this.bloodPressureService = bloodPressureService;
	}
	@Resource(name="heartRate_service")
	public void setHeartRateService(IHeartRateService heartRateService){
		this.heartRateService = heartRateService;
	}
	@Resource(name="user_service")
	public void setUserService(IUserService userService){
		this.userService = userService;
	}
	public void loadParameters()throws Exception{
		JSONObject reqObject;
		List<Integer> lst;
		JSONArray respArray = new JSONArray();
		String respMessage;
		try{
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/json;charset=utf-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String temp;
			while((temp=br.readLine())!= null){
				sb.append(temp);
			}
			System.out.println("请求报文："+sb.toString());
			JSONArray reqValue = JSONArray.fromObject(sb.toString());
			reqObject = reqValue.getJSONObject(0);
			String userName = reqObject.getString("userName");
			Integer index = Integer.valueOf(reqObject.getString("index"));
			Integer number = Integer.valueOf(reqObject.getString("number"));
			System.out.println(number);
			Integer userId = userService.getIdByUserName(userName);
			System.out.println(userId);
			if(index==0){
				lst = bloodPressureService.getRecentValues(userId, number);
			}
			else{
				lst = heartRateService.getRecentValues(userId, number);
			}
			JSONObject obj = new JSONObject();
			obj.put("values", lst);
			respArray.add(obj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			respMessage = respArray==null?null:respArray.toString();
			System.out.println("返回报文："+respMessage);
			PrintWriter pw = response.getWriter();
			pw.write(respMessage);
			pw.flush();
			pw.close();
		}
	}
}
