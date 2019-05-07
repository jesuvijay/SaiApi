package com.example.saiapi.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saiapi.R;
import com.example.saiapi.fragments.api.model.Organization;
import com.example.saiapi.fragments.api.model.OrganizationList;
import com.example.saiapi.fragments.ui.ApiClient;
import com.example.saiapi.utils.constants.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrganizationsFragment extends Fragment {
    private static final String TAG = "OrganizationsFragment";
    @BindView(R.id.orgRecyclerView)
    RecyclerView recyclerView;
    private OrganizationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organizations, container, false);
        ButterKnife.bind(this, view);
        // Inflate the layout for this fragment
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new OrganizationAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnClickListener() {
            @Override
            public void onItemClick(int position) {
                callApplicationList(adapter.getItem(position));
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
        // for organization list
        getOrganizationList(" Bearer " + Constants.getJwtToken(getContext()), "50", "0");
        return view;
    }

    // call application list
    private void callApplicationList(Organization organization) {
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        ApplicationFragment applicationFragment = ApplicationFragment.newInstance(organization.getId());
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().addToBackStack(ApplicationFragment.class.getSimpleName()).replace(android.R.id.content, applicationFragment).commitAllowingStateLoss();
    }


    public void getOrganizationList(String jwtToken, String limit, String offset) {
        Log.d(TAG, "getOrganizationList: " + jwtToken);
        final Call<OrganizationList> organizationListCall = ApiClient.getApi().getOrganization(jwtToken, limit, offset);
        organizationListCall.enqueue(new Callback<OrganizationList>() {
            @Override
            public void onResponse(Call<OrganizationList> call, Response<OrganizationList> response) {
                Log.d(TAG, "onResponse: " + response.code());
                OrganizationList organizationList = response.body();
                if (organizationList != null) {
                    adapter.addData(organizationList.getOrganizations());
                    Log.v(TAG, "getOrganizarionData: " + organizationList.toString());
                }


            }

            @Override
            public void onFailure(Call<com.example.saiapi.fragments.api.model.OrganizationList> call, Throwable t) {
                Log.d(TAG, "onFailure: fetch error");
                organizationListCall.cancel();
            }
        });

    }



}
