package com.its.scc.Models;

public class JadwalBS {
	String id_jadwal_bs,id_internal,hari,jam_mulai,jam_selesai,status_booking,status_aktif;

	public String getId_jadwal_bs() {
		return id_jadwal_bs;
	}

	public void setId_jadwal_bs(String id_jadwal_bs) {
		this.id_jadwal_bs = id_jadwal_bs;
	}

	public String getId_internal() {
		return id_internal;
	}

	public void setId_internal(String id_internal) {
		this.id_internal = id_internal;
	}

	public String getHari() {
		return hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}

	public String getJam_mulai() {
		return jam_mulai;
	}

	public void setJam_mulai(String jam_mulai) {
		this.jam_mulai = jam_mulai;
	}

	public String getJam_selesai() {
		return jam_selesai;
	}

	public void setJam_selesai(String jam_selesai) {
		this.jam_selesai = jam_selesai;
	}

	public String getStatus_booking() {
		return status_booking;
	}

	public void setStatus_booking(String status_booking) {
		this.status_booking = status_booking;
	}

	public String getStatus_aktif() {
		return status_aktif;
	}

	public void setStatus_aktif(String status_aktif) {
		this.status_aktif = status_aktif;
	}
}
