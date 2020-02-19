package com.its.scc.Activities.Eksternal.ListSoftware.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.ListSoftware.view.IEksternalListSoftwareView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Software;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EksternalListSoftwarePresenter implements IEksternalListSoftwarePresenter {

	Context context;
	IEksternalListSoftwareView eksternalListSoftwareView;

	BaseUrl baseUrl;

	ArrayList<Software> dataModelArrayList;

	public EksternalListSoftwarePresenter(Context context, IEksternalListSoftwareView eksternalListSoftwareView) {
		this.context = context;
		this.eksternalListSoftwareView = eksternalListSoftwareView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData() {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/software/list_software"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {

					JSONObject obj = new JSONObject(response);
					String success = obj.getString("success");
					String message = obj.getString("message");

					if (success.equals("1")) {

						dataModelArrayList = new ArrayList<>();
						JSONArray dataArray = obj.getJSONArray("software");
						for (int i = 0; i < dataArray.length(); i++) {

							Software playerModel = new Software();
							JSONObject dataobj = dataArray.getJSONObject(i);

							String id_software = dataobj.getString("id_software");
							String nama = dataobj.getString("nama");

							playerModel.setId_software(id_software);
							playerModel.setNama(nama);

							dataModelArrayList.add(playerModel);
						}

						eksternalListSoftwareView.onSetupListView(dataModelArrayList);
						// eksternalListSoftwareView.onSuccessMessage("Pilih Salah Satu Materi Prove !");
					} else {
						dataModelArrayList = new ArrayList<>();
						eksternalListSoftwareView.onSetupListView(dataModelArrayList);
						eksternalListSoftwareView.onErrorMessage(message);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					eksternalListSoftwareView.onErrorMessage("Gagal Menerima Data : " + e.toString());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				eksternalListSoftwareView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
