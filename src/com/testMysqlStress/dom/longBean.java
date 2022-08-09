package com.testMysqlStress.dom;

import com.mysql.cj.jdbc.Blob;

public class longBean {
	private Blob longblob;

	public Blob getLongblob() {
		return longblob;
	}

	public void setLongblob(java.sql.Blob blob) {
		this.longblob = (Blob) blob;
	}


}
