package com.its.scc.Activities.Eksternal.DetailProve.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.DetailProve.view.IEksternalDetailProveView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Eksternal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EksternalDetailProvePresenter implements IEksternalDetailProvePresenter {

	Context context;
	IEksternalDetailProveView eksternalDetailProveView;

	BaseUrl baseUrl;

	ArrayList<Eksternal> dataModelArrayList;

	public EksternalDetailProvePresenter(Context context, IEksternalDetailProveView eksternalDetailProveView) {
		this.context = context;
		this.eksternalDetailProveView = eksternalDetailProveView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onLoadSemuaData(String id_prove) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/eksternal/list_eksternal"; // url http request

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
							JSONArray dataArray = obj.getJSONArray("eksternal");
							for (int i = 0; i < dataArray.length(); i++) {

								Eksternal playerModel = new Eksternal();
								JSONObject dataobj = dataArray.getJSONObject(i);

								String id_eksternal = dataobj.getString("id_eksternal");
								String nama = dataobj.getString("nama");
								String no_hp = dataobj.getString("no_hp");
								String akun_line = dataobj.getString("akun_line");
								String username = dataobj.getString("username");
								String angkatan = dataobj.getString("angkatan");
								String foto = dataobj.getString("foto");

								playerModel.setId_eksternal(id_eksternal);
								playerModel.setNama(nama);
								playerModel.setNo_hp(no_hp);
								playerModel.setAkun_line(akun_line);
								playerModel.setUsername(username);
								playerModel.setAngkatan(angkatan);
								playerModel.setFoto(foto);

								dataModelArrayList.add(playerModel);
							}
							eksternalDetailProveView.onSetupListView(dataModelArrayList);

						} else {
							dataModelArrayList = new ArrayList<>();
							eksternalDetailProveView.onSetupListView(dataModelArrayList);
							eksternalDetailProveView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalDetailProveView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalDetailProveView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_prove", id_prove);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onKeluarProve(String id_prove, String id_eksternal) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/prove/delete_detail_prove"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalDetailProveView.onSuccessMessage(message);
							eksternalDetailProveView.backPressed();
						} else {
							eksternalDetailProveView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalDetailProveView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalDetailProveView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_prove", id_prove);
				params.put("id_eksternal", id_eksternal);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onChangeRating(String id_prove, String id_eksternal, String rating, String id_jadwal_prove) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/prove/update_detail_prove"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalDetailProveView.backPressed();
						} else {
							eksternalDetailProveView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalDetailProveView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalDetailProveView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_prove", id_prove);
				params.put("id_eksternal", id_eksternal);
				params.put("rating", rating);
				params.put("id_jadwal_prove", id_jadwal_prove);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
