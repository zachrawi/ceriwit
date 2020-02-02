package com.zachrawi.ceriwit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CeriwitAdapter extends RecyclerView.Adapter<CeriwitAdapter.ViewHolder> {
    private Context mContext;
    private int mResource;
    private ArrayList<Ceriwit> mCeriwits;

    public CeriwitAdapter(Context context, int resource, ArrayList<Ceriwit> ceriwits) {
        mContext = context;
        mResource = resource;
        mCeriwits = ceriwits;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(mResource, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ceriwit ceriwit = mCeriwits.get(position);

        holder.tvUsername.setText(ceriwit.getUsername());
        holder.tvMessage.setText(ceriwit.getMessage());
    }

    @Override
    public int getItemCount() {
        return mCeriwits.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername;
        TextView tvMessage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvMessage = itemView.findViewById(R.id.tvMessage);
        }
    }
}
