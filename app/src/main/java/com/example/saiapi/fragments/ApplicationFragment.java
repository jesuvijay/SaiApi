package com.example.saiapi.fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saiapi.R;
import com.example.saiapi.fragments.api.model.ApplicationList;
import com.example.saiapi.fragments.api.model.ApplicationLora;
import com.example.saiapi.fragments.ui.ApiClient;
import com.example.saiapi.fragments.ui.DeviceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.saiapi.utils.constants.Constants.getJwtToken;

/**
 * A simple {@link Fragment} subclass.
 */
public class ApplicationFragment extends Fragment {

    @BindView(R.id.orgRecyclerView)
    RecyclerView recyclerView;
    private ApplicationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static final String ORGANIZATION_ID = "organizationId";
    private String organizationID;

    public ApplicationFragment() {
        // Required empty public constructor
    }

    public static ApplicationFragment newInstance(String organizationID) {

        Bundle args = new Bundle();

        ApplicationFragment fragment = new ApplicationFragment();
        args.putString(ORGANIZATION_ID, organizationID);
        fragment.setArguments(args);
        return fragment;
    }


    private static final String TAG = "ApplicationFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            organizationID = bundle.getString(ORGANIZATION_ID);
        }
        View view = inflater.inflate(R.layout.fragment_organizations, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new ApplicationAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        // for organization list
        // organization id fixed value

        adapter.setOnItemClickListener(new OnClickListener() {
            @Override
            public void onItemClick(int position) {
                callDeviceFragment(adapter.getItem(position));
            }

            @Override
            public void onLongItemClick(int position) {
                showDialog(adapter.getItem(position).toString());
            }
        });
        getApplicationList(" Bearer " + getJwtToken(getContext()), "50", "0", String.valueOf(organizationID));
        return view;
    }

    private void showDialog(String details) {
        DialogFragment dialogFragment = AlertDialogFragment.newInstance(details);
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    // application list
    private void getApplicationList(String jwtToken, String limit, String offset, String orgId) {
        Log.d(TAG, "getApplicationList: " + jwtToken);
        final Call<ApplicationList> applicationListCall = ApiClient.getApi().getApplicationList(jwtToken, limit, offset, orgId);
        applicationListCall.enqueue(new Callback<ApplicationList>() {
            @Override
            public void onResponse(Call<ApplicationList> call, Response<ApplicationList> response) {
                Log.d(TAG, "onResponse: " + response.code());
                ApplicationList applicationList = response.body();
                if (applicationList != null) {
                    Log.v(TAG, "getApplicationLIst data: " + applicationListCall.toString());
                    adapter.addData(applicationList.getApplicationLoras());
                }

            }

            @Override
            public void onFailure(Call<ApplicationList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                call.cancel();
                Toast.makeText(getContext(), getString(R.string.error_failure), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void callDeviceFragment(ApplicationLora applicationLora) {
        DeviceFragment deviceFragment = DeviceFragment.newInstance(applicationLora.getId(), applicationLora.getServiceProfileID());
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(ApplicationFragment.class.getSimpleName()).replace(android.R.id.content, deviceFragment).commitAllowingStateLoss();


    }
}
