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

import com.its.scc.Models.Jadwal;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListJadwal extends RecyclerView.Adapter<AdapterListJadwal.ListJadwalViewHolder> {

	Context context;
	ArrayList<Jadwal> dataModelArrayList;

	private static ClickListener clickListener;

	public AdapterListJadwal(Context context, ArrayList<Jadwal> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;
	}

	@NonNull
	@Override
	public AdapterListJadwal.ListJadwalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_jadwal, parent, false);
		return new ListJadwalViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListJadwal.ListJadwalViewHolder holder, int position) {
		holder.tvHari.setText(dataModelArrayList.get(position).getHari());

		String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
		String jam_selesai = dataModelArrayList.get(position).getJam_selesai();
		holder.tvJam.setText("( " + jam_mulai + " sampai " + jam_selesai + " )");

		String status_booking  = dataModelArrayList.get(position).getStatus_booking();
		holder.tvStatusBooking.setText(status_booking);

		if (!status_booking.equals("Free")){
			holder.cvItemAdapterListJadwal.setCardBackgroundColor(Color.RED);
		}
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListJadwalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		CardView cvItemAdapterListJadwal;
		TextView tvHari, tvJam, tvStatusBooking;

		public ListJadwalViewHolder(@NonNull View itemView) {
			super(itemView);

			tvHari = itemView.findViewById(R.id.tv_hari);
			tvJam = itemView.findViewById(R.id.tv_jam);
			tvStatusBooking = itemView.findViewById(R.id.tv_status_booking);

			cvItemAdapterListJadwal = itemView.findViewById(R.id.cv_item_adapter_list_jadwal);

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
		AdapterListJadwal.clickListener = clickListener;
	}
}
