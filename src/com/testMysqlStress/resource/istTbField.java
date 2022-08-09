package com.testMysqlStress.resource;

public class istTbField {

	//普通表插入字段
	public static String ISTCOMMONTBFILED ="(char_type2,char_type,bigid,smallid,tinyid,mediumid,double_type,float_type,date_type ,decimal_type ,blob_type,varchar_type,int8_type,boolean_type,datetime_mh,time_type,year_type,timestamp_type,tinyblob_type,tinytext_type,  text_type,mediumtext_type,longblob_type,longtext_type) ";
	
	//普通表插入数值
	public static String ISTCOMMONTBVALUE ="('abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij',"
			+ "'abcdefghijabcdefghij',9223372036854775807,32767,127,18446744073709551615,100.00,100.12,'1921-07-23',123.45,'abcdefghijabcdefghij','abcdefghijabcdefghijabcdefghijab',"
			+ "88888888,true,'1921-07-01','23:59:59','2008',null,'abcdefghijabcdefghij','tinytext','text','mediumtext','abcdefghijabcdefghij','longtext');";

}

