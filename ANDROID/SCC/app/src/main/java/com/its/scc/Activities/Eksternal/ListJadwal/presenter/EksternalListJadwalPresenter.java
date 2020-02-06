package com.its.scc.Activities.Eksternal.ListJadwal.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.ListJadwal.view.IEksternalListJadwalView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Jadwal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EksternalListJadwalPresenter implements IEksternalListJadwalPresenter {

	Context context;
	IEksternalListJadwalView eksternalListJadwalView;

	BaseUrl baseUrl;

	ArrayList<Jadwal> dataModelArrayList;

	public EksternalListJadwalPresenter(Context context, IEksternalListJadwalView eksternalListJadwalView) {
		this.context = context;
		this.eksternalListJadwalView = eksternalListJadwalView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void inisiasiAwal(String id_internal) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/jadwal/list_jadwal"; // url http request

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
							JSONArray dataArray = obj.getJSONArray("jadwal_prove");
							for (int i = 0; i < dataArray.length(); i++) {

								Jadwal playerModel = new Jadwal();
								JSONObject dataobj = dataArray.getJSONObject(i);

								String id_jadwal_prove = dataobj.getString("id_jadwal_prove");
								String id_internal = dataobj.getString("id_internal");
								String hari = dataobj.getString("hari");
								String jam_mulai = dataobj.getString("jam_mulai");
								String jam_selesai = dataobj.getString("jam_selesai");
								String status_booking = dataobj.getString("status_booking");
								String status_aktif = dataobj.getString("status_aktif");
								String terakhir_dibooking = dataobj.getString("terakhir_dibooking");

								playerModel.setId_jadwal_prove(id_jadwal_prove);
								playerModel.setId_internal(id_internal);
								playerModel.setHari(hari);
								playerModel.setJam_mulai(jam_mulai);
								playerModel.setJam_selesai(jam_selesai);
								playerModel.setStatus_booking(status_booking);
								playerModel.setStatus_aktif(status_aktif);
								playerModel.setTerakhir_dibooking(terakhir_dibooking);

								dataModelArrayList.add(playerModel);
							}

							eksternalListJadwalView.onSetupListView(dataModelArrayList);
							// eksternalListJadwalView.onSuccessMessage("Pilih Jadwal Prove!");
						} else {
							dataModelArrayList = new ArrayList<>();
							eksternalListJadwalView.onSetupListView(dataModelArrayList);
							eksternalListJadwalView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalListJadwalView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalListJadwalView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_internal", id_internal);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
