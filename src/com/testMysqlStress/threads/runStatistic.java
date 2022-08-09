package com.testMysqlStress.threads;
import java.text.SimpleDateFormat;
import java.util.Date;


public class runStatistic  implements Runnable{

	private int insert_threads;
	private int select_threads;
	private int delete_threads;
	private int update_threads;
	private int tps;
	private int qps;
	private Long lat95;
	private Long recordTime;
	Date dt= new Date();
	
//	public runStatistic(int ths,int t,int q,Long lat,Long time) {
//		threads = ths;
//		tps = t;
//		qps = q;
//		lat95 = lat;
//		recordTime = String.valueOf(time);
//	}
	public runStatistic(int ths1,int ths2,int ths3,int ths4) {
		insert_threads = ths1;
		select_threads = ths2;
		delete_threads = ths3;
		update_threads = ths4;
//		tps = t;
//		qps = q;
//		lat95 = lat;
//		recordTime = time;
	}
	
	
	@Override
	public void run() {
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("[ "+sdFormatter.format(dt.getTime())+" ] insert threads:"+insert_threads+",select threads:"+select_threads+",delete threads:"+delete_threads
				+ ",update threads:"+update_threads);

				
		
		//		[ 230s ] thds: 32 tps: 820.00 qps: 820.00 (r/w/o: 0.00/820.00/0.00) lat (ms,95%): 55.82 err/s: 0.00 reconn/s: 0.00
	}

}
