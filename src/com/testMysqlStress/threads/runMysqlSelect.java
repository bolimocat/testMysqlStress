package com.testMysqlStress.threads;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.testMysqlStress.function.commonkit;
import com.testMysqlStress.function.controlMySql;

public class runMysqlSelect implements Runnable {

	private String host;
	private String user;
	private String pass;
	private String port;
	private String database;
//	private String tbKind;
	private String paraFiled;
	private String paraCondition;
	private int tbnum;
	
	
	public runMysqlSelect(String h,String u,String p,String pt,String db,String f,String c,int n) {
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
//		tbKind = k;
		paraFiled = f;
		paraCondition = c;
		tbnum = n;
	}
	controlMySql ctMysql = new controlMySql(host,user,pass,port,database);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		commonkit kit = new commonkit();
		Date date = new Date();
		date.setTime(kit.Time());
		Long time = ctMysql.selecttb(host, user, pass, port, database, paraFiled,paraCondition,tbnum);
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",查询耗时：,"+time);
	}

}
