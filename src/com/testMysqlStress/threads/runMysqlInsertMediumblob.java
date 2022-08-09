package com.testMysqlStress.threads;

import com.testMysqlStress.function.controlMySql;
import com.testMysqlStress.function.controlMySqlBlob;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.testMysqlStress.function.commonkit;
//import com.testMysqlStress.statistic.statisticInfo;

public class runMysqlInsertMediumblob implements Runnable{

	private String host;
	private String user;
	private String pass;
	private String port;
	private String database;
	private int tbnum;
	private String insertValue;
	private Integer insertNum;
	private String mediumfile;
//	private Long interval;
	
	

	
	public runMysqlInsertMediumblob(String h,String u,String p,String pt,String db,int n,String insertV,Integer insertN,String file) {
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
		tbnum = n;
		insertValue = insertV;
		insertNum = insertN;
		mediumfile = file;
//		interval = reportTime;
		
	}
//	controlMySql ctMysql = new controlMySql(host,user,pass,port,database);
	controlMySqlBlob ctMysqlBlob = new controlMySqlBlob(host,user,pass,port,database);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		commonkit kit = new commonkit();
	   Date date = new Date();
	   date.setTime(kit.Time());
		Long time = ctMysqlBlob.insertmediumtb(host, user, pass, port, database, tbnum, insertValue,mediumfile);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",写入耗时：,"+time);
	}

}
