package com.its.scc.Activities.Eksternal._Home.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal._Home.view.IEksternalHomeView;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EksternalHomePresenter implements IEksternalHomePresenter {

	Context context;
	IEksternalHomeView eksternalHomeView;

	BaseUrl baseUrl;

	public EksternalHomePresenter(Context context, IEksternalHomeView eksternalHomeView) {
		this.context = context;
		this.eksternalHomeView = eksternalHomeView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String kode_prove) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/prove/cek_kode_prove"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalHomeView.onSuccessMessage(message);
						} else {
							eksternalHomeView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalHomeView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalHomeView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("kode_prove", kode_prove);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
