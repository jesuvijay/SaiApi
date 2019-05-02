package com.example.saiapi.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saiapi.R;
import com.example.saiapi.fragments.api.model.Organization;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder> {

    private List<Organization> organizationsList;
    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        clickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.organization_listitem, parent, false);
        return new OrganizationViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationViewHolder holder, int position) {
        Organization currentItem = organizationsList.get(position);
        holder.tvOrgName.setText(currentItem.getDisplayName());
        holder.tvOrgTime.setText(currentItem.getUpdatedAt());

    }

    public OrganizationAdapter() {

    }

    public void addData(List<Organization> organizations) {
        organizationsList = organizations;
        notifyDataSetChanged();
    }

    public Organization getItem(int position){
        return organizationsList.get(position);
    }
    @Override
    public int getItemCount() {
        if (organizationsList != null)
            return organizationsList.size();
        return 0;
    }

    public static class OrganizationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtOrgName)
        public TextView tvOrgName;
        @BindView(R.id.tvOrgTime)
        public TextView tvOrgTime;

        public OrganizationViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {

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
        }

    }
}
