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
import com.its.scc.Models.Eksternal;
import com.its.scc.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class AdapterListEksternal extends RecyclerView.Adapter<AdapterListEksternal.ListEksternalViewHolder> {

	Context context;
	ArrayList<Eksternal> dataModelArrayList;

	SessionManager sessionManager;
	BaseUrl baseUrl;
	private static ClickListener clickListener;

	public AdapterListEksternal(Context context, ArrayList<Eksternal> dataModelArrayList) {
		this.context = context;
		this.dataModelArrayList = dataModelArrayList;

		sessionManager = new SessionManager(context);
		baseUrl = new BaseUrl();
	}

	@NonNull
	@Override
	public AdapterListEksternal.ListEksternalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(context).inflate(R.layout.item_adapter_list_prove_anggota, parent, false);
		return new ListEksternalViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterListEksternal.ListEksternalViewHolder holder, int position) {
		String nama = dataModelArrayList.get(position).getNama();
		String username = dataModelArrayList.get(position).getUsername();
		String akun_line = dataModelArrayList.get(position).getAkun_line();
		String no_hp = dataModelArrayList.get(position).getNo_hp();

		holder.tvNama.setText(nama + " / " + username);
		holder.tvAkunLine.setText("Akun Line : " + akun_line);
		holder.tvNoHp.setText("No Hp : " + no_hp);

		String alamat = baseUrl.getUrlUpload() + "image/eksternal/" + dataModelArrayList.get(position).getFoto() + ".jpg";
		Picasso.get().load(alamat).resize(300, 300).centerInside().placeholder(R.drawable.ic_account_circle).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.ivFoto);
	}

	@Override
	public int getItemCount() {
		return dataModelArrayList.size();
	}

	public class ListEksternalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		ImageView ivFoto;
		TextView tvNama, tvAkunLine, tvNoHp;

		public ListEksternalViewHolder(@NonNull View itemView) {
			super(itemView);

			ivFoto = itemView.findViewById(R.id.iv_foto);
			tvNama = itemView.findViewById(R.id.tv_nama);
			tvAkunLine = itemView.findViewById(R.id.tv_akun_line);
			tvNoHp = itemView.findViewById(R.id.tv_no_hp);

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
		AdapterListEksternal.clickListener = clickListener;
	}
}
