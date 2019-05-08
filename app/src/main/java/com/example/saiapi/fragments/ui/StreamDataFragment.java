package com.example.saiapi.fragments.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.saiapi.R;
import com.example.saiapi.fragments.api.service.Api;
import com.example.saiapi.utils.constants.Constants;

import java.util.Observable;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * <p>
 * to handle interaction events.
 * Use the {@link StreamDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StreamDataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String DEV_EUI = "dev_eui";

    private static final String TAG = "StreamDataFragment";
    // TODO: Rename and change types of parameters
    private String devEui;

    @BindView(R.id.tvStreamData)
     TextView tvStreamData;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param devEui Parameter 2.
     * @return A new instance of fragment StreamDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StreamDataFragment newInstance( String devEui) {
        StreamDataFragment fragment = new StreamDataFragment();
        Bundle args = new Bundle();
        args.putString(DEV_EUI, devEui);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            devEui = getArguments().getString(DEV_EUI);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stream_data, container, false);
        ButterKnife.bind(this,view);
        // Inflate the layout for this fragment
        callData(devEui);

        return view;
    }

    private void callData(String devEui){
        Call<ResponseBody> responseBodyCall=ApiClient.getApi().streamJson(Constants.getJwtToken(getContext()),devEui);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Log.d(TAG, response.body().toString());
                    tvStreamData.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


}
