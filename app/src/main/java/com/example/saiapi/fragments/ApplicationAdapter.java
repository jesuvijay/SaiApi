package com.example.saiapi.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saiapi.R;
import com.example.saiapi.fragments.api.model.ApplicationLora;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {

    private List<ApplicationLora> applicationsList;
    private OnClickListener onItemClickListener;

    public void setOnItemClickListener(OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.organization_listitem, parent, false);
        return new ApplicationViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        ApplicationLora currentItem = applicationsList.get(position);
        holder.tvOrgName.setText(currentItem.getName());
        holder.tvOrgTime.setText(currentItem.getDescription());

    }

    public ApplicationAdapter() {

    }

    public void addData(List<ApplicationLora> applicationLoras) {
        applicationsList = applicationLoras;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (applicationsList != null)
            return applicationsList.size();
        return 0;
    }

    public ApplicationLora getItem(int position){
        return applicationsList.get(position);
    }
    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtOrgName)
        public TextView tvOrgName;
        @BindView(R.id.tvOrgTime)
        public TextView tvOrgTime;

        public ApplicationViewHolder(@NonNull View itemView, OnClickListener onItemClickListener) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
            itemView.setOnLongClickListener(v->{
                if (onItemClickListener!=null){
                    int position=getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION){
                        onItemClickListener.onLongItemClick(position);
                    }
                }
                return true;
            });
        }

    }
}
