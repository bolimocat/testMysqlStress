package com.testMysqlStress.threads;

import com.testMysqlStress.function.controlMySql;

public class runMysqlDelete  implements Runnable{

	
	private String host;
	private String user;
	private String pass;
	private String port;
	private String database;
	
	private String tbKind;
	public runMysqlDelete(String h,String u,String p,String pt,String db) {
		
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
		
	}
	controlMySql ctMysql = new controlMySql(host,user,pass,port,database);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ctMysql.deletetb(host,user,pass,port,database);
	}

}
