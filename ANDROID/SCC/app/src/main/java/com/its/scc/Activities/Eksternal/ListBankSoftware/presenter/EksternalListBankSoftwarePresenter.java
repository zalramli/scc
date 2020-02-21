package com.its.scc.Activities.Eksternal.ListBankSoftware.presenter;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.its.scc.Activities.Eksternal.ListBankSoftware.view.IEksternalListBankSoftwareView;
import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Models.BankSoftware;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EksternalListBankSoftwarePresenter implements IEksternalListBankSoftwarePresenter {

	Context context;
	IEksternalListBankSoftwareView eksternalListBankSoftwareView;

	BaseUrl baseUrl;

	ArrayList<BankSoftware> dataModelArrayList;

	public EksternalListBankSoftwarePresenter(Context context, IEksternalListBankSoftwareView eksternalListBankSoftwareView) {
		this.context = context;
		this.eksternalListBankSoftwareView = eksternalListBankSoftwareView;

		baseUrl = new BaseUrl();
	}

	@Override
	public void inisiasiAwal(String id, String hakAkses, String tujuan) {
		String base_url = baseUrl.getUrlData();
		String URL_DATA = "";

		if (tujuan.equals("ke_pemberitahuan")) {
			URL_DATA = base_url + "eksternal/bank_software/list_bank_software_hanya_belum_selesai"; // url http request
		} else {
			URL_DATA = base_url + "eksternal/bank_software/list_bank_software"; // url http request
		}

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
							JSONArray dataArray = obj.getJSONArray("list_bank_software");
							for (int i = 0; i < dataArray.length(); i++) {

								BankSoftware playerModel = new BankSoftware();
								JSONObject dataobj = dataArray.getJSONObject(i);

								String kode_bank_s = dataobj.getString("kode_bank_s");
								String id_eksternal = dataobj.getString("id_eksternal");
								String nama = dataobj.getString("nama");
								String no_hp = dataobj.getString("no_hp");
								String akun_line = dataobj.getString("akun_line");
								String angkatan = dataobj.getString("angkatan");
								String foto = dataobj.getString("foto");
								String id_jadwal_bs = dataobj.getString("id_jadwal_bs");
								String hari = dataobj.getString("hari");
								String jam_mulai = dataobj.getString("jam_mulai");
								String jam_selesai = dataobj.getString("jam_selesai");
								String tanggal_booking = dataobj.getString("tanggal_booking");
								String tanggal_bs = dataobj.getString("tanggal_bs");
								String status_bs = dataobj.getString("status_bs");

								playerModel.setKode_bank_s(kode_bank_s);
								playerModel.setId_eksternal(id_eksternal);
								playerModel.setNama(nama);
								playerModel.setNo_hp(no_hp);
								playerModel.setAkun_line(akun_line);
								playerModel.setAngkatan(angkatan);
								playerModel.setFoto(foto);
								playerModel.setId_jadwal_bs(id_jadwal_bs);
								playerModel.setHari(hari);
								playerModel.setJam_mulai(jam_mulai);
								playerModel.setJam_selesai(jam_selesai);
								playerModel.setTanggal_booking(tanggal_booking);
								playerModel.setTanggal_bs(tanggal_bs);
								playerModel.setStatus_bs(status_bs);

								dataModelArrayList.add(playerModel);
							}

							eksternalListBankSoftwareView.onSetupListView(dataModelArrayList);

						} else {
							dataModelArrayList = new ArrayList<>();
							eksternalListBankSoftwareView.onSetupListView(dataModelArrayList);
							eksternalListBankSoftwareView.onErrorMessage(message);
						}

					} catch (JSONException e) {
						e.printStackTrace();
						eksternalListBankSoftwareView.onErrorMessage("Kesalahan Menerima Data : " + e.toString());
					}
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					eksternalListBankSoftwareView.onErrorMessage("Tidak Ada Koneksi Ke Server !, Periksa Kembali Koneksi Anda");
				}
			}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				params.put("hak_akses", hakAkses);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(context);
		requestQueue.add(stringRequest);
	}
}
