package com.its.scc.Activities.Eksternal.ListMateri.presenter;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.ListMateri.view.IEksternalListMateriView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.MateriProve;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EksternalListMateriPresenter implements IEksternalListMateriPresenter {

	Context context;
	IEksternalListMateriView eksternalListMateriView;

	BaseUrl baseUrl;

	ArrayList<MateriProve> dataModelArrayList;

	public EksternalListMateriPresenter(Context context, IEksternalListMateriView eksternalListMateriView) {
		this.context = context;
		this.eksternalListMateriView = eksternalListMateriView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData() {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/materi_prove/list_materi_prove"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try {

					JSONObject obj = new JSONObject(response);
					String success = obj.getString("success");
					String message = obj.getString("message");

					if (success.equals("1")) {

						dataModelArrayList = new ArrayList<>();
						JSONArray dataArray = obj.getJSONArray("materi_prove");
						for (int i = 0; i < dataArray.length(); i++) {

							MateriProve playerModel = new MateriProve();
							JSONObject dataobj = dataArray.getJSONObject(i);

							String id_materi_prove = dataobj.getString("id_materi_prove");
							String nama = dataobj.getString("nama");

							playerModel.setId_materi_prove(id_materi_prove);
							playerModel.setNama(nama);

							dataModelArrayList.add(playerModel);
						}

						eksternalListMateriView.onSetupListView(dataModelArrayList);
						// eksternalListMateriView.onSuccessMessage("Pilih Salah Satu Materi Prove !");
					} else {
						dataModelArrayList = new ArrayList<>();
						eksternalListMateriView.onSetupListView(dataModelArrayList);
						eksternalListMateriView.onErrorMessage(message);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					eksternalListMateriView.onErrorMessage("Gagal Menerima Data : " + e.toString());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				eksternalListMateriView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
			}
		});

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
