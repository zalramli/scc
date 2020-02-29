package com.its.scc.Activities.Internal.CreateAbsensi.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Internal.CreateAbsensi.view.IInternalCreateAbsensiView;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InternalCreateAbsensiPresenter implements IInternalCreateAbsensiPresenter {

	Context context;
	IInternalCreateAbsensiView internalCreateAbsensiView;

	BaseUrl baseUrl;

	public InternalCreateAbsensiPresenter(Context context, IInternalCreateAbsensiView internalCreateAbsensiView) {
		this.context = context;
		this.internalCreateAbsensiView = internalCreateAbsensiView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String id_internal, String judul_absensi, String tgl_absensi, String jam_mulai, String jam_selesai) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/absensi/tambah_absensi"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							internalCreateAbsensiView.onSuccessMessage(message);
							internalCreateAbsensiView.backPressed();
						} else {
							internalCreateAbsensiView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						internalCreateAbsensiView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					internalCreateAbsensiView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_internal", id_internal);
				params.put("judul_absensi", judul_absensi);
				params.put("tgl_absensi", tgl_absensi);
				params.put("jam_mulai", jam_mulai);
				params.put("jam_selesai", jam_selesai);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
