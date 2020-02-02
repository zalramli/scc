package com.its.scc.Activities.Eksternal.ListProve.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.ListProve.view.IEksternalListProveView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Prove;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EksternalListProvePresenter implements IEksternalListProvePresenter {

	Context context;
	IEksternalListProveView eksternalListProveView;

	BaseUrl baseUrl;


	ArrayList<Prove> dataModelArrayList;

	public EksternalListProvePresenter(Context context, IEksternalListProveView eksternalListProveView) {
		this.context = context;
		this.eksternalListProveView = eksternalListProveView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void inisiasiAwal(String id , String hakAkses) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/prove/list_prove"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject obj = new JSONObject(response);
						String success = obj.getString("success");
						String message = obj.getString("message");

						if (success.equals("1")) {

							dataModelArrayList = new ArrayList<>();
							JSONArray dataArray = obj.getJSONArray("list_prove");
							for (int i = 0; i < dataArray.length(); i++) {

								Prove playerModel = new Prove();
								JSONObject dataobj = dataArray.getJSONObject(i);

								String id_prove = dataobj.getString("id_prove");
								String id_eksternal = dataobj.getString("id_eksternal");
								String nama_eksternal = dataobj.getString("nama_eksternal");
								String id_internal = dataobj.getString("id_internal");
								String nama_internal = dataobj.getString("nama_internal");
								String id_materi_prove = dataobj.getString("id_materi_prove");
								String nama_materi_prove = dataobj.getString("nama_materi_prove");
								String id_jadwal_prove = dataobj.getString("id_jadwal_prove");
								String hari = dataobj.getString("hari");
								String jam_mulai = dataobj.getString("jam_mulai");
								String jam_selesai = dataobj.getString("jam_selesai");
								String deskripsi_materi = dataobj.getString("deskripsi_materi");
								String tanggal_booking = dataobj.getString("tanggal_booking");
								String tanggal_prove = dataobj.getString("tanggal_prove");
								String kode_prove = dataobj.getString("kode_prove");
								String kata_sandi = dataobj.getString("kata_sandi");
								String status_prove = dataobj.getString("status_prove");

								playerModel.setId_prove(id_prove);
								playerModel.setId_eksternal(id_eksternal);
								playerModel.setNama_eksternal(nama_eksternal);
								playerModel.setId_internal(id_internal);
								playerModel.setNama_internal(nama_internal);
								playerModel.setId_materi_prove(id_materi_prove);
								playerModel.setNama_materi_prove(nama_materi_prove);
								playerModel.setId_jadwal_prove(id_jadwal_prove);
								playerModel.setHari(hari);
								playerModel.setJam_mulai(jam_mulai);
								playerModel.setJam_selesai(jam_selesai);
								playerModel.setDeskripsi_materi(deskripsi_materi);
								playerModel.setTanggal_booking(tanggal_booking);
								playerModel.setTanggal_prove(tanggal_prove);
								playerModel.setKode_prove(kode_prove);
								playerModel.setKata_sandi(kata_sandi);
								playerModel.setKata_sandi(status_prove);

								dataModelArrayList.add(playerModel);
							}
							eksternalListProveView.onSetupListView(dataModelArrayList);

						} else {
							dataModelArrayList = new ArrayList<>();
							eksternalListProveView.onSetupListView(dataModelArrayList);
							eksternalListProveView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalListProveView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalListProveView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				params.put("ha_akses", hakAkses);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
