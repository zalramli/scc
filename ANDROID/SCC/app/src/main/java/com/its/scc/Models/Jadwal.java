package com.its.scc.Models;

public class Jadwal {
	String id_jadwal_prove, id_internal, hari, jam_mulai, jam_selesai, status_booking, status_aktif, terakhir_dibooking;

	public String getId_jadwal_prove() {
		return id_jadwal_prove;
	}

	public void setId_jadwal_prove(String id_jadwal_prove) {
		this.id_jadwal_prove = id_jadwal_prove;
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

	public String getTerakhir_dibooking() {
		return terakhir_dibooking;
	}

	public void setTerakhir_dibooking(String terakhir_dibooking) {
		this.terakhir_dibooking = terakhir_dibooking;
	}
}
