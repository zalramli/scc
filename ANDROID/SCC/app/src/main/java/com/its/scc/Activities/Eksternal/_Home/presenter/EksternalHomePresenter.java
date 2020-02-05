package com.its.scc.Activities.Eksternal._Home.presenter;

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
import com.its.scc.Activities.Eksternal._Home.view.IEksternalHomeView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Prove;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

							String kata_sandi = "";

							JSONArray dataArray = jsonObject.getJSONArray("prove");
							for (int i = 0; i < dataArray.length(); i++) {

								JSONObject dataobj = dataArray.getJSONObject(i);

								kata_sandi = dataobj.getString("kata_sandi");
							}

							eksternalHomeView.onSuccessMessage(kata_sandi);

							Intent intent = new Intent();
							intent = new Intent(context, EksternalMasukKelasStep1Activity.class);
							intent.putExtra(EksternalMasukKelasStep1Activity.EXTRA_KODE_PROVE, kode_prove);
							intent.putExtra(EksternalMasukKelasStep1Activity.EXTRA_KATA_SANDI, kata_sandi);
							context.startActivity(intent);
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
