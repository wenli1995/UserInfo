package com.java.util;

public class StringUtil {
	public static boolean isEmpty(String str){
		if(str==null||str.equals("")){
			return true;
		}else{
			return false;
		}
		
	}
	public static boolean isNotEmpty(String str){
		if(str!=null&&!str.equals("")){
			return true;
		}else{
			return false;
		}
		
	}
}
