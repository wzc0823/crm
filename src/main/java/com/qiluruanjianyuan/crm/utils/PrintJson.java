package com.qiluruanjianyuan.crm.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 用来转换json串
 *目的：把数组，对象，map集合，对象集合有效解析为json串
 * */

public class PrintJson {
	
	//将boolean值解析为json串
	public static void printJsonFlag(HttpServletResponse response,boolean flag){
		
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("success",flag);//把传过来的true或者false转换为键值对的方式
		
		ObjectMapper om = new ObjectMapper();
		try {
			//{"success":true}
			String json = om.writeValueAsString(map);
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	//将对象解析为json串
	public static void printJsonObj(HttpServletResponse response,Object obj){
		
		/*
		 * 
		 * Person p
		 * 	id name age
		 * {"id":"?","name":"?","age":?}
		 * 
		 * List<Person> pList
		 * [{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?},{"id":"?","name":"?","age":?}...]
		 * 
		 * Map
		 * 	key value
		 * {key:value}
		 * 
		 * 
		 */
		
		ObjectMapper om = new ObjectMapper();
		try {
			String json = om.writeValueAsString(obj);
			response.getWriter().print(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}























