package com.its.scc.Models;

public class TableSoftware {
	String id, id_software,nama;

	public TableSoftware(String id, String id_software, String nama) {
		this.id = id;
		this.id_software = id_software;
		this.nama = nama;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_software() {
		return id_software;
	}

	public void setId_software(String id_software) {
		this.id_software = id_software;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
}
