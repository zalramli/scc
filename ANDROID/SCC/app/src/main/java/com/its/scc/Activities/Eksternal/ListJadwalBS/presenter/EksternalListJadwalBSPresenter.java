package com.its.scc.Activities.Eksternal.ListJadwalBS.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.ListJadwalBS.view.IEksternalListJadwalBSView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.JadwalBS;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EksternalListJadwalBSPresenter implements IEksternalListJadwalBSPresenter {

	Context context;
	IEksternalListJadwalBSView eksternalListJadwalView;

	BaseUrl baseUrl;
	ArrayList<JadwalBS> dataModelArrayList;

	public EksternalListJadwalBSPresenter(Context context, IEksternalListJadwalBSView eksternalListJadwalView) {
		this.context = context;
		this.eksternalListJadwalView = eksternalListJadwalView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData() {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/jadwal_bs/list_jadwal_bs"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {

					JSONObject obj = new JSONObject(response);
					String success = obj.getString("success");
					String message = obj.getString("message");

					if (success.equals("1")) {

						dataModelArrayList = new ArrayList<>();
						JSONArray dataArray = obj.getJSONArray("jadwal_bs");
						for (int i = 0; i < dataArray.length(); i++) {

							JadwalBS playerModel = new JadwalBS();
							JSONObject dataobj = dataArray.getJSONObject(i);

							String id_jadwal_bs = dataobj.getString("id_jadwal_bs");
							String id_internal = dataobj.getString("id_internal");
							String hari = dataobj.getString("hari");
							String jam_mulai = dataobj.getString("jam_mulai");
							String jam_selesai = dataobj.getString("jam_selesai");
							String status_booking = dataobj.getString("status_booking");
							String status_aktif = dataobj.getString("status_aktif");

							playerModel.setId_jadwal_bs(id_jadwal_bs);
							playerModel.setId_internal(id_internal);
							playerModel.setHari(hari);
							playerModel.setJam_mulai(jam_mulai);
							playerModel.setJam_selesai(jam_selesai);
							playerModel.setStatus_booking(status_booking);
							playerModel.setStatus_aktif(status_aktif);

							dataModelArrayList.add(playerModel);
						}

						eksternalListJadwalView.onSetupListView(dataModelArrayList);
						// eksternalListJadwalView.onSuccessMessage("Pilih Salah Satu Materi Prove !");
					} else {
						dataModelArrayList = new ArrayList<>();
						eksternalListJadwalView.onSetupListView(dataModelArrayList);
						eksternalListJadwalView.onErrorMessage(message);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					eksternalListJadwalView.onErrorMessage("Gagal Menerima Data : " + e.toString());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				eksternalListJadwalView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
