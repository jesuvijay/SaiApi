package com.example.saiapi.fragments.ui;


import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saiapi.R;
import com.example.saiapi.fragments.AlertDialogFragment;
import com.example.saiapi.fragments.ApplicationAdapter;
import com.example.saiapi.fragments.OnClickListener;
import com.example.saiapi.fragments.api.model.DeviceList;
import com.example.saiapi.utils.constants.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeviceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeviceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String APP_ID = "jwtToken";
    private static final String SERVICE_PROFILE_ID = "serviceProfileId";
    private static final String TAG = "DeviceFragment";
    // TODO: Rename and change types of parameters
    private String appId, servicePId;
    @BindView(R.id.orgRecyclerView)
    RecyclerView recyclerView;
    private DeviceListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static final String ORGANIZATION_ID = "organizationId";
    private String organizationID;


    public DeviceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param appId      Parameter 1.
     * @param servicePId Parameter 2.
     * @return A new instance of fragment DeviceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeviceFragment newInstance(String appId, String servicePId) {
        DeviceFragment fragment = new DeviceFragment();
        Bundle args = new Bundle();
        args.putString(APP_ID, appId);
        args.putString(SERVICE_PROFILE_ID, servicePId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appId = getArguments().getString(APP_ID);
            servicePId = getArguments().getString(SERVICE_PROFILE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_organizations, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new DeviceListAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onLongItemClick(int position) {
                showDialog(adapter.getItem(position).toString());
            }
        });
        getDeviceList(Constants.getJwtToken(getContext()), "50", "0", appId, servicePId);
        return view;
    }


    private void showDialog(String details) {
        DialogFragment dialogFragment = AlertDialogFragment.newInstance(details);
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    // getDevice list
    private void getDeviceList(String jwtToken, String limit, String offset, String appId, String serviceProfId) {
        Log.d(TAG, "getApplicationList: " + jwtToken);
        final Call<DeviceList> applDeviceListCall = ApiClient.getApi().getDeviceList(jwtToken, limit, offset, appId, serviceProfId);
        applDeviceListCall.enqueue(new Callback<DeviceList>() {
            @Override
            public void onResponse(Call<DeviceList> call, Response<DeviceList> response) {
                Log.d(TAG, "onResponse: " + response.code());
                DeviceList deviceList = response.body();
                if (deviceList != null && deviceList.getDevices() != null) {
                    adapter.addData(deviceList.getDevices());
                    Log.i(TAG, "onResponse: " + deviceList.toString());
                }
            }

            @Override
            public void onFailure(Call<DeviceList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                call.cancel();

            }
        });

    }


}
