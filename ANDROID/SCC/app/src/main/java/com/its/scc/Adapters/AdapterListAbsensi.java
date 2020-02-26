package com.its.scc.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Absensi;
import com.its.scc.R;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;

public class AdapterListAbsensi extends RecyclerView.Adapter<AdapterListAbsensi.ListAbsensiViewHolder> {
	Context context;
	ArrayList<Absensi> dataModelArrayList;

	private static ClickListener clickListener;

	SessionManager sessionManager;
	String id_internal;

	public AdapterListAbsensi(Context context, ArrayList<Absensi> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;

		sessionManager = new SessionManager(context);
		HashMap<String, String> user = sessionManager.getDataUser();
		id_internal = user.get(sessionManager.ID_USER);
	}

	@NonNull
	@Override
	public ListAbsensiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_absensi, parent, false);
		return new ListAbsensiViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ListAbsensiViewHolder holder, int position) {
		// String id_absensi = dataModelArrayList.get(position).getId_absensi();
		String id_internal_db = dataModelArrayList.get(position).getId_internal();
		String judul_absensi = dataModelArrayList.get(position).getJudul_absensi();
		String tgl_absensi = dataModelArrayList.get(position).getTgl_absensi();
		String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
		String jam_selesai = dataModelArrayList.get(position).getJam_selesai();
		String status_absensi = dataModelArrayList.get(position).getStatus_absensi();
		String kata_sandi = dataModelArrayList.get(position).getKata_sandi();

		if (id_internal.equals(id_internal_db)) {
			holder.tvJudulAbsensi.setText(judul_absensi + " (" + kata_sandi + ")");
		} else {
			holder.tvJudulAbsensi.setText(judul_absensi);
		}

		holder.tvDetailJadwal.setText("( " + tgl_absensi + " ," + jam_mulai + " - " + jam_selesai + " )");
		holder.tvStatusAbsensi.setText("Status : " + status_absensi);

		if (status_absensi.equals("Belum Selesai")) {
			holder.cvItemAdapterListAbsensi.setCardBackgroundColor(Color.RED);
		} else if (status_absensi.equals("Batal")) {
			holder.cvItemAdapterListAbsensi.setCardBackgroundColor(Color.GRAY);
		}
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListAbsensiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView tvJudulAbsensi, tvDetailJadwal, tvStatusAbsensi;
		CardView cvItemAdapterListAbsensi;

		public ListAbsensiViewHolder(@NonNull View itemView) {
			super(itemView);

			tvJudulAbsensi = itemView.findViewById(R.id.tv_judul_absensi);
			tvDetailJadwal = itemView.findViewById(R.id.tv_detail_jadwal);
			tvStatusAbsensi = itemView.findViewById(R.id.tv_status_absensi);

			cvItemAdapterListAbsensi = itemView.findViewById(R.id.cv_item_adapter_list_absensi);

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
		AdapterListAbsensi.clickListener = clickListener;
	}
}
