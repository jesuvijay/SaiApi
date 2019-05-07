package com.example.saiapi.fragments.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saiapi.R;
import com.example.saiapi.fragments.OnClickListener;
import com.example.saiapi.fragments.api.model.ApplicationLora;
import com.example.saiapi.fragments.api.model.Device;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder> {

    private List<Device> devices;
    private OnClickListener onItemClickListener;

    public void setOnItemClickListener(OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }



    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.organization_listitem, parent, false);
        return new DeviceViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        Device currentItem = devices.get(position);
        holder.tvOrgName.setText(currentItem.getName());
        holder.tvOrgTime.setText(currentItem.getLastSeenAt());

    }

    public DeviceListAdapter() {

    }

    public void addData(List<Device> _devices) {
        devices = _devices;
        notifyDataSetChanged();
    }


    public Device getItem(int position){
        return devices.get(position);
    }
    @Override
    public int getItemCount() {
        if (devices != null)
            return devices.size();
        return 0;
    }

    public static class DeviceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtOrgName)
        public TextView tvOrgName;
        @BindView(R.id.tvOrgTime)
        public TextView tvOrgTime;

        public DeviceViewHolder(@NonNull View itemView, OnClickListener onItemClickListener) {

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
