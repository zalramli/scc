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

import com.its.scc.Models.BankSoftware;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListBankSoftware extends RecyclerView.Adapter<AdapterListBankSoftware.ListBankSoftwareViewHolder> {

	Context context;
	ArrayList<BankSoftware> dataModelArrayList;

	private static ClickListener clickListener;

	public AdapterListBankSoftware(Context context, ArrayList<BankSoftware> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;
	}

	@NonNull
	@Override
	public AdapterListBankSoftware.ListBankSoftwareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_bank_software, parent, false);
		return new ListBankSoftwareViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListBankSoftware.ListBankSoftwareViewHolder holder, int position) {
		String tanggal_bs = dataModelArrayList.get(position).getTanggal_bs();
		String hari = dataModelArrayList.get(position).getHari();
		String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
		String jam_selesai = dataModelArrayList.get(position).getJam_selesai();
		String nama_eksternal = dataModelArrayList.get(position).getNama();
		String status_bs = dataModelArrayList.get(position).getStatus_bs();

		holder.tvTanggalBankSoftware.setText(tanggal_bs);
		holder.tvDetailJadwal.setText("Jadwal : " + hari + " ( " + jam_mulai + " - " + jam_selesai + " )");
		holder.tvNamaEksternal.setText("Eksternal : " + nama_eksternal);
		holder.tvStatusBankSoftware.setText("Status : " + status_bs);

		if (status_bs.equals("Belum Selesai")){
			holder.cvItemAdapterListBankSoftware.setCardBackgroundColor(Color.RED);
		} else if (status_bs.equals("Batal")){
			holder.cvItemAdapterListBankSoftware.setCardBackgroundColor(Color.GRAY);
		}
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListBankSoftwareViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		TextView tvTanggalBankSoftware, tvDetailJadwal, tvNamaEksternal, tvStatusBankSoftware;
		CardView cvItemAdapterListBankSoftware;

		public ListBankSoftwareViewHolder(@NonNull View itemView) {
			super(itemView);

			tvTanggalBankSoftware = itemView.findViewById(R.id.tv_tanggal_bank_software);
			tvDetailJadwal = itemView.findViewById(R.id.tv_detail_jadwal);
			tvNamaEksternal = itemView.findViewById(R.id.tv_nama_eksternal);
			tvStatusBankSoftware = itemView.findViewById(R.id.tv_status_bank_software);

			cvItemAdapterListBankSoftware = itemView.findViewById(R.id.cv_item_adapter_list_bank_software);

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
		AdapterListBankSoftware.clickListener = clickListener;
	}
}
