package com.its.scc.Activities.Internal.AkunEdit.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Internal.AkunEdit.view.IInternalAkunEditView;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class InternalAkunEditPresenter implements IInternalAkunEditPresenter {

	Context context;
	IInternalAkunEditView internalAkunEditView;

	BaseUrl baseUrl;

	public InternalAkunEditPresenter(Context context, IInternalAkunEditView internalAkunEditView) {
		this.context = context;
		this.internalAkunEditView = internalAkunEditView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void inisiasiAwal(String id_internal) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/akun/ambil_data_internal"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						JSONArray jsonArray = jsonObject.getJSONArray("internal");

						if (success.equals("1")) {
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject object = jsonArray.getJSONObject(i);

								String nama = object.getString("nama").trim();
								String no_hp = object.getString("no_hp").trim();
								String akun_line = object.getString("akun_line").trim();
								String username = object.getString("username").trim();
								String hak_akses = object.getString("hak_akses").trim();
								String jabatan_managerial = object.getString("jabatan_managerial").trim();
								String status_sj = object.getString("status_sj").trim();
								String foto = object.getString("foto").trim();

								String alamat_foto = baseUrl.getUrlUpload() + "image/internal/" + foto + ".jpg";

								internalAkunEditView.setNilaiDefault(nama, no_hp, akun_line, username, hak_akses, jabatan_managerial, status_sj, alamat_foto);
							}
						} else {
							internalAkunEditView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						internalAkunEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					internalAkunEditView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_internal", id_internal);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onUpdate(String id_internal, String nama, String no_hp, String akun_line, String username, String password, String hak_akses, String jabatan_managerial, String status_sj, String foto) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "internal/akun/update_internal"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							internalAkunEditView.onSuccessMessage(message);
							internalAkunEditView.backPressed();
						} else {
							internalAkunEditView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						internalAkunEditView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					internalAkunEditView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_internal", id_internal);
				params.put("nama", nama);
				params.put("no_hp", no_hp);
				params.put("akun_line", akun_line);
				params.put("username", username);
				params.put("password", password);
				params.put("hak_akses", hak_akses);
				params.put("jabatan_managerial", jabatan_managerial);
				params.put("status_sj", status_sj);
				params.put("foto", foto);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public String getStringImage(Bitmap bitmap) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

		byte[] imageByteArray = byteArrayOutputStream.toByteArray();
		String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

		return encodedImage;
	}
}
