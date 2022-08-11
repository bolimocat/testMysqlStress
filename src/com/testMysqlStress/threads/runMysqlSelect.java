package com.testMysqlStress.threads;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.testMysqlStress.dom.cmSelectBean;
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
//	private int tbnum;
	
	
	public runMysqlSelect(String h,String u,String p,String pt,String db,String f,String c) {
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
//		tbKind = k;
		paraFiled = f;
		paraCondition = c;
//		tbnum = n;
	}
	controlMySql ctMysql = new controlMySql(host,user,pass,port,database);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		ArrayList ctSelectlist = new ArrayList();
		ctSelectlist = ctMysql.selecttb(host, user, pass, port, database, paraFiled,paraCondition);
//		for(int i=0;i<ctSelectlist.size();i++) {
//			cmSelectBean csb = (cmSelectBean)ctSelectlist.get(i);
//			System.out.println("result : "+csb.getField1()+"  ,  "+csb.getField2());
//		}
	}

}
