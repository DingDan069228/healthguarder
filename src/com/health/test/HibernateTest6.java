package com.health.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.health.dao.impl.BloodPressureDaoImpl;

public class HibernateTest6 {
	public static void main(String[] args){
		//BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		//BloodPressureDaoImpl bdao = (BloodPressureDaoImpl) bf.getBean("bloodPressure_dao");
		//List lst = bdao.findByProperty(1, 10);
		//list.toArray()要使用带参数的toArray方法
		//Integer[] a = (Integer[]) lst.toArray(new Integer[0]);
		//for(int i=0;i<a.length;i++){
			//System.out.println(a[i]);
		//}
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		array.add(new JSONObject().put("kry", "values"));
		obj.put("values", "key");
		array.add(obj);
		System.out.println(array);
		JSONObject obj2 = array.getJSONObject(0);
		List<Integer> lst2 = (List<Integer>) obj2.get("values");
		System.out.println(lst2);
		for(int i=0;i<lst2.size();i++){
			System.out.println(lst2.get(i));
		}
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		System.out.println(list);
		
	}
}
