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
import com.its.scc.Controllers.SessionManager;
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

	SessionManager sessionManager;

	public InternalDetailAbsensiPresenter(Context context, IInternalDetailAbsensiView internalDetailAbsensiView) {
		this.context = context;
		this.internalDetailAbsensiView = internalDetailAbsensiView;

		baseUrl = new BaseUrl();

		sessionManager = new SessionManager(context);
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
						String id_internal_akses = obj.getString("id_internal_akses");

						HashMap<String, String> user = sessionManager.getDataUser();
						String id_internal_session = user.get(sessionManager.ID_USER);

						if (id_internal_akses.equals(id_internal_session)) {
							internalDetailAbsensiView.showAkses();
						}

						if (success.equals("1")) {

							dataModelArrayList = new ArrayList<>();
							JSONArray dataArray = obj.getJSONArray("list_anggota_absensi");
							for (int i = 0; i < dataArray.length(); i++) {

								Internal playerModel = new Internal();
								JSONObject dataobj = dataArray.getJSONObject(i);

								String id_detail_absensi = dataobj.getString("id_detail_absensi");
								String id_absensi = dataobj.getString("id_absensi");
								String id_internal = dataobj.getString("id_internal");
								String tgl_absen = dataobj.getString("tgl_absen");
								String nama = dataobj.getString("nama");
								String no_hp = dataobj.getString("no_hp");
								String akun_line = dataobj.getString("akun_line");
								String username = dataobj.getString("username");
								String hak_akses = dataobj.getString("hak_akses");
								String jabatan_managerial = dataobj.getString("jabatan_managerial");
								String status_sj = dataobj.getString("status_sj");
								String foto = dataobj.getString("foto");

								playerModel.setId_detail_absensi(id_detail_absensi);
								playerModel.setId_absensi(id_absensi);
								playerModel.setId_internal(id_internal);
								playerModel.setTgl_absen(tgl_absen);
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

	@Override
	public void onHapus(String id_absensi) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/absensi/hapus_absensi"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject obj = new JSONObject(response);
						String success = obj.getString("success");
						String message = obj.getString("message");

						if (success.equals("1")) {
							internalDetailAbsensiView.onSuccessMessage(message);
							internalDetailAbsensiView.backPressed();
						} else {
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
