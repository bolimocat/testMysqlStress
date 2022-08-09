package com.testMysqlStress.threads;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.jdbc.Blob;
import com.testMysqlStress.dom.mediumBean;
import com.testMysqlStress.function.commonkit;
import com.testMysqlStress.function.controlMySql;
import com.testMysqlStress.function.controlMySqlBlob;

public class runMysqlSelectMediumblob implements Runnable {

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
	
	
	public runMysqlSelectMediumblob(String h,String u,String p,String pt,String db,String f,String c,String file) {
		host = h;
		user = u;
		pass = p;
		port = pt;
		database = db;
//		tbKind = k;
		paraFiled = f;
		paraCondition = c;
		outfilepath = file;
//		tbnum = n;
	}
	controlMySqlBlob ctMysqlblob = new controlMySqlBlob(host,user,pass,port,database);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		ArrayList mediumbloblist = new ArrayList();
		mediumbloblist = ctMysqlblob.selectmediumtb(host, user, pass, port, database, paraFiled,paraCondition,outfilepath);
		for(int i=0;i<mediumbloblist.size();i++) {
			mediumBean mb = (mediumBean)mediumbloblist.get(i);
			Blob mediumfiled = mb.getMediumblob();
			try {
				InputStream in_mediumblob = mediumfiled.getBinaryStream();
				 OutputStream out_mediumblob = new FileOutputStream("./outfile/"+outfilepath+"_mediumblob");
				 byte[] buffer = new byte[1024];
				 int len_mediumblob = 0;
		        while((len_mediumblob = in_mediumblob.read(buffer)) != -1){
		        	out_mediumblob.write(buffer, 0, len_mediumblob);
		            }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
