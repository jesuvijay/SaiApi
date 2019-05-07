package com.example.saiapi.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.saiapi.R;
import com.example.saiapi.fragments.api.model.JwtToken;
import com.example.saiapi.fragments.api.model.LoginRequest;
import com.example.saiapi.fragments.ui.ApiClient;
import com.example.saiapi.utils.preference.Keys;
import com.example.saiapi.utils.preference.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JwtFragment extends Fragment {

    @BindView(R.id.etUsername)
    EditText etUserName;
    @BindView(R.id.etPasswd)
    EditText etPassword;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    public static String jwtToken = "";
    private static final String TAG = "JwtFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jwt, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnSubmit)
    void submit() {
        getJwtTokenCall();
    }

    // set jwt token
    private void setJwtToken(String jwtToken) {
        Prefs.init(getContext());
        Prefs.putStrPref(Keys.JWT_TOKEN, jwtToken);

    }

    private void getJwtTokenCall() {
        if (!TextUtils.isEmpty(etPassword.getText()) && !TextUtils.isEmpty(etUserName.getText())) {
            LoginRequest loginRequest = new LoginRequest(etUserName.getText().toString(), etPassword.getText().toString());
            final Call<JwtToken> loginCall = ApiClient.getApi().getJwt(loginRequest);
            loginCall.enqueue(new Callback<JwtToken>() {
                @Override
                public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {
                    Log.d(TAG, "onResponse: " + response.code());
                    JwtToken login = response.body();
                    if (login != null) {
                        Toast.makeText(getContext(), login.getJswttoken(), Toast.LENGTH_SHORT).show();
                        jwtToken = login.getJswttoken();
                        setJwtToken(jwtToken);
                        callOrganizationList();

                    } else
                        Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<JwtToken> call, Throwable t) {

                    Log.d(TAG, "onFailure: " + t.getMessage());
                    call.cancel();
                    Toast.makeText(getContext(), getString(R.string.error_failure), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


    private void callOrganizationList() {
        // remove fragment
//        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        OrganizationsFragment organizationsFragment = new OrganizationsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .addToBackStack(OrganizationsFragment.class.getSimpleName())
                .replace(android.R.id.content, organizationsFragment)
                .commitAllowingStateLoss();


    }

}
