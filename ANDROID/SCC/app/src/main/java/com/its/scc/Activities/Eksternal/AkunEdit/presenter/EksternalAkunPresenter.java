package com.its.scc.Activities.Eksternal.AkunEdit.presenter;

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
import com.its.scc.Activities.Eksternal.AkunEdit.view.IEksternalAkunView;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class EksternalAkunPresenter implements IEksternalAkunPresenter {

	Context context;
	IEksternalAkunView eksternalAkunView;

	BaseUrl baseUrl;

	public EksternalAkunPresenter(Context context, IEksternalAkunView eksternalAkunView) {
		this.context = context;
		this.eksternalAkunView = eksternalAkunView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void inisiasiAwal(String id_eksternal) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/akun/ambil_data_eksternal"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						JSONArray jsonArray = jsonObject.getJSONArray("eksternal");

						if (success.equals("1")) {
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject object = jsonArray.getJSONObject(i);

								String nama = object.getString("nama").trim();
								String no_hp = object.getString("no_hp").trim();
								String akun_line = object.getString("akun_line").trim();
								String username = object.getString("username").trim();
								String angkatan = object.getString("angkatan").trim();
								String foto = object.getString("foto").trim();

								String alamat_foto = baseUrl.getUrlUpload() + "image/eksternal/" + foto + ".jpg";

								eksternalAkunView.setNilaiDefault(nama, no_hp, akun_line, username, angkatan, alamat_foto);
							}
						} else {
							eksternalAkunView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalAkunView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalAkunView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_eksternal", id_eksternal);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}

	@Override
	public void onUpdate(String id_eksternal, String nama, String no_hp, String akun_line, String username, String password, String angkatan, String foto) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "eksternal/akun/update_eksternal"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							eksternalAkunView.onSuccessMessage(message);
							eksternalAkunView.backPressed();
						} else {
							eksternalAkunView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalAkunView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalAkunView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id_eksternal", id_eksternal);
				params.put("nama", nama);
				params.put("no_hp", no_hp);
				params.put("akun_line", akun_line);
				params.put("username", username);
				params.put("password", password);
				params.put("angkatan", angkatan);
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
