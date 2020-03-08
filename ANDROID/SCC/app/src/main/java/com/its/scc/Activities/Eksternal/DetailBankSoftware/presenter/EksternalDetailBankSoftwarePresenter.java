package com.its.scc.Activities.Eksternal.DetailBankSoftware.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.DetailBankSoftware.view.IEksternalDetailBankSoftwareView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Software;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EksternalDetailBankSoftwarePresenter implements IEksternalDetailBankSoftwarePresenter {

	Context context;
	IEksternalDetailBankSoftwareView eksternalDetailBankSoftwareView;

	BaseUrl baseUrl;

	ArrayList<Software> dataModelArrayList;

	public EksternalDetailBankSoftwarePresenter(Context context, IEksternalDetailBankSoftwareView eksternalDetailBankSoftwareView) {
		this.context = context;
		this.eksternalDetailBankSoftwareView = eksternalDetailBankSoftwareView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData(String kode_bank_s) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/software/list_software_by_kode_bank_s"; // url http request

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
							JSONArray dataArray = obj.getJSONArray("list_software");
							for (int i = 0; i < dataArray.length(); i++) {

								Software playerModel = new Software();
								JSONObject dataobj = dataArray.getJSONObject(i);

								int id = 0;
								String id_software = dataobj.getString("id_software");
								String nama = dataobj.getString("nama");

								playerModel.setId(id);
								playerModel.setId_software(id_software);
								playerModel.setNama(nama);

								dataModelArrayList.add(playerModel);
							}

							eksternalDetailBankSoftwareView.onSetupListView(dataModelArrayList);
							// eksternalDetailBankSoftwareView.onSuccessMessage("Pilih Salah Satu Materi Prove !");
						} else {
							dataModelArrayList = new ArrayList<>();
							eksternalDetailBankSoftwareView.onSetupListView(dataModelArrayList);
							eksternalDetailBankSoftwareView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalDetailBankSoftwareView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalDetailBankSoftwareView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("kode_bank_s", kode_bank_s);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onChangeRating(String kode_bank_s, String rating) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/bank_software/on_update_rating"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalDetailBankSoftwareView.onSuccessMessage(message);
							eksternalDetailBankSoftwareView.backPressed();
						} else {
							eksternalDetailBankSoftwareView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalDetailBankSoftwareView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalDetailBankSoftwareView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("kode_bank_s", kode_bank_s);
				params.put("rating", rating);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onHapus(String kode_bank_s) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/bank_software/on_hapus"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {

						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalDetailBankSoftwareView.onSuccessMessage(message);
							eksternalDetailBankSoftwareView.backPressed();
						} else {
							eksternalDetailBankSoftwareView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalDetailBankSoftwareView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalDetailBankSoftwareView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("kode_bank_s", kode_bank_s);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onSelesai(String kode_bank_s) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/bank_software/on_selesai"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {

						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalDetailBankSoftwareView.onSuccessMessage(message);
							eksternalDetailBankSoftwareView.backPressed();
						} else {
							eksternalDetailBankSoftwareView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalDetailBankSoftwareView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalDetailBankSoftwareView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("kode_bank_s", kode_bank_s);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
