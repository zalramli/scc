package com.its.scc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Models.JadwalBS;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListJadwalBS extends RecyclerView.Adapter<AdapterListJadwalBS.ListJadwalBSViewHolder> {

	Context context;
	ArrayList<JadwalBS> dataModelArrayList;

	private static ClickListener clickListener;

	public AdapterListJadwalBS(Context context, ArrayList<JadwalBS> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;
	}

	@NonNull
	@Override
	public AdapterListJadwalBS.ListJadwalBSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_jadwal_bs, parent, false);
		return new ListJadwalBSViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListJadwalBS.ListJadwalBSViewHolder holder, int position) {
		holder.tvHari.setText(dataModelArrayList.get(position).getHari());

		String jam_mulai = dataModelArrayList.get(position).getJam_mulai();
		String jam_selesai = dataModelArrayList.get(position).getJam_selesai();
		holder.tvJam.setText("( " + jam_mulai + " sampai " + jam_selesai + " )");

//		String status_booking  = dataModelArrayList.get(position).getStatus_booking();
//		holder.tvStatusBooking.setText(status_booking);
//
//		if (!status_booking.equals("Free")){
//			holder.cvItemAdapterListJadwalBS.setCardBackgroundColor(Color.RED);
//		}
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListJadwalBSViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		CardView cvItemAdapterListJadwalBS;
		TextView tvHari, tvJam;

		public ListJadwalBSViewHolder(@NonNull View itemView) {
			super(itemView);

			tvHari = itemView.findViewById(R.id.tv_hari);
			tvJam = itemView.findViewById(R.id.tv_jam);

			cvItemAdapterListJadwalBS = itemView.findViewById(R.id.cv_item_adapter_list_jadwal_bs);

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
		AdapterListJadwalBS.clickListener = clickListener;
	}
}
