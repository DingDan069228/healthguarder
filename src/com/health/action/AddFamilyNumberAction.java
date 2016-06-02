package com.health.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Component;

import com.health.constants.Constant;
import com.health.entity.Concern;
import com.health.service.IConcernService;
import com.health.service.IUserService;
import com.health.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionSupport;
@Component("addFamilyNumber_action")
public class AddFamilyNumberAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private IUserService userService;
	private IConcernService concernService;
	@Resource(name="concern_service")
	public void setConcernService(IConcernService concernService){
		this.concernService = concernService;
	}
	@Resource(name="user_service")
	public void setUserService(IUserService userService){
		this.userService = userService;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void addFamilyNumber() throws IOException{
		String reqMessage;
		JSONArray array;
		String res = "0";
		JSONArray respArray = null;
		String respMessage;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
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
			array = JSONArray.fromObject(reqMessage);
			JSONObject obj = (JSONObject) array.get(0);
			String userName = obj.getString("userName");
			System.out.println(userName);
			String familyName = obj.getString("familyName");
			System.out.println(familyName);
			Integer userId = userService.getIdByUserName(userName);
			Integer familyId = userService.getIdByUserName(familyName);
			if(familyId == null){
				//返回-1表示查找的用户名不存在
				res = String.valueOf("-1");
			}else{
				Concern concern = new Concern(userId, familyId, 1, 0, Constant.ON_VERIFYING,
						sdf.format(new Date()));
				Integer i = concernService.createPair(concern);
				System.out.println(i);
				if(i==null){
					//返回-2表示添加失败
					res = String.valueOf("-2");
				}
			}
			respArray = new JSONArray();
			JSONObject obj2 = new JSONObject();
			obj2.put("admin", res);
			System.out.println(res);
			respArray.add(obj2);
		}	
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			respMessage = respArray == null ? "" : respArray.toString();
			System.out.println("返回报文:" + respMessage);
			PrintWriter pw = response.getWriter();
			pw.write(respMessage);
			pw.flush();
			pw.close();
		}
	}
}
