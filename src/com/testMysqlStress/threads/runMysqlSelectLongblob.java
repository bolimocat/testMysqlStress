package com.testMysqlStress.threads;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.jdbc.Blob;
import com.testMysqlStress.dom.longBean;
import com.testMysqlStress.dom.mixBean;
import com.testMysqlStress.function.commonkit;
import com.testMysqlStress.function.controlMySql;
import com.testMysqlStress.function.controlMySqlBlob;

public class runMysqlSelectLongblob implements Runnable {

	private String host;
	private String user;
	private String pass;
	private String port;
	private String database;
//	private String tbKind;
	private String paraFiled;
	private String paraCondition;
//	private int tbnum;
	private String outfilepath;
	
	
	public runMysqlSelectLongblob(String h,String u,String p,String pt,String db,String f,String c,String file) {
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
//		tbKind = k;
		paraFiled = f;
		paraCondition = c;
//		tbnum = n;
		outfilepath = file;
		
	}
	controlMySqlBlob ctMysqlblob = new controlMySqlBlob(host,user,pass,port,database);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList longbloblist = new ArrayList();
		longbloblist = ctMysqlblob.selectlongblobtb(host, user, pass, port, database, paraFiled,paraCondition,outfilepath);
		for (int i=0;i<longbloblist.size();i++) {
			longBean lb = (longBean)longbloblist.get(i);
			Blob longfiled = lb.getLongblob();
			//开始读入文件
			try {
				 InputStream in_longblob = longfiled.getBinaryStream();
				 OutputStream out_longblob = new FileOutputStream("./outfile/"+outfilepath+"_longblob");
				 byte[] buffer = new byte[1024];
				 int len_longblob = 0;
		        while((len_longblob = in_longblob.read(buffer)) != -1){
		        	out_longblob.write(buffer, 0, len_longblob);
		            }
		       
		            
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
