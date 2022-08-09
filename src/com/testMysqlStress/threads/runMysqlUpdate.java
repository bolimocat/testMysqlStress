package com.testMysqlStress.threads;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.testMysqlStress.function.commonkit;
import com.testMysqlStress.function.controlMySql;

public class runMysqlUpdate implements Runnable{

	
	private String host;
	private String user;
	private String pass;
	private String port;
	private String database;
	private int tbrange;
//	private String tbKind;
	
	public runMysqlUpdate(String h,String u,String p,String pt,String db,int range) {
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
		tbrange = range;
//		tbKind = k;
	}
	controlMySql ctMysql = new controlMySql(host,user,pass,port,database);
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		commonkit kit = new commonkit();
		Date date = new Date();
		date.setTime(kit.Time());
		Long time = ctMysql.updatetb(host,user,pass,port,database,tbrange);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",更新耗时：,"+time);
		
	}

}
