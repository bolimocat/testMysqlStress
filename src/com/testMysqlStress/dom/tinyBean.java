package com.testMysqlStress.dom;

import com.mysql.cj.jdbc.Blob;

public class tinyBean {
	private Blob tinyblob;

	public Blob getTinyblob() {
		return tinyblob;
	}

	public void setTinyblob(java.sql.Blob blob) {
		this.tinyblob = (Blob) blob;
	}

}
		