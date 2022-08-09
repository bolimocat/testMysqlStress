package com.testMysqlStress.threads;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.jdbc.Blob;
import com.testMysqlStress.dom.tinyBean;
import com.testMysqlStress.function.commonkit;
import com.testMysqlStress.function.controlMySql;
import com.testMysqlStress.function.controlMySqlBlob;

public class runMysqlSelectTinyblob implements Runnable {

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
	
	
	public runMysqlSelectTinyblob(String h,String u,String p,String pt,String db,String f,String c,String file) {
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
		ArrayList tinybloblist = new ArrayList();
		tinybloblist = ctMysqlblob.selecttinytb(host, user, pass, port, database, paraFiled,paraCondition,outfilepath);
		for(int i=0;i<tinybloblist.size();i++) {
			tinyBean tb = (tinyBean)tinybloblist.get(i);
			Blob tinyblobfiled = tb.getTinyblob();
			try {
				InputStream in_tinyblob = tinyblobfiled.getBinaryStream();
				OutputStream out_tinyblob = new FileOutputStream("./outfile/"+outfilepath+"_tinyblob");
				 byte[] buffer = new byte[1024];
				 int len_tinyblob = 0;
		        while((len_tinyblob = in_tinyblob.read(buffer)) != -1){
		        	out_tinyblob.write(buffer, 0, len_tinyblob);
		            }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
