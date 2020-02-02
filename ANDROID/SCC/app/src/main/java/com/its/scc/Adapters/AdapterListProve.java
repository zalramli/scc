package com.its.scc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Prove;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListProve extends RecyclerView.Adapter<AdapterListProve.ListProveViewHolder> {

	Context context;
	ArrayList<Prove> dataModelArrayList;

	SessionManager sessionManager;
	BaseUrl baseUrl;
	private static ClickListener clickListener;

	public AdapterListProve(Context context, ArrayList<Prove> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;

		sessionManager = new SessionManager(context);
		baseUrl = new BaseUrl();
	}

	@NonNull
	@Override
	public AdapterListProve.ListProveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_prove, parent, false);
		return new ListProveViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListProve.ListProveViewHolder holder, int position) {

		String hari_jadwal = dataModelArrayList.get(position).getHari();
		String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
		String jam_selesai = dataModelArrayList.get(position).getJam_selesai();
		String tanggal_prove = dataModelArrayList.get(position).getTanggal_prove();
		String kode_prove = dataModelArrayList.get(position).getKode_prove();
		String kata_sandi = dataModelArrayList.get(position).getKata_sandi();
		String nama_internal = dataModelArrayList.get(position).getNama_internal();
		String status_prove = dataModelArrayList.get(position).getStatus_prove();

		holder.tvNamaMateri.setText(dataModelArrayList.get(position).getNama_materi_prove());
		holder.tvDetailJadwal.setText("Jadwal : " + hari_jadwal + " ( " + jam_mulai + " - " + jam_selesai + " )");
		holder.tvTanggalProve.setText("Tanggal Prove : " + tanggal_prove);
		holder.tvKodeProve.setText("Kode Prove : " + kode_prove);
		holder.tvKataSandi.setText("Kata Sandi : " + kata_sandi);
		holder.tvNamaInternal.setText("Pembimbing : " + nama_internal);
		holder.tvStatusProve.setText("Status : " + status_prove);

	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListProveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView tvNamaMateri, tvDetailJadwal, tvTanggalProve, tvKodeProve, tvKataSandi, tvNamaInternal, tvStatusProve;

		public ListProveViewHolder(@NonNull View itemView) {
			super(itemView);

			// tv_nama_materi, tv_detail_jadwal , tv_tanggal_prove, tv_kode_prove, tv_kata_sandi , tv_nama_internal, tv_status_prove
			tvNamaMateri = itemView.findViewById(R.id.tv_nama_materi);
			tvDetailJadwal = itemView.findViewById(R.id.tv_detail_jadwal);
			tvTanggalProve = itemView.findViewById(R.id.tv_tanggal_prove);
			tvKodeProve = itemView.findViewById(R.id.tv_kode_prove);
			tvKataSandi = itemView.findViewById(R.id.tv_kata_sandi);
			tvNamaInternal = itemView.findViewById(R.id.tv_nama_internal);
			tvStatusProve = itemView.findViewById(R.id.tv_status_prove);

			ButterKnife.bind(this, itemView);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			clickListener.onClick(v, getAdapterPosition());
		}
	}

	public interface ClickListener {
		void onClick(View view, int position);
	}

	public void setOnItemClickListener(ClickListener clickListener) {
		AdapterListProve.clickListener = clickListener;
	}
}
