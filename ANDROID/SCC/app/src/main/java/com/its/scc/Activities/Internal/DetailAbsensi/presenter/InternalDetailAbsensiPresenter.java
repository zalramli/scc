package com.its.scc.Activities.Internal.DetailAbsensi.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Internal.DetailAbsensi.view.IInternalDetailAbsensiView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Internal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InternalDetailAbsensiPresenter implements IInternalDetailAbsensiPresenter {

	Context context;
	IInternalDetailAbsensiView internalDetailAbsensiView;

	BaseUrl baseUrl;

	ArrayList<Internal> dataModelArrayList;

	public InternalDetailAbsensiPresenter(Context context, IInternalDetailAbsensiView internalDetailAbsensiView) {
		this.context = context;
		this.internalDetailAbsensiView = internalDetailAbsensiView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData(String id_absensi) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/absensi/list_detail_absensi"; // url http request

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
							JSONArray dataArray = obj.getJSONArray("internal");
							for (int i = 0; i < dataArray.length(); i++) {

								Internal playerModel = new Internal();
								JSONObject dataobj = dataArray.getJSONObject(i);

								String id_internal = dataobj.getString("id_internal");

								playerModel.setId_internal(id_internal);

								dataModelArrayList.add(playerModel);
							}

							internalDetailAbsensiView.onSetupListView(dataModelArrayList);

						} else {
							
							dataModelArrayList = new ArrayList<>();
							internalDetailAbsensiView.onSetupListView(dataModelArrayList);
							internalDetailAbsensiView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						internalDetailAbsensiView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					internalDetailAbsensiView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_absensi", id_absensi);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
