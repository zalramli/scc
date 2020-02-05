package com.its.scc.Activities.Eksternal.MasukKelasStep1.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.MasukKelasStep1.EksternalMasukKelasStep1Activity;
import com.its.scc.Activities.Eksternal.MasukKelasStep1.view.IEksternalMasukKelasStep1View;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EksternalMasukKelasStep1Presenter implements IEksternalMasukKelasStep1Presenter {

	Context context;
	IEksternalMasukKelasStep1View eksternalMasukKelasStep1View;

	BaseUrl baseUrl;

	public EksternalMasukKelasStep1Presenter(Context context, IEksternalMasukKelasStep1View eksternalMasukKelasStep1View) {
		this.context = context;
		this.eksternalMasukKelasStep1View = eksternalMasukKelasStep1View;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String kode_prove, String id_eksternal) {

		eksternalMasukKelasStep1View.onSuccessMessage(kode_prove);

		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/prove/tambah_detail_prove"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {

							eksternalMasukKelasStep1View.onSuccessMessage(message);
							eksternalMasukKelasStep1View.keHalamanLain();

						} else {
							eksternalMasukKelasStep1View.onSuccessMessage(message);
							eksternalMasukKelasStep1View.keHalamanLain();
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalMasukKelasStep1View.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalMasukKelasStep1View.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("kode_prove", kode_prove);
				params.put("id_eksternal", id_eksternal);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
