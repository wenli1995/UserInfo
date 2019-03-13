package com.java.util;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String Date2String(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}//����ת�����ַ���
	
	
	public static Date String2Date(String str,String format) throws Exception{
		if(StringUtil.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}//�ַ���ת��������
	
	
}
