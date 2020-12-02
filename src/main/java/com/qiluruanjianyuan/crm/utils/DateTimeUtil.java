package com.qiluruanjianyuan.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期以及时间的插件
 * */

public class DateTimeUtil {
	
	public static String getSysTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		String dateStr = sdf.format(date);
		
		return dateStr;
		
	}
	
}
