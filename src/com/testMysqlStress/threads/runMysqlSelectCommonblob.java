package com.testMysqlStress.threads;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.cj.jdbc.Blob;
import com.testMysqlStress.dom.blobBean;
import com.testMysqlStress.function.commonkit;
import com.testMysqlStress.function.controlMySql;
import com.testMysqlStress.function.controlMySqlBlob;

public class runMysqlSelectCommonblob implements Runnable {

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
	
	
	public runMysqlSelectCommonblob(String h,String u,String p,String pt,String db,String f,String c,String file) {
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
		ArrayList commonbloblist = new ArrayList();
		commonbloblist = ctMysqlblob.selectblobtb(host, user, pass, port, database, paraFiled,paraCondition,outfilepath);
		for(int i=0;i<commonbloblist.size();i++) {
			blobBean cb = (blobBean)commonbloblist.get(i);
			Blob commonblobfiled = cb.getBlobblob();
			try {
				InputStream in_commonblob = commonblobfiled.getBinaryStream();
				 OutputStream out_commonblob = new FileOutputStream("./outfile/"+outfilepath+"_blob");
				 byte[] buffer = new byte[1024];
				 int len_commonblob = 0;
		        while((len_commonblob = in_commonblob.read(buffer)) != -1){
		        	out_commonblob.write(buffer, 0, len_commonblob);
		            }
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
