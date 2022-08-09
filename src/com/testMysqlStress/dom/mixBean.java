package com.testMysqlStress.dom;

import com.mysql.cj.jdbc.Blob;

public class mixBean {
	private Blob tinyfiled;
	private Blob mediumfiled;
	private Blob blobfiled;
	public Blob getBlobfiled() {
		return blobfiled;
	}
	public void setBlobfiled(Blob blobfiled) {
		this.blobfiled = blobfiled;
	}
	public Blob getLongfiled() {
		return longfiled;
	}
	public void setLongfiled(Blob longfiled) {
		this.longfiled = longfiled;
	}
	public void setMediumfiled(Blob mediumfiled) {
		this.mediumfiled = mediumfiled;
	}
	private Blob longfiled;
	public Blob getTinyfiled() {
		return tinyfiled;
	}
	public void setTinyfiled(Blob tinyfiled) {
		this.tinyfiled = tinyfiled;
	}
	public Blob getMediumfiled() {
		return mediumfiled;
	}
	public void setMixblobTinyfiled(java.sql.Blob blob) {
		this.tinyfiled = (Blob) blob;
	}
	public void setMixblobMediumfiled(java.sql.Blob blob) {
		this.mediumfiled = (Blob) blob;
	}
	public void setMixblobBlobfiled(java.sql.Blob blob) {
		this.blobfiled = (Blob) blob;
	}
	public void setMixblobLongfiled(java.sql.Blob blob) {
		this.longfiled = (Blob) blob;
	}


}
