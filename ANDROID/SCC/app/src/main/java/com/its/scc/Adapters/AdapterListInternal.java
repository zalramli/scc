package com.its.scc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.its.scc.Controllers.BaseUrl;
import com.its.scc.Controllers.SessionManager;
import com.its.scc.Models.Internal;
import com.its.scc.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListInternal extends RecyclerView.Adapter<AdapterListInternal.ListInternalViewHolder> {

	Context context;
	ArrayList<Internal> dataModelArrayList;

	SessionManager sessionManager;
	BaseUrl baseUrl;
	private static ClickListener clickListener;

	public AdapterListInternal(Context context, ArrayList<Internal> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;

		sessionManager = new SessionManager(context);
		baseUrl = new BaseUrl();
	}

	@NonNull
	@Override
	public AdapterListInternal.ListInternalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_internal, parent, false);
		return new ListInternalViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListInternal.ListInternalViewHolder holder, int position) {

		String akun_line = dataModelArrayList.get(position).getAkun_line();

		holder.tvNama.setText(dataModelArrayList.get(position).getNama());
		holder.tvAkunLine.setText("Akun Line : " + akun_line);

		String alamat = baseUrl.getUrlUpload() + "image/internal/" + dataModelArrayList.get(position).getFoto() + ".jpg";
		Picasso.get().load(alamat).resize(300, 600).centerInside().placeholder(R.drawable.ic_account_circle).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListInternalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		ImageView ivFoto;
		TextView tvNama, tvAkunLine;

		public ListInternalViewHolder(@NonNull View itemView) {
			super(itemView);

			ivFoto = itemView.findViewById(R.id.iv_foto);
			tvNama = itemView.findViewById(R.id.tv_nama);
			tvAkunLine = itemView.findViewById(R.id.tv_akun_line);

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
		AdapterListInternal.clickListener = clickListener;
	}
}
