package com.testMysqlStress.dom;

import com.mysql.cj.jdbc.Blob;

public class mediumBean {
	private Blob mediumblob;

	public Blob getMediumblob() {
		return mediumblob;
	}

	public void setMediumblob(java.sql.Blob blob) {
		this.mediumblob = (Blob) blob;
	}


}
