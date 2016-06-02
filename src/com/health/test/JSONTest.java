package com.health.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.NameValuePair;

import com.health.entity.User;

public class JSONTest {
	public static void main(String... args){
		/*List<NameValuePair> lst = new ArrayList<NameValuePair>();
		JSONObject obj = new JSONObject();
		obj.put("username", "Jack");
		obj.put("password", "123456");
		System.out.println(obj.toString());
		lst.add(new NameValuePair("json",obj.toString()));
		NameValuePair a = lst.get(0);
		String s = a.getValue();
		JSONObject obj2 = JSONObject.fromObject(s);
		System.out.println(obj2.toString());*/
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("username", "admin");
		obj.put("password", "123456");
		array.add(obj);
		//array.add(new JSONObject().put("userId",5));
		//在JSONArray的add方法中不能匿名创建JSONObject对象，不会报错，但值为null
		JSONObject obj2 = new JSONObject();
		obj2.put("userId", 5);
		array.add(obj2);
		System.out.println(array.toString());
		User user = new User("ding", "123456", "ding", null, null, null);
		JSONObject obj3 = JSONObject.fromObject(user);
		System.out.println(obj3);
		
	}
}
