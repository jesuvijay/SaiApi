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

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.OrganizationViewHolder> {

    private List<ApplicationLora> applicationsList;

    @NonNull
    @Override
    public OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.organization_listitem, parent, false);
        return new OrganizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationViewHolder holder, int position) {
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

    public static class OrganizationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtOrgName)
        public TextView tvOrgName;
        @BindView(R.id.tvOrgTime)
        public TextView tvOrgTime;

        public OrganizationViewHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
