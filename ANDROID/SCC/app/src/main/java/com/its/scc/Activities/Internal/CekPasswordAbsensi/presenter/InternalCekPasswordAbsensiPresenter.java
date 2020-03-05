package com.its.scc.Activities.Internal.CekPasswordAbsensi.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Internal.CekPasswordAbsensi.view.IInternalCekPasswordAbsensiView;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InternalCekPasswordAbsensiPresenter implements IInternalCekPasswordAbsensiPresenter {

	Context context;
	IInternalCekPasswordAbsensiView internalCekPasswordAbsensiView;

	BaseUrl baseUrl;

	public InternalCekPasswordAbsensiPresenter(Context context, IInternalCekPasswordAbsensiView internalCekPasswordAbsensiView) {
		this.context = context;
		this.internalCekPasswordAbsensiView = internalCekPasswordAbsensiView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String id_absensi, String id_internal) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/absensi/masuk_absensi"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							internalCekPasswordAbsensiView.onSuccessMessage(message);
							internalCekPasswordAbsensiView.keHalamanLain();
						} else {
							internalCekPasswordAbsensiView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						internalCekPasswordAbsensiView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					internalCekPasswordAbsensiView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
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
	}
}
