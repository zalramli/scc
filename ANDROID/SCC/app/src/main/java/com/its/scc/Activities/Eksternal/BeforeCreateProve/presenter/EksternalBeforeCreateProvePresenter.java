package com.its.scc.Activities.Eksternal.BeforeCreateProve.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.BeforeCreateProve.view.IEksternalBeforeCreateProveView;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EksternalBeforeCreateProvePresenter implements IEksternalBeforeCreateProvePresenter {

	Context context;
	IEksternalBeforeCreateProveView eksternalBeforeCreateProveView;

	BaseUrl baseUrl;

	public EksternalBeforeCreateProvePresenter(Context context, IEksternalBeforeCreateProveView eksternalBeforeCreateProveView) {
		this.context = context;
		this.eksternalBeforeCreateProveView = eksternalBeforeCreateProveView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String id_eksternal, String id_internal, String id_materi_prove, String id_jadwal_prove, String deskripsi_materi, String tanggal_prove) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/prove/tambah_prove"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalBeforeCreateProveView.onSuccessMessage(message);
							eksternalBeforeCreateProveView.backPressed();
						} else {
							eksternalBeforeCreateProveView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalBeforeCreateProveView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalBeforeCreateProveView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_eksternal", id_eksternal);
				params.put("id_internal", id_internal);
				params.put("id_materi_prove", id_materi_prove);
				params.put("id_jadwal_prove", id_jadwal_prove);
				params.put("deskripsi_materi", deskripsi_materi);
				params.put("tanggal_prove", tanggal_prove);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
