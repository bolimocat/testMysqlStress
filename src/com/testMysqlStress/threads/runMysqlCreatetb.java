package com.testMysqlStress.threads;

import com.testMysqlStress.function.controlMySql;

public class runMysqlCreatetb implements Runnable {

	private String host;
	private String user;
	private String pass;
	private String port;
	private String database;
//	private String tbKind;
	private int tbnum;
	private String tbField;
	private int tbFieldNum;
	public runMysqlCreatetb(String h,String u,String p,String pt,String db,int n,String tbf,int tbn) {
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
//		tbKind = k;
		tbnum = n;
		tbField = tbf;
		tbFieldNum = tbn;
		
	}
	
	controlMySql ctMysql = new controlMySql(host,user,pass,port,database);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ctMysql.createtb(tbnum,tbField,tbFieldNum);
		
	}

}
