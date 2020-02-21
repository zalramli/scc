package com.its.scc.Activities.Eksternal.BeforeOrderBS.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.BeforeOrderBS.view.IEksternalBeforeOrderBSView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.DB.DBConstants;
import com.its.scc.DB.DatabaseHelper;
import com.its.scc.Models.Software;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EksternalBeforeOrderBSPresenter implements IEksternalBeforeOrderBSPresenter {

	Context context;
	IEksternalBeforeOrderBSView eksternalBeforeOrderBSView;

	ArrayList<Software> dataModelArrayList;

	BaseUrl baseUrl;
	DatabaseHelper databaseHelper;

	public EksternalBeforeOrderBSPresenter(Context context, IEksternalBeforeOrderBSView eksternalBeforeOrderBSView) {
		this.context = context;
		this.eksternalBeforeOrderBSView = eksternalBeforeOrderBSView;

		baseUrl = new BaseUrl();
		databaseHelper = new DatabaseHelper(context);
	}

	@Override
	public void onSubmit(String id_eksternal, String id_jadwal_bs, String tanggal_bs) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/bank_software/tambah_bank_software"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalBeforeOrderBSView.onSuccessMessage(message);
							eksternalBeforeOrderBSView.backPressed();
						} else {
							eksternalBeforeOrderBSView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalBeforeOrderBSView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalBeforeOrderBSView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_eksternal", id_eksternal);
				params.put("id_jadwal_bs", id_jadwal_bs);
				params.put("tanggal_bs", tanggal_bs);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onSubmitDetail(String kode_bank_s, String id_software) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/bank_software/tambah_detail_bank_software"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
//						String success = jsonObject.getString("success");
//						String message = jsonObject.getString("message");
//
//						if (success.equals("1")) {
//							eksternalBeforeOrderBSView.onSuccessMessage(message);
//							eksternalBeforeOrderBSView.backPressed();
//						} else {
//							eksternalBeforeOrderBSView.onErrorMessage(message);
//						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalBeforeOrderBSView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalBeforeOrderBSView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("kode_bank_s", kode_bank_s);
				params.put("id_software", id_software);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onLoadSemuaData() {
		dataModelArrayList = databaseHelper.getAllData(DBConstants.C_ID + " ASC");
		eksternalBeforeOrderBSView.onSetupListView(dataModelArrayList);
		if (dataModelArrayList.size() < 1) {
			eksternalBeforeOrderBSView.onErrorMessage("Detail List Software Kosong !");
		}
	}

	@Override
	public void onDelete(String id) {
		databaseHelper.deleteInfo(id);
	}
}
