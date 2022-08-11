package com.testMysqlStress.threads;
import java.text.SimpleDateFormat;
import java.util.Date;


public class runStatistic  implements Runnable{

	private String runType;
	private int interval;
	private int run_threads;
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
	public runStatistic(String type,int ths) {
		runType = type;
		run_threads = ths;
//		tps = t;
//		qps = q;
//		lat95 = lat;
//		recordTime = time;
	}
	
	
	@Override
	public void run() {
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("[ "+sdFormatter.format(dt.getTime())+" ] "+runType+" run threads:"+run_threads);

				
		
		//		[ 230s ] thds: 32 tps: 820.00 qps: 820.00 (r/w/o: 0.00/820.00/0.00) lat (ms,95%): 55.82 err/s: 0.00 reconn/s: 0.00
	}

}
