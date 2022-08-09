package com.testMysqlStress.function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import com.testMysqlStress.resource.createTbField;
import com.testMysqlStress.statistic.statisticInfo;

public class controlMySql extends createTbField {
	
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
	
	public controlMySql(String h,String u,String ps,String pt,String db) {
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
	 * 建表操作
	 * @param tbIdx
	 * @param tbKind
	 */
	public void createtb(int tbNum,String tbField,int tbFieldNum) {
		
		String createtb = "";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+database+"";
			ct = DriverManager.getConnection(url, user, pass);
				for(int i=0;i<tbNum;i++) {
					System.out.println("建表： "+CMTBNAME+"_"+i);
					if(tbField.equals("null")) { //建表时如果没指定字段类型和字段数量，则以默认语句建表
						createtb = "create table  "+CMTBNAME+"_"+i+" ("+COMMONTBFILED+";";
					}
					else {
						String customFiled = "";
						for(int j=0;j<tbFieldNum;j++) {
							customFiled = customFiled + "filed_"+j+" "+tbField+" ,"+" ";
						}
						customFiled = customFiled + "id int AUTO_INCREMENT, primary key(id))ENGINE=InnoDB auto_increment=1 DEFAULT CHARSET=utf8";
						System.out.println("建表语句： "+customFiled);
						createtb = "create table  "+CMTBNAME+"_"+i+" ("+customFiled+";";
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
		System.out.println("建表完成... ...");
	}
	
	/**
	 * 插入记录
	 * @param tbIdx
	 * @param tbKind
	 */
	public Long inserttb(String host,String user,String pass,String port,String db,int tbnum,String insertValue,int insertFieldnum) {
		String istTb = "";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);
			
				for(int i=0;i<tbnum;i++) {
					if(insertValue.equals("null")) {
						istTb = "insert into "+CMTBNAME+"_"+i+"  values "+ ISTTBVALUE;
						ps = ct.prepareStatement(istTb);
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
						String isttbvalue = "";
						for(int j=0;j<insertFieldnum;j++) {
							isttbvalue = isttbvalue + insertValue;
						}
						isttbvalue = isttbvalue + "NULL );";
						istTb = "insert into "+CMTBNAME+"_"+i+"  values ("+ isttbvalue;
						ps = ct.prepareStatement(istTb);
						Long start = ck.Time();
						if(ps.executeUpdate()>0) {
							ps.executeUpdate();
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
						Long end = ck.Time();
						itconsume = ck.timer(start, end);
						
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
	 * 查询操作
	 * @param host
	 * @param user
	 * @param pass
	 * @param port
	 * @param db
	 * @param tbKind
	 */
	public Long selecttb(String host,String user,String pass,String port,String db,String paraFiled,String paraCondition,int tbnum) {
		String selectTb = "";
		Random rand=new Random();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://"+host+":"+port+"/"+db+"";
			ct = DriverManager.getConnection(url, user, pass);

//			int tbnum = rand.nextInt(tbnum);

				if((paraFiled.equals("null"))||(paraCondition.equals("null"))) {
					selectTb = "select "+SELECTFILED+" from "+CMTBNAME+"_"+tbnum+"  where id = "+ rand.nextInt(100) +"&& id > 0";
				}
				else {
					selectTb = "select "+paraFiled+" from "+CMTBNAME+"_"+tbnum+" where "+paraCondition+" ;";
				}

		
		
			ps = ct.prepareStatement(selectTb);
			Long start = ck.Time();
			rs = ps.executeQuery();
			if(rs.next()) {
				qnums = qnums + 1;
			}
			else {
				qfnums = qfnums + 1;
				System.out.println("QUERY ERROR : "+selectTb);
			}
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
		return qconsume;
	}


}
