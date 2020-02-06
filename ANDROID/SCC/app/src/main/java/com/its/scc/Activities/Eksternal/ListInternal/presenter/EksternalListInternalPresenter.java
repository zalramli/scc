package com.its.scc.Activities.Eksternal.ListInternal.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.ListInternal.view.IEksternalListInternalView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.Internal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EksternalListInternalPresenter implements IEksternalListInternalPresenter {

	Context context;
	IEksternalListInternalView eksternalListInternalView;

	BaseUrl baseUrl;

	ArrayList<Internal> dataModelArrayList;

	public EksternalListInternalPresenter(Context context, IEksternalListInternalView eksternalListInternalView) {
		this.context = context;
		this.eksternalListInternalView = eksternalListInternalView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void inisiasiAwal(String id_materi_prove) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/internal/list_internal"; // url http request

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
								String nama = dataobj.getString("nama");
								String no_hp = dataobj.getString("no_hp");
								String akun_line = dataobj.getString("akun_line");
								String username = dataobj.getString("username");
								String hak_akses = dataobj.getString("hak_akses");
								String jabatan_managerial = dataobj.getString("jabatan_managerial");
								String status_sj = dataobj.getString("status_sj");
								String foto = dataobj.getString("foto");

								playerModel.setId_internal(id_internal);
								playerModel.setNama(nama);
								playerModel.setNo_hp(no_hp);
								playerModel.setAkun_line(akun_line);
								playerModel.setUsername(username);
								playerModel.setHak_akses(hak_akses);
								playerModel.setJabatan_managerial(jabatan_managerial);
								playerModel.setStatus_sj(status_sj);
								playerModel.setFoto(foto);

								dataModelArrayList.add(playerModel);
							}

							eksternalListInternalView.onSetupListView(dataModelArrayList);
							// eksternalListInternalView.onSuccessMessage("Pilih Salah Satu pembimbing Materi!");
						} else {
							dataModelArrayList = new ArrayList<>();
							eksternalListInternalView.onSetupListView(dataModelArrayList);
							eksternalListInternalView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalListInternalView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalListInternalView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_materi_prove", id_materi_prove);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
