package com.testMysqlStress.dom;

import com.mysql.cj.jdbc.Blob;

public class blobBean {

	private Blob blobblob;

	public Blob getBlobblob() {
		return blobblob;
	}

//	public void setBlobblob(Blob blobblob) {
//		this.blobblob = blobblob;
//	}
	
	public void setBlobblob(java.sql.Blob blob) {
		this.blobblob = (Blob) blob;
	}
}
