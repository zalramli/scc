package com.its.scc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Models.Absensi;
import com.its.scc.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListAbsensi extends RecyclerView.Adapter<AdapterListAbsensi.ListAbsensiViewHolder> {
	// extends RecyclerView.Adapter<AdapterListBankSoftware.ListBankSoftwareViewHolder>
	Context context;
	ArrayList<Absensi> dataModelArrayList;

	private static ClickListener clickListener;

	public AdapterListAbsensi(Context context, ArrayList<Absensi> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;
	}

	@NonNull
	@Override
	public ListAbsensiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_bank_software, parent, false);
		return new ListAbsensiViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull ListAbsensiViewHolder holder, int position) {

	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListAbsensiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		public ListAbsensiViewHolder(@NonNull View itemView) {
			super(itemView);

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
