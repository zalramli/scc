package com.its.scc.Activities.Internal.ListAbsensi.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Internal.ListAbsensi.view.IInternalListAbsensiView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Absensi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InternalListAbsensiPresenter implements IInternalListAbsensiPresenter {

	Context context;
	IInternalListAbsensiView internalListAbsensiView;

	BaseUrl baseUrl;

	ArrayList<Absensi> dataModelArrayList;

	public InternalListAbsensiPresenter(Context context, IInternalListAbsensiView internalListAbsensiView) {
		this.context = context;
		this.internalListAbsensiView = internalListAbsensiView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData() {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/absensi/list_absensi"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {

					JSONObject obj = new JSONObject(response);
					String success = obj.getString("success");
					String message = obj.getString("message");

					if (success.equals("1")) {

						dataModelArrayList = new ArrayList<>();
						JSONArray dataArray = obj.getJSONArray("absensi");
						for (int i = 0; i < dataArray.length(); i++) {

							Absensi playerModel = new Absensi();
							JSONObject dataobj = dataArray.getJSONObject(i);

							String id_absensi = dataobj.getString("id_absensi");
							String id_internal = dataobj.getString("id_internal");
							String judul_absensi = dataobj.getString("judul_absensi");
							String tgl_absensi = dataobj.getString("tgl_absensi");
							String jam_mulai = dataobj.getString("jam_mulai");
							String jam_selesai = dataobj.getString("jam_selesai");
							String status_absensi = dataobj.getString("status_absensi");
							String kata_sandi = dataobj.getString("kata_sandi");

							playerModel.setId_absensi(id_absensi);
							playerModel.setId_internal(id_internal);
							playerModel.setJudul_absensi(judul_absensi);
							playerModel.setTgl_absensi(tgl_absensi);
							playerModel.setJam_mulai(jam_mulai);
							playerModel.setJam_selesai(jam_selesai);
							playerModel.setStatus_absensi(status_absensi);
							playerModel.setKata_sandi(kata_sandi);

							dataModelArrayList.add(playerModel);
						}

						internalListAbsensiView.onSetupListView(dataModelArrayList);
					} else {
						dataModelArrayList = new ArrayList<>();
						internalListAbsensiView.onSetupListView(dataModelArrayList);
						internalListAbsensiView.onErrorMessage(message);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					internalListAbsensiView.onErrorMessage("Gagal Menerima Data : " + e.toString());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				internalListAbsensiView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public String cekAbsen(String id_absensi, String id_internal) {
		String cek_absen = "";

		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/absensi/cek_absen"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						String cek = jsonObject.getString("cek_absen");

						if (success.equals("1")) {

						} else {
							internalListAbsensiView.onErrorMessage(message);
						}

						if (cek.equals("Sudah")){
							
						}

					} catch (JSONException e) {
						e.printStackTrace();
						internalListAbsensiView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					internalListAbsensiView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_absensi", id_absensi);
				params.put("id_internal", id_internal);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);

		return cek_absen;
	}
}
