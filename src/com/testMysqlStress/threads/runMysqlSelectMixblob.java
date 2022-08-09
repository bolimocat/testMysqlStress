package com.testMysqlStress.threads;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Spliterator;

import com.mysql.cj.jdbc.Blob;
import com.testMysqlStress.dom.mixBean;
import com.testMysqlStress.function.commonkit;
import com.testMysqlStress.function.controlMySql;
import com.testMysqlStress.function.controlMySqlBlob;

public class runMysqlSelectMixblob implements Runnable {

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
	
	
	public runMysqlSelectMixblob(String h,String u,String p,String pt,String db,String f,String c,String file) {
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
		ArrayList mixbloblist = new ArrayList();
		
//		commonkit kit = new commonkit();
//		Date date = new Date();
//		date.setTime(kit.Time());
		mixbloblist = ctMysqlblob.selectmixblobtb(host, user, pass, port, database, paraFiled,paraCondition,outfilepath);
		String[] file_array = outfilepath.split("_");
		for (int i=0;i<mixbloblist.size();i++) {
			System.out.println("mixbloblist : "+mixbloblist.size());
			mixBean mixb = (mixBean)mixbloblist.get(i);
			Blob MixblobTinyfiled = 	mixb.getTinyfiled();
			Blob MixblobMediumfiled  = mixb.getMediumfiled();
			Blob MixblobBlobfiled = mixb.getBlobfiled();
			Blob MixblobLongfiled = mixb.getLongfiled();
			//开始读入文件
			try {
				 InputStream in_mix_tiny = MixblobTinyfiled.getBinaryStream();
				 OutputStream out_mix_tiny = new FileOutputStream("./outfile/"+file_array[0]+"_tinyblob");
				 InputStream in_mix_medium = MixblobMediumfiled.getBinaryStream();
				 OutputStream out_mix_medium = new FileOutputStream("./outfile/"+file_array[1]+"_mediumblob");
				 InputStream in_mix_blob = MixblobBlobfiled.getBinaryStream();
				 OutputStream out_mix_blob = new FileOutputStream("./outfile/"+file_array[2]+"_blob");
				 InputStream in_mix_long = MixblobLongfiled.getBinaryStream();
				 OutputStream out_mix_long = new FileOutputStream("./outfile/"+file_array[3]+"_longblob");
				 byte[] buffer = new byte[1024];
		         int len_mix_tiny = 0;
		         int len_mix_medium = 0;
		         int len_mix_blob = 0;
		         int len_mix_long = 0;
		         while((len_mix_tiny = in_mix_tiny.read(buffer)) != -1){
		        	 out_mix_tiny.write(buffer, 0, len_mix_tiny);
		            }
		         while((len_mix_medium = in_mix_medium.read(buffer)) != -1){
		        	 out_mix_medium.write(buffer, 0, len_mix_medium);
		            }
		         while((len_mix_blob = in_mix_blob.read(buffer)) != -1){
		        	 out_mix_blob.write(buffer, 0, len_mix_blob);
		            }
		         while((len_mix_long = in_mix_long.read(buffer)) != -1){
		        	 out_mix_long.write(buffer, 0, len_mix_long);
		            }
		       
			} catch (Exception eSelectOutmixblob) {
				// TODO: handle exception
				eSelectOutmixblob.printStackTrace();
			}
		}
		
	}

}
