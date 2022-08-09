package com.testMysqlStress.function;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.testMysqlStress.dom.blobBean;
import com.testMysqlStress.dom.longBean;
import com.testMysqlStress.dom.mediumBean;
import com.testMysqlStress.dom.mixBean;
import com.testMysqlStress.dom.tinyBean;
import com.testMysqlStress.resource.createTbField;
import com.testMysqlStress.statistic.statisticInfo;

public class controlMySqlBlob extends createTbField {
	
	private String host;
	private String user;
	private String pass;
	private String port;
	private String database;
//	it为所有insert事务，
	public static int itnums = 1; //insert correct number
	public static int itfnums = 1; //insert incorrect number
	public  static Long itfasted = 10000l;
	public  static Long itslowed = 0l;
	public  static Long itall = 0l;
	public  static Long itconsume = 0l;
//	ut为所有update事务，
	public static int utnums = 1; 
	public static int utfnums = 1; 
	public  static Long utfasted = 10000l;
	public  static Long utslowed = 0l;
	public  static Long utall = 0l;
	public  static Long utconsume = 0l;
	
	
// q为所有查询事务，不包括写入
	public static int qnums = 1;
	public static int qfnums = 1;
	public  static Long qfasted = 10000l;
	public  static Long qslowed = 0l;
	public  static Long qall = 0l;
	public  static Long qconsume = 0l;
	
//	statisticInfo ssi = new statisticInfo();
	
	public controlMySqlBlob(String h,String u,String ps,String pt,String db) {
		host = h;
		user = u;
		pass = ps;
		port = pt;
		database = db;
	}
	
	//定义数据库操作
	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	commonkit ck = new commonkit();
	
	
	/**
	 * tiny_blob建表操作
	 * @param tbIdx
	 * @param tbKind
	 */
	public void createtinyblobtb(int tbNum,String tbField,int tbFieldNum) {
		String createtb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"";
			ct = DriverManager.getConnection(url, user, pass);
				for(int i=0;i<tbNum;i++) {
					System.out.println("建表： "+TINYNM+"_"+i);
					if(tbField.equals("null")) { //建表时如果没指定字段类型和字段数量，则以默认语句建表
						createtb = "create table  "+TINYNM+"_"+i+" ("+TINYFILED+";";
					}
					else {
						String customFiled = "";
						for(int j=0;j<tbFieldNum;j++) {
							customFiled = customFiled + "filed_"+j+" "+tbField+" ,"+" ";
						}
						customFiled = customFiled + "id int AUTO_INCREMENT, primary key(id))ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8";
						System.out.println("建表语句： "+customFiled);
						createtb = "create table  "+TINYNM+"_"+i+" ("+customFiled+";";
					}
					
					ps = ct.prepareStatement(createtb);
					ps.executeUpdate();
				}
			
		}catch (Exception eCreatetb) {
			eCreatetb.printStackTrace();
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println("tiny_blob 建表完成... ...");
	}
	
	/**
	 * create medium filed table 
	 * @param tbNum
	 * @param tbField
	 * @param tbFieldNum
	 */
	public void createmediumblobtb(int tbNum,String tbField,int tbFieldNum) {
		String createtb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"";
			ct = DriverManager.getConnection(url, user, pass);
				for(int i=0;i<tbNum;i++) {
					System.out.println("建表： "+MEDIUMNM+"_"+i);
					if(tbField.equals("null")) { //建表时如果没指定字段类型和字段数量，则以默认语句建表
						createtb = "create table  "+MEDIUMNM+"_"+i+" ("+MEDIUMFILED+";";
					}
					else {
						String customFiled = "";
						for(int j=0;j<tbFieldNum;j++) {
							customFiled = customFiled + "filed_"+j+" "+tbField+" ,"+" ";
						}
						customFiled = customFiled + "id int AUTO_INCREMENT, primary key(id))ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8";
						System.out.println("建表语句： "+customFiled);
						createtb = "create table  "+MEDIUMNM+"_"+i+" ("+customFiled+";";
					}
					
					ps = ct.prepareStatement(createtb);
					ps.executeUpdate();
				}
			
		}catch (Exception eCreatetb) {
			eCreatetb.printStackTrace();
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println("medium_blob 建表完成... ...");
	}
	
	/**
	 * create common blob filed table
	 * @param tbNum
	 * @param tbField
	 * @param tbFieldNum
	 */
	public void createblobtb(int tbNum,String tbField,int tbFieldNum) {
		String createtb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"";
			ct = DriverManager.getConnection(url, user, pass);
				for(int i=0;i<tbNum;i++) {
					System.out.println("建表： "+BLOBNM+"_"+i);
					if(tbField.equals("null")) { //建表时如果没指定字段类型和字段数量，则以默认语句建表
						createtb = "create table  "+BLOBNM+"_"+i+" ("+BLOBFILED+";";
					}
					else {
						String customFiled = "";
						for(int j=0;j<tbFieldNum;j++) {
							customFiled = customFiled + "filed_"+j+" "+tbField+" ,"+" ";
						}
						customFiled = customFiled + "id int AUTO_INCREMENT, primary key(id))ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8";
						System.out.println("建表语句： "+customFiled);
						createtb = "create table  "+BLOBNM+"_"+i+" ("+customFiled+";";
					}
					
					ps = ct.prepareStatement(createtb);
					ps.executeUpdate();
				}
			
		}catch (Exception eCreatetb) {
			eCreatetb.printStackTrace();
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println("blob 建表完成... ...");
	}
		
	/**
	 * create long blob filed table
	 * @param tbNum
	 * @param tbField
	 * @param tbFieldNum
	 */
	public void createlongblobtb(int tbNum,String tbField,int tbFieldNum) {
		String createtb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"";
			ct = DriverManager.getConnection(url, user, pass);
				for(int i=0;i<tbNum;i++) {
					System.out.println("建表： "+LONGNM+"_"+i);
					if(tbField.equals("null")) { //建表时如果没指定字段类型和字段数量，则以默认语句建表
						createtb = "create table  "+LONGNM+"_"+i+" ("+LONGFILED+";";
					}
					else {
						String customFiled = "";
						for(int j=0;j<tbFieldNum;j++) {
							customFiled = customFiled + "filed_"+j+" "+tbField+" ,"+" ";
						}
						customFiled = customFiled + "id int AUTO_INCREMENT, primary key(id))ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8";
						System.out.println("建表语句： "+customFiled);
						createtb = "create table  "+LONGNM+"_"+i+" ("+customFiled+";";
					}
					
					ps = ct.prepareStatement(createtb);
					ps.executeUpdate();
				}
			
		}catch (Exception eCreatetb) {
			eCreatetb.printStackTrace();
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println("long blob 建表完成... ...");
	}
	
	/**
	 * create long blob filed table
	 * @param tbNum
	 * @param tbField
	 * @param tbFieldNum
	 */
	public void createmixblobtb(int tbNum,String tbField,int tbFieldNum) {
		String createtb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"";
			ct = DriverManager.getConnection(url, user, pass);
				for(int i=0;i<tbNum;i++) {
					System.out.println("建表： "+MIXNM+"_"+i);
					if(tbField.equals("null")) { //建表时如果没指定字段类型和字段数量，则以默认语句建表
						createtb = "create table  "+MIXNM+"_"+i+" ("+MIXFILED+";";
					}
					else {
						String customFiled = "";
						for(int j=0;j<tbFieldNum;j++) {
							customFiled = customFiled + "filed_"+j+" "+tbField+" ,"+" ";
						}
						customFiled = customFiled + "id int AUTO_INCREMENT, primary key(id))ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8";
						System.out.println("建表语句： "+customFiled);
						createtb = "create table  "+MIXNM+"_"+i+" ("+customFiled+";";
					}
					
					ps = ct.prepareStatement(createtb);
					ps.executeUpdate();
				}
			
		}catch (Exception eCreatetb) {
			eCreatetb.printStackTrace();
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println("mix blob 建表完成... ...");
	}
		
	/**
	 * 插入tiny_blob记录
	 * @param tbIdx
	 * @param tbKind
	 */
	public Long inserttinytb(String host,String user,String pass,String port,String db,int tbnum,String insertValue,String tinyblobfile) {
		String istTb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
				for(int i=0;i<tbnum;i++) {
					if(insertValue.equals("null")) {
						istTb = "insert into "+TINYNM + "_"+i+" (tinyfiled) values (?)";
//						InputStream in = new FileInputStream("./file/tinyblob.txt");
						InputStream in = new FileInputStream(tinyblobfile);
						ps = ct.prepareStatement(istTb);//查询写入大对象的ps 语句用法
						ps.setBlob(1, in);
						Long start = ck.Time();
						if(ps.executeUpdate()>0) {
							itnums = itnums + 1;
							Long end = ck.Time();
							itconsume = ck.timer(start, end);
						}
						else {
							itfnums = itfnums + 1;
							System.out.println("INSERT ERROR : "+istTb);
							Long end = -999999L;
							itconsume = ck.timer(start, end);
						}
						
						
						itall = itall + itconsume;
						if(itconsume < itfasted ) {
							itfasted = itconsume;
						}
						if(itconsume >= itfasted ) {
							itfasted = itfasted;
						}
						if(itconsume > itslowed) {
							itslowed = itconsume;
						}
						if(itconsume <= itslowed) {
							itslowed = itslowed;
						}
					}
					else {
						System.out.println("插入非默认字段");
					}
					
					
				}
			
		}catch (Exception einserttb) {
//			einserttb.printStackTrace();
			einserttb.printStackTrace(System.out);
			itconsume = 0l;
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
	return itconsume;
//		System.out.println("本次写入耗时："+tconsume);
	}
	
	/**
	 * 插入medium_blob记录
	 * @param tbIdx
	 * @param tbKind
	 */
	public Long insertmediumtb(String host,String user,String pass,String port,String db,int tbnum,String insertValue,String mediumblobfile) {
		String istTb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
				for(int i=0;i<tbnum;i++) {
					if(insertValue.equals("null")) {
						istTb = "insert into "+MEDIUMNM + "_"+i+" (mediumfiled) values (?)";
						InputStream in = new FileInputStream(mediumblobfile);
						ps = ct.prepareStatement(istTb);//查询写入大对象的ps 语句用法
						ps.setBlob(1, in);
						Long start = ck.Time();
						if(ps.executeUpdate()>0) {
							itnums = itnums + 1;
							Long end = ck.Time();
							itconsume = ck.timer(start, end);
						}
						else {
							itfnums = itfnums + 1;
							System.out.println("INSERT ERROR : "+istTb);
							Long end = -999999L;
							itconsume = ck.timer(start, end);
						}
						
						
						itall = itall + itconsume;
						if(itconsume < itfasted ) {
							itfasted = itconsume;
						}
						if(itconsume >= itfasted ) {
							itfasted = itfasted;
						}
						if(itconsume > itslowed) {
							itslowed = itconsume;
						}
						if(itconsume <= itslowed) {
							itslowed = itslowed;
						}
					}
					else {
						System.out.println("插入非默认字段");
					}
					
					
				}
			
		}catch (Exception einserttb) {
//			einserttb.printStackTrace();
			einserttb.printStackTrace(System.out);
			itconsume = 0l;
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
	return itconsume;
//		System.out.println("本次写入耗时："+tconsume);
	}
	
	/**
	 * 插入long blob记录
	 * @param tbIdx
	 * @param tbKind
	 */
	public Long insertlongtb(String host,String user,String pass,String port,String db,int tbnum,String insertValue,String longblobfile) {
		String istTb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
				for(int i=0;i<tbnum;i++) {
					if(insertValue.equals("null")) {
						istTb = "insert into "+LONGNM + "_"+i+" (longfiled) values (?)";
						InputStream in = new FileInputStream(longblobfile);
						ps = ct.prepareStatement(istTb);//查询写入大对象的ps 语句用法
						ps.setBlob(1, in);
						Long start = ck.Time();
						if(ps.executeUpdate()>0) {
							itnums = itnums + 1;
							Long end = ck.Time();
							itconsume = ck.timer(start, end);
						}
						else {
							itfnums = itfnums + 1;
							System.out.println("INSERT ERROR : "+istTb);
							Long end = -999999L;
							itconsume = ck.timer(start, end);
						}
						
						
						itall = itall + itconsume;
						if(itconsume < itfasted ) {
							itfasted = itconsume;
						}
						if(itconsume >= itfasted ) {
							itfasted = itfasted;
						}
						if(itconsume > itslowed) {
							itslowed = itconsume;
						}
						if(itconsume <= itslowed) {
							itslowed = itslowed;
						}
					}
					else {
						System.out.println("插入非默认字段");
					}
					
					
				}
			
		}catch (Exception einserttb) {
//			einserttb.printStackTrace();
			einserttb.printStackTrace(System.out);
			itconsume = 0l;
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
	return itconsume;
//		System.out.println("本次写入耗时："+tconsume);
	}
		
	/**
	 * 插入blob记录
	 * @param tbIdx
	 * @param tbKind
	 */
	public Long insertblobtb(String host,String user,String pass,String port,String db,int tbnum,String insertValue,String blobfile) {
		String istTb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
				for(int i=0;i<tbnum;i++) {
					if(insertValue.equals("null")) {
						istTb = "insert into "+BLOBNM + "_"+i+" (blobfiled) values (?)";
						InputStream in = new FileInputStream(blobfile);
						ps = ct.prepareStatement(istTb);//查询写入大对象的ps 语句用法
						ps.setBlob(1, in);
						Long start = ck.Time();
						if(ps.executeUpdate()>0) {
							itnums = itnums + 1;
							Long end = ck.Time();
							itconsume = ck.timer(start, end);
						}
						else {
							itfnums = itfnums + 1;
							System.out.println("INSERT ERROR : "+istTb);
							Long end = -999999L;
							itconsume = ck.timer(start, end);
						}
						
						
						itall = itall + itconsume;
						if(itconsume < itfasted ) {
							itfasted = itconsume;
						}
						if(itconsume >= itfasted ) {
							itfasted = itfasted;
						}
						if(itconsume > itslowed) {
							itslowed = itconsume;
						}
						if(itconsume <= itslowed) {
							itslowed = itslowed;
						}
					}
					else {
						System.out.println("插入非默认字段");
					}
					
					
				}
			
		}catch (Exception einserttb) {
//			einserttb.printStackTrace();
			einserttb.printStackTrace(System.out);
			itconsume = 0l;
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
	return itconsume;
//		System.out.println("本次写入耗时："+tconsume);
	}
	
	/**
	 * 插入mix blob记录
	 * @param tbIdx
	 * @param tbKind
	 */
	public Long insertmixtb(String host,String user,String pass,String port,String db,int tbnum,String insertValue,String mixblobfile) {
		String istTb = "";
		String[] mix_array = mixblobfile.split("_");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
				for(int i=0;i<tbnum;i++) {
					if(insertValue.equals("null")) {
						istTb = "insert into "+MIXNM + "_"+i+" (tinyfiled,mediumfiled,blobfiled,longfiled) values (?,?,?,?)";
						InputStream in1 = new FileInputStream(mix_array[0]);
						InputStream in2 = new FileInputStream(mix_array[1]);
						InputStream in3 = new FileInputStream(mix_array[2]);
						InputStream in4 = new FileInputStream(mix_array[3]);
						ps = ct.prepareStatement(istTb);//查询写入大对象的ps 语句用法
						ps.setBlob(1, in1);
						ps.setBlob(2, in2);
						ps.setBlob(3, in3);
						ps.setBlob(4, in4);
						Long start = ck.Time();
						if(ps.executeUpdate()>0) {
							itnums = itnums + 1;
							Long end = ck.Time();
							itconsume = ck.timer(start, end);
						}
						else {
							itfnums = itfnums + 1;
							System.out.println("INSERT ERROR : "+istTb);
							Long end = -999999L;
							itconsume = ck.timer(start, end);
						}
						
						
						itall = itall + itconsume;
						if(itconsume < itfasted ) {
							itfasted = itconsume;
						}
						if(itconsume >= itfasted ) {
							itfasted = itfasted;
						}
						if(itconsume > itslowed) {
							itslowed = itconsume;
						}
						if(itconsume <= itslowed) {
							itslowed = itslowed;
						}
					}
					else {
						System.out.println("插入非默认字段");
					}
					
					
				}
			
		}catch (Exception einserttb) {
//			einserttb.printStackTrace();
			einserttb.printStackTrace(System.out);
			itconsume = 0l;
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
	return itconsume;
//		System.out.println("本次写入耗时："+tconsume);
	}
	
	
	/**
	 * 删除记录
	 * @param tbIdx
	 * @param tbKind
	 */
	public void deletetb(String host,String user,String pass,String port,String db) {
		String detTb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
			
				System.out.println("删除普通表记录");
				detTb = "delete from "+CMTBNAME+" where id = "+ck.random()+"";
			
		
			ps = ct.prepareStatement(detTb);
			ps.executeUpdate();
		}catch (Exception edeletetb) {
			edeletetb.printStackTrace();
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
	}

	/**
	 * 更新记录
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param db
	 * @param tbKind
	 */
	public Long updatetb(String host,String user,String pass,String port,String db,int tbrange) {
		String updateTb = "";
		Random rand=new Random();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			int tbnum = rand.nextInt(tbrange);
			int idnum = ck.random();
			updateTb = "update "+CMTBNAME+"_"+tbnum+" set char_type = 'update' where id = "+idnum;
			ps = ct.prepareStatement(updateTb);
			Long start = ck.Time();
			if(ps.executeUpdate()>0) {
				utnums = utnums + 1;
				Long end = ck.Time();
				utconsume = ck.timer(start, end);
			}
			else {
				utfnums = utfnums + 0;
				Long end = -999999L;
//				tconsume = ck.timer(start, end);
				utconsume = 0L;
			}
			
			utall = utall + utconsume;
			if(utconsume < utfasted ) {
				utfasted = utconsume;
			}
			if(utconsume >= utfasted ) {
				utfasted = utfasted;
			}
			if(utconsume > utslowed) {
				utslowed = utconsume;
			}
			if(utconsume <= utslowed) {
				utslowed = utslowed;
			}
			
		}catch (Exception eupdatetb) {
			eupdatetb.printStackTrace(System.out);
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
//				tconsume = 0l;
			}
		}
		
		return utconsume;
	}

	/**
	 * tinyblob查询操作
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param db
	 * @param tbKind
	 */
	public ArrayList<tinyBean> selecttinytb(String host,String user,String pass,String port,String db,String paraFiled,String paraCondition,String outfilepath) {
		String selectTb = "";
		commonkit kit = new commonkit();
		Date date = new Date();
		date.setTime(kit.Time());
		ArrayList<tinyBean> tinybloblist = new ArrayList<tinyBean>();
//		Random rand=new Random();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);

				if((paraFiled.equals("null"))||(paraCondition.equals("null"))) {
					selectTb = "select tinyfiled from "+TINYNM+"_0 limit  1";
					ps = ct.prepareStatement(selectTb);
					Long start = ck.Time();
					rs = ps.executeQuery();
					Long end = ck.Time();
					qconsume = ck.timer(start, end);
					qall = qall + qconsume;
					if(qconsume < qfasted ) {
						qfasted = qconsume;
					}
					if(qconsume >= qfasted ) {
						qfasted = qfasted;
					}
					
					
					if(qconsume > qslowed) {
						qslowed = qconsume;
					}
					if(qconsume <= qslowed) {
						qslowed = qslowed;
					}
					while(rs.next()) {
						qnums = qnums + 1;
						tinyBean tb = new tinyBean();
						tb.setTinyblob(rs.getBlob("tinyfiled"));
						tinybloblist.add(tb);
						
					}
				}
				else {
					System.out.println("not tiny blob");
					qfnums = qfnums + 1;
					System.out.println("QUERY ERROR : "+selectTb);
				}
		}catch (Exception eupdatetb) {
			eupdatetb.printStackTrace();
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",查询耗时："+qconsume+" ms");
		return tinybloblist;
	}

	
	/**
	 * mediumblob查询操作
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param db
	 * @param tbKind
	 */
	public ArrayList<mediumBean> selectmediumtb(String host,String user,String pass,String port,String db,String paraFiled,String paraCondition,String outfilepath) {
		String selectTb = "";
		commonkit kit = new commonkit();
		Date date = new Date();
		date.setTime(kit.Time());
		ArrayList<mediumBean> mediumbloblist = new ArrayList<mediumBean>();
//		Random rand=new Random();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);

				if((paraFiled.equals("null"))||(paraCondition.equals("null"))) {
					selectTb = "select mediumfiled from "+MEDIUMNM+"_0 limit  1";
					ps = ct.prepareStatement(selectTb);
					Long start = ck.Time();
					rs = ps.executeQuery();
					Long end = ck.Time();
					qconsume = ck.timer(start, end);
					qall = qall + qconsume;
					if(qconsume < qfasted ) {
						qfasted = qconsume;
					}
					if(qconsume >= qfasted ) {
						qfasted = qfasted;
					}
					
					
					if(qconsume > qslowed) {
						qslowed = qconsume;
					}
					if(qconsume <= qslowed) {
						qslowed = qslowed;
					}
					while(rs.next()) {
						qnums = qnums + 1;
						mediumBean mb = new mediumBean();
						mb.setMediumblob(rs.getBlob("mediumfiled"));
						mediumbloblist.add(mb);
					}
				}
				else {
					System.out.println("not tiny blob");
					qfnums = qfnums + 1;
					System.out.println("QUERY ERROR : "+selectTb);
				}
			
						
			
			
			
		}catch (Exception eupdatetb) {
			eupdatetb.printStackTrace();
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",查询耗时："+qconsume+" ms");
		return mediumbloblist;
	}

	
	/**
	 * commonblob查询操作
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param db
	 * @param tbKind
	 */
	public ArrayList<blobBean> selectblobtb(String host,String user,String pass,String port,String db,String paraFiled,String paraCondition,String outfilepaht) {
		String selectTb = "";
		commonkit kit = new commonkit();
		Date date = new Date();
		date.setTime(kit.Time());
		ArrayList<blobBean> bloblist = new ArrayList<blobBean>();
//		Random rand=new Random();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);

				if((paraFiled.equals("null"))||(paraCondition.equals("null"))) {
					selectTb = "select blobfiled from "+BLOBNM+"_0 limit  1";
					ps = ct.prepareStatement(selectTb);
					Long start = ck.Time();
					rs = ps.executeQuery();
					Long end = ck.Time();
					qconsume = ck.timer(start, end);
					qall = qall + qconsume;
					if(qconsume < qfasted ) {
						qfasted = qconsume;
					}
					if(qconsume >= qfasted ) {
						qfasted = qfasted;
					}
					
					
					if(qconsume > qslowed) {
						qslowed = qconsume;
					}
					if(qconsume <= qslowed) {
						qslowed = qslowed;
					}
					while(rs.next()) {
						qnums = qnums + 1;
						blobBean bb = new blobBean();
						bb.setBlobblob(rs.getBlob("blobfiled"));
						bloblist.add(bb);
					}
				}
				else {
					System.out.println("not tiny blob");
					qfnums = qfnums + 1;
					System.out.println("QUERY ERROR : "+selectTb);
				}
			
		}catch (Exception eupdatetb) {
			eupdatetb.printStackTrace();
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",查询耗时："+qconsume+" ms");
		return bloblist;
	}
	
	/**
	 * commonblob查询操作
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param db
	 * @param tbKind
	 */
	public ArrayList<longBean> selectlongblobtb(String host,String user,String pass,String port,String db,String paraFiled,String paraCondition,String outfilepath) {
		String selectTb = "";
		commonkit kit = new commonkit();
		Date date = new Date();
		date.setTime(kit.Time());
		ArrayList<longBean> longbloblist = new ArrayList<longBean>();
//		Random rand=new Random();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
				if((paraFiled.equals("null"))||(paraCondition.equals("null"))) {
					selectTb = "select longfiled from "+LONGNM+"_0 limit  1";
					
					ps = ct.prepareStatement(selectTb);
					Long start = ck.Time();
					rs = ps.executeQuery();
					Long end = ck.Time();
					qconsume = ck.timer(start, end);
										qall = qall + qconsume;
					if(qconsume < qfasted ) {
						qfasted = qconsume;
					}
					if(qconsume >= qfasted ) {
						qfasted = qfasted;
					}
					if(qconsume > qslowed) {
						qslowed = qconsume;
					}
					if(qconsume <= qslowed) {
						qslowed = qslowed;
					}
					while(rs.next()) {
						qnums = qnums + 1;
						longBean lb = new longBean();
						lb.setLongblob(rs.getBlob("longfiled"));
						longbloblist.add(lb);
					}
				}
				else {
					System.out.println("not tiny blob");
					qfnums = qfnums + 1;
					System.out.println("QUERY ERROR : "+selectTb);
				}
			
		}catch (Exception eupdatetb) {
			eupdatetb.printStackTrace();
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",查询耗时："+qconsume+" ms");
		return longbloblist;
	}
	
	/**
	 * commonblob查询操作
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param db
	 * @param tbKind
	 */
	public ArrayList<mixBean> selectmixblobtb(String host,String user,String pass,String port,String db,String paraFiled,String paraCondition,String outfilepath) {
		String selectTb = "";
		
		ArrayList<mixBean> mixbloblist = new ArrayList<mixBean>();
		commonkit kit = new commonkit();
		Date date = new Date();
		date.setTime(kit.Time());
//		Random rand=new Random();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);

				if((paraFiled.equals("null"))||(paraCondition.equals("null"))) {
					selectTb = "select tinyfiled,mediumfiled,blobfiled,longfiled from "+MIXNM+"_0 limit  1";
					ps = ct.prepareStatement(selectTb);
					Long start = ck.Time();
					rs = ps.executeQuery();
					Long end = ck.Time();
					qconsume = ck.timer(start, end);
					qall = qall + qconsume;
					if(qconsume < qfasted ) {
						qfasted = qconsume;
					}
					if(qconsume >= qfasted ) {
						qfasted = qfasted;
					}
					if(qconsume > qslowed) {
						qslowed = qconsume;
					}
					if(qconsume <= qslowed) {
						qslowed = qslowed;
					}
					while(rs.next()) {
						qnums = qnums + 1;
						mixBean mixb = new mixBean();
						mixb.setMixblobTinyfiled(rs.getBlob("tinyfiled"));
						mixb.setMixblobMediumfiled(rs.getBlob("mediumfiled"));
						mixb.setMixblobBlobfiled(rs.getBlob("blobfiled"));
						mixb.setMixblobLongfiled(rs.getBlob("longfiled"));
						mixbloblist.add(mixb);
					}
				}
				else {
					System.out.println("not tiny blob");
					qfnums = qfnums + 1;
					System.out.println("QUERY ERROR : "+selectTb);
				}
		}catch (Exception eupdatetb) {
			eupdatetb.printStackTrace();
		
		}
		finally{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception eCloseDb) {
				eCloseDb.printStackTrace();
			}
		}
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+",查询耗时：,"+qconsume+" ms");
		return mixbloblist;
	}
}
