package com.testMysqlStress.function;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import com.testMysqlStress.dom.paraFileBean;
import com.testMysqlStress.statistic.statisticInfo;
import com.testMysqlStress.threads.runMysqlCreatetb;
import com.testMysqlStress.threads.runMysqlDelete;
import com.testMysqlStress.threads.runMysqlInsert;
import com.testMysqlStress.threads.runMysqlInsertCommonblob;
import com.testMysqlStress.threads.runMysqlInsertLongblob;
import com.testMysqlStress.threads.runMysqlInsertMediumblob;
import com.testMysqlStress.threads.runMysqlInsertMixblob;
import com.testMysqlStress.threads.runMysqlInsertTinyblob;
import com.testMysqlStress.threads.runMysqlSelect;
import com.testMysqlStress.threads.runMysqlSelectCommonblob;
import com.testMysqlStress.threads.runMysqlSelectLongblob;
import com.testMysqlStress.threads.runMysqlSelectMediumblob;
import com.testMysqlStress.threads.runMysqlSelectMixblob;
import com.testMysqlStress.threads.runMysqlSelectTinyblob;
import com.testMysqlStress.threads.runMysqlUpdate;
import com.testMysqlStress.threads.runStatistic;

public class main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
//		Date dt= new Date();
		
		commonkit ck = new commonkit();
//		ArrayList paraList = ck.fetchLine("./file/dblink");//按行读取配置文件
		ArrayList paraList = ck.fetchLine(args[0]);//按行读取配置文件
		String[] str = null; 
		paraFileBean pfb0 = (paraFileBean)paraList.get(0);
		str = pfb0.getFileLine().split(":");
		String host = str[1];
		paraFileBean pfb1 = (paraFileBean)paraList.get(1);
		str = pfb1.getFileLine().split(":");
		String user = str[1];
		paraFileBean pfb2 = (paraFileBean)paraList.get(2);
		str = pfb2.getFileLine().split(":");
		String pass = str[1];
		paraFileBean pfb3 = (paraFileBean)paraList.get(3);
		str = pfb3.getFileLine().split(":");
		String port = str[1];
		paraFileBean pfb4 = (paraFileBean)paraList.get(4);
		str = pfb4.getFileLine().split(":");
		String db = str[1];
		paraFileBean pfb5 = (paraFileBean)paraList.get(5);
		str = pfb5.getFileLine().split(":");
		String tbtype = str[1];
		String tbField = str[2];
		String tbFieldNum = str[3];
		paraFileBean pfb6 = (paraFileBean)paraList.get(6);
		str = pfb6.getFileLine().split(":");
		String selectThreads = str[1];
		String paraFiled = str[2];
		String paraCondition = str[3];
		String outBlobfilepath = str[4]; 
		paraFileBean pfb7 = (paraFileBean)paraList.get(7);
		str = pfb7.getFileLine().split(":");
		String insertThreads = str[1];
		String insertValue = str[2];
		String insertFieldnum = str[3];
		String blobfilepath = str[4];
		paraFileBean pfb8 = (paraFileBean)paraList.get(8);
		str = pfb8.getFileLine().split(":");
		String updateThreads = str[1];
		paraFileBean pfb9 = (paraFileBean)paraList.get(9);
		str = pfb9.getFileLine().split(":");
		String deleteThreads = str[1];
		paraFileBean pfb10 = (paraFileBean)paraList.get(10);
		str = pfb10.getFileLine().split(":");
		String exeType = str[1];
		paraFileBean pfb11 = (paraFileBean)paraList.get(11);
		str = pfb11.getFileLine().split(":");
		String lasting = str[1];
		paraFileBean pfb12 = (paraFileBean)paraList.get(12);
		str = pfb12.getFileLine().split(":");
		String createtb = str[1];
		paraFileBean pfb13 = (paraFileBean)paraList.get(13);
		str = pfb13.getFileLine().split(":");
		int tbnum = Integer.valueOf(str[1]);
		paraFileBean pfb14 = (paraFileBean)paraList.get(14);
		str = pfb14.getFileLine().split(":");
		Long interval = Long.valueOf(str[1]);
		//		System.out.println(" host,user,pass,port,db,tbType,select,insert,update,delete: "+host+","+user+","+pass+","+port+","+db+","+tbType+","+selectThreads+";");
		
		System.out.println("Mysql Stress Testing tool .");
		controlMySql ctMysql = new controlMySql(host,user,pass,port,db);
		controlMySqlBlob ctMysqlblob = new controlMySqlBlob(host,user,pass,port,db);
			Random rand = new Random();
//		statisticInfo ssi = new statisticInfo();
		Long start = 0l;
		Long exeTime = 0l;
			
			//建表
			if(createtb.equals("1")) {
				if(tbtype.equals("common")) {
					System.out.println("创建普通表:");
					System.out.println("执行建表 num : "+tbnum);
					ctMysql.createtb(tbnum,tbField,Integer.valueOf(tbFieldNum));
				}
				
				if(tbtype.equals("btiny")) {
					System.out.println("创建tiny_blob表:");
					System.out.println("执行建表 num : "+tbnum);
					ctMysqlblob.createtinyblobtb(tbnum, tbField, Integer.valueOf(tbFieldNum));
				}
				
				if(tbtype.equals("bmedium")) {
					System.out.println("创建medium_blob表:");
					System.out.println("执行建表 num : "+tbnum);
					ctMysqlblob.createmediumblobtb(tbnum, tbField, Integer.valueOf(tbFieldNum));
				}
				
				if(tbtype.equals("bblob")) {
					System.out.println("创建blob表:");
					System.out.println("执行建表 num : "+tbnum);
					ctMysqlblob.createblobtb(tbnum, tbField, Integer.valueOf(tbFieldNum));
				}
				
				if(tbtype.equals("blong")) {
					System.out.println("创建long blob表:");
					System.out.println("执行建表 num : "+tbnum);
					ctMysqlblob.createlongblobtb(tbnum, tbField, Integer.valueOf(tbFieldNum));
				}
				if(tbtype.equals("bmix")) {
					System.out.println("创建mix blob表:");
					System.out.println("执行建表 num : "+tbnum);
					ctMysqlblob.createmixblobtb(tbnum, tbField, Integer.valueOf(tbFieldNum));
				}
			}
			
			if(exeType.equals("count")) {
				if(tbtype.equals("common")) {
					int loop = Integer.valueOf(lasting);//记次
					int insertthreadnum = Integer.valueOf(insertThreads);//普通表写入并发
					int updatethreadnum = Integer.valueOf(updateThreads);
					int deletethreadnum = Integer.valueOf(deleteThreads);
					int selectthreadnum = Integer.valueOf(selectThreads);
				
					for(int i=0;i<loop;i++) {
						for(int a = 0;a<insertthreadnum;a++) {
							Thread ta = new Thread(new runMysqlInsert(host,user,pass,port,db,tbnum,insertValue,Integer.valueOf(insertFieldnum),interval));
							ta.start();
							try {
								ta.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int b = 0;b<updatethreadnum;b++) {
							Thread tb = new Thread(new runMysqlUpdate(host,user,pass,port,db,tbnum));
							tb.start();
							try {
								tb.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						for(int c = 0;c<deletethreadnum;c++) {
							Thread tc = new Thread(new runMysqlDelete(host,user,pass,port,db));
							tc.start();
							try {
								tc.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int d = 0;d<selectthreadnum;d++) {
							Thread td = new Thread(new runMysqlSelect(host,user,pass,port,db,paraFiled,paraCondition));
							td.start();
							try {
								td.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				}
			}
			

			if(exeType.equals("time")) {
				if(tbtype.equals("common")) {
					int time = Integer.valueOf(lasting);//计时
					Long currenttime = 0l;
					int n = 1;
					Long starttime = ck.Time();
					Long max = ck.Time()+time * 1000;
					int insertthreadnum = Integer.valueOf(insertThreads);//普通表写入并发
					int updatethreadnum = Integer.valueOf(updateThreads);
					int deletethreadnum = Integer.valueOf(deleteThreads);
					int selectthreadnum = Integer.valueOf(selectThreads);
					start = ck.Time();
					SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					while(ck.Time() < max) {
						for(int a = 0;a<insertthreadnum;a++) {
							Thread ta = new Thread(new runMysqlInsert(host,user,pass,port,db,tbnum,insertValue,Integer.valueOf(insertFieldnum),interval));
							ta.start();
							try {
								currenttime = starttime + n * interval * 1000l;
								if(ck.Time() >= currenttime) {
									System.out.println("[ "+sdFormatter.format(ck.Time())+" ] insert tds:"+insertthreadnum+" tps : "+ctMysql.itnums / (ctMysql.itall / 1000) +" , 平均耗时 : "+ ctMysql.itall / ctMysql.itnums+"毫秒，失败插入事务数： "+(ctMysql.itfnums-1)+" 。");
									n = n+1;
								}
								ta.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int b = 0;b<updatethreadnum;b++) {
							Thread tb = new Thread(new runMysqlUpdate(host,user,pass,port,db,tbnum));
							tb.start();
							try {
								currenttime = starttime + n * interval * 1000l;
								if(ck.Time() >= currenttime) {
//									System.out.println("[ "+sdFormatter.format(ck.Time())+" ] update tds:"+updatethreadnum+" 平均每秒更新事务 : "+ctMysql.utnums / (ctMysql.utall / 1000) +" , 平均耗时 : "+ ctMysql.utall / ctMysql.utnums+"毫秒，失败更新事务数： "+(ctMysql.utfnums-1)+" 。");
									n = n+1;
								}
								tb.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						for(int c = 0;c<deletethreadnum;c++) {
							Thread tc = new Thread(new runMysqlDelete(host,user,pass,port,db));
							tc.start();
							try {
								tc.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int d = 0;d<selectthreadnum;d++) {
							Thread td = new Thread(new runMysqlSelect(host,user,pass,port,db,paraFiled,paraCondition));
							td.start();
							try {
								td.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				}
				
				//
				if(tbtype.equals("btiny")) {
					int time = Integer.valueOf(lasting);//计时
					Long max = ck.Time()+time * 1000;
					int insertthreadnum = Integer.valueOf(insertThreads);//普通表写入并发
					int updatethreadnum = Integer.valueOf(updateThreads);
					int deletethreadnum = Integer.valueOf(deleteThreads);
					int selectthreadnum = Integer.valueOf(selectThreads);
					start = ck.Time();
					while(ck.Time() < max) {

						for(int a = 0;a<insertthreadnum;a++) {
							Thread ta = new Thread(new runMysqlInsertTinyblob(host, user, pass, port, db, tbnum, insertValue, Integer.valueOf(insertFieldnum),blobfilepath));
							ta.start();
							try {
								ta.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int b = 0;b<updatethreadnum;b++) {
							Thread tb = new Thread(new runMysqlUpdate(host,user,pass,port,db,tbnum));
							tb.start();
							try {
								tb.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						for(int c = 0;c<deletethreadnum;c++) {
							Thread tc = new Thread(new runMysqlDelete(host,user,pass,port,db));
							tc.start();
							try {
								tc.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int d = 0;d<selectthreadnum;d++) {
							Thread td = new Thread(new runMysqlSelectTinyblob(host,user,pass,port,db,paraFiled,paraCondition,blobfilepath));
							td.start();
							try {
								td.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				}
				
				if(tbtype.equals("bmedium")) {
					int time = Integer.valueOf(lasting);//计时
					Long max = ck.Time()+time * 1000;
					int insertthreadnum = Integer.valueOf(insertThreads);//普通表写入并发
					int updatethreadnum = Integer.valueOf(updateThreads);
					int deletethreadnum = Integer.valueOf(deleteThreads);
					int selectthreadnum = Integer.valueOf(selectThreads);
					start = ck.Time();
					while(ck.Time() < max) {

						for(int a = 0;a<insertthreadnum;a++) {
							Thread ta = new Thread(new runMysqlInsertMediumblob(host, user, pass, port, db, tbnum, insertValue, Integer.valueOf(insertFieldnum),blobfilepath));
							ta.start();
							try {
								ta.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int b = 0;b<updatethreadnum;b++) {
							Thread tb = new Thread(new runMysqlUpdate(host,user,pass,port,db,tbnum));
							tb.start();
							try {
								tb.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						for(int c = 0;c<deletethreadnum;c++) {
							Thread tc = new Thread(new runMysqlDelete(host,user,pass,port,db));
							tc.start();
							try {
								tc.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int d = 0;d<selectthreadnum;d++) {
							Thread td = new Thread(new runMysqlSelectMediumblob(host,user,pass,port,db,paraFiled,paraCondition,outBlobfilepath));
							td.start();
							try {
								td.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				}
				
				//
				if(tbtype.equals("bblob")) {
					int time = Integer.valueOf(lasting);//计时
					Long max = ck.Time()+time * 1000;
					int insertthreadnum = Integer.valueOf(insertThreads);//普通表写入并发
					int updatethreadnum = Integer.valueOf(updateThreads);
					int deletethreadnum = Integer.valueOf(deleteThreads);
					int selectthreadnum = Integer.valueOf(selectThreads);
					start = ck.Time();
					while(ck.Time() < max) {

						for(int a = 0;a<insertthreadnum;a++) {
							Thread ta = new Thread(new runMysqlInsertCommonblob(host, user, pass, port, db, tbnum, insertValue, Integer.valueOf(insertFieldnum),blobfilepath));
							ta.start();
							try {
								ta.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int b = 0;b<updatethreadnum;b++) {
							Thread tb = new Thread(new runMysqlUpdate(host,user,pass,port,db,tbnum));
							tb.start();
							try {
								tb.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						for(int c = 0;c<deletethreadnum;c++) {
							Thread tc = new Thread(new runMysqlDelete(host,user,pass,port,db));
							tc.start();
							try {
								tc.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int d = 0;d<selectthreadnum;d++) {
							Thread td = new Thread(new runMysqlSelectCommonblob(host,user,pass,port,db,paraFiled,paraCondition,blobfilepath));
							td.start();
							try {
								td.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				}
				
				//
				if(tbtype.equals("blong")) {
					int time = Integer.valueOf(lasting);//计时
					Long max = ck.Time()+time * 1000;
					int insertthreadnum = Integer.valueOf(insertThreads);//普通表写入并发
					int updatethreadnum = Integer.valueOf(updateThreads);
					int deletethreadnum = Integer.valueOf(deleteThreads);
					int selectthreadnum = Integer.valueOf(selectThreads);
					start = ck.Time();
					while(ck.Time() < max) {

						for(int a = 0;a<insertthreadnum;a++) {
							Thread ta = new Thread(new runMysqlInsertLongblob(host, user, pass, port, db, tbnum, insertValue, Integer.valueOf(insertFieldnum),blobfilepath));
							ta.start();
							try {
								
								ta.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int b = 0;b<updatethreadnum;b++) {
							Thread tb = new Thread(new runMysqlUpdate(host,user,pass,port,db,tbnum));
							tb.start();
							try {
								tb.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						for(int c = 0;c<deletethreadnum;c++) {
							Thread tc = new Thread(new runMysqlDelete(host,user,pass,port,db));
							tc.start();
							try {
								tc.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int d = 0;d<selectthreadnum;d++) {
							Thread td = new Thread(new runMysqlSelectLongblob(host,user,pass,port,db,paraFiled,paraCondition,outBlobfilepath));
							td.start();
							try {
								td.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				}
				
				//
				if(tbtype.equals("bmix")) {
					int time = Integer.valueOf(lasting);//计时
					Long max = ck.Time()+time * 1000;
					int insertthreadnum = Integer.valueOf(insertThreads);//普通表写入并发
					int updatethreadnum = Integer.valueOf(updateThreads);
					int deletethreadnum = Integer.valueOf(deleteThreads);
					int selectthreadnum = Integer.valueOf(selectThreads);
					start = ck.Time();
					while(ck.Time() < max) {

						for(int a = 0;a<insertthreadnum;a++) {
							Thread ta = new Thread(new runMysqlInsertMixblob(host, user, pass, port, db, tbnum, insertValue, Integer.valueOf(insertFieldnum),blobfilepath));
							ta.start();
							try {
								ta.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int b = 0;b<updatethreadnum;b++) {
							Thread tb = new Thread(new runMysqlUpdate(host,user,pass,port,db,tbnum));
							tb.start();
							try {
								tb.sleep(10);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}
						for(int c = 0;c<deletethreadnum;c++) {
							Thread tc = new Thread(new runMysqlDelete(host,user,pass,port,db));
							tc.start();
							try {
								tc.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						for(int d = 0;d<selectthreadnum;d++) {
							Thread td = new Thread(new runMysqlSelectMixblob(host,user,pass,port,db,paraFiled,paraCondition,outBlobfilepath));
							td.start();
							try {
								td.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
				}
			}
		
		System.out.println("");
		if(!(insertThreads.equals("0"))) {
			System.out.println("完成插入事务总数 : "+ctMysql.itnums+" , 插入事务总耗时 : "+ctMysql.itall+"毫秒 , 插入事务用时最短 : "+ctMysql.itfasted+"毫秒 ,"
					+ " 插入事务用时最长 : "+ctMysql.itslowed+ "毫秒, 平均耗时 : "+ ctMysql.itall / ctMysql.itnums+"毫秒，失败插入事务数： "+(ctMysql.itfnums-1)+" 。");
		}
		
		if(!(updateThreads.equals("0"))) {
			System.out.println("完成更新事务总数 : "+ctMysql.utnums+" , 更新事务总耗时 : "+ctMysql.utall+"毫秒 , 更新事务用时最短 : "+ctMysql.utfasted+"毫秒 ,"
					+ " 更新事务用时最长 : "+ctMysql.utslowed+ "毫秒, 平均耗时 : "+ ctMysql.utall / ctMysql.utnums+"毫秒，失败更新事务数： "+(ctMysql.utfnums-1)+" 。");
		}
		
		if(!(selectThreads.equals("0"))) {
			System.out.println("读取事务总数 : "+ctMysql.qnums+" , 读取事务总耗时 : "+ctMysql.qall+"毫秒 , 读取事务用时最短 : "+ctMysql.qfasted+"毫秒 ,"
					+ " 读取事务用时最长 : "+ctMysql.qslowed+ "毫秒, 平均耗时 : "+ ctMysql.qall / ctMysql.qnums+"毫秒，失败查询事务数 ： "+(ctMysql.qfnums-1)+" 。");
		}
		
		
	}

}
