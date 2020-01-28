package com.its.scc.Activities._Pendaftaran.presenter;

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
import com.its.scc.Activities._Pendaftaran.view.IPendaftaranView;
import com.its.scc.Controllers.BaseUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class PendaftaranPresenter implements IPendaftaranPresenter {


	Context context;
	IPendaftaranView pendaftaranView;

	BaseUrl baseUrl;

	public PendaftaranPresenter(Context context, IPendaftaranView pendaftaranView) {
		this.context = context;
		this.pendaftaranView = pendaftaranView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void onSubmit(String nama, String no_hp, String akun_line, String username, String password, String angkatan, String foto) {

		String base_url = baseUrl.getUrlData();
		String URL_DATA = base_url + "pendaftaran/eksternal"; // url http request

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
			new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try {
						JSONObject jsonObject = new JSONObject(response);
						String success = jsonObject.getString("success");
						String message = jsonObject.getString("message");

						if (success.equals("1")) {
							pendaftaranView.onSuccessMessage(message);
							pendaftaranView.backPressed();
						} else {
							pendaftaranView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						pendaftaranView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					pendaftaranView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
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
