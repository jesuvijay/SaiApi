package com.example.saiapi.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saiapi.R;
import com.example.saiapi.api.model.JwtToken;
import com.example.saiapi.api.model.LoginRequest;
import com.example.saiapi.api.model.OrganizationList;
import com.example.saiapi.api.service.Api;
import com.example.saiapi.utils.preference.Defaults;
import com.example.saiapi.utils.preference.Keys;
import com.example.saiapi.utils.preference.Prefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etUserName, etPassword;
    Button btnSubmit;
    public static String jwtToken = "";
    private static final String TAG = "MainActivity";
    public Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPasswd);
        btnSubmit = findViewById(R.id.btnSubmit);
        api = ApiClient.getClient().create(Api.class);
        btnSubmit.setOnClickListener(v -> getJwtTokenCall());
        // for organization list
       getOrganizationList("Bearer "+getJwtToken(),1, 0);
    }

    private void getJwtTokenCall() {
        if (!TextUtils.isEmpty(etPassword.getText()) && !TextUtils.isEmpty(etUserName.getText())) {
            LoginRequest loginRequest = new LoginRequest(etUserName.getText().toString(), etPassword.getText().toString());
            final Call<JwtToken> loginCall = api.getJwt(loginRequest);
            loginCall.enqueue(new Callback<JwtToken>() {
                @Override
                public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {
                    Log.d(TAG, "onResponse: " + response.code());
                    JwtToken login = response.body();
                    if (login != null) {
                        Toast.makeText(MainActivity.this, login.getJswttoken(), Toast.LENGTH_SHORT).show();
                        jwtToken = login.getJswttoken();
                        setJwtToken(jwtToken);
                    } else
                        Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<JwtToken> call, Throwable t) {

                    Log.d(TAG, "onFailure: " + t.getMessage());
                    call.cancel();
                    Toast.makeText(MainActivity.this, getString(R.string.error_failure), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    /**
     * @param limit  Max number of organizations to return in the result-set.
     * @param offset Offset in the result-set (for pagination).
     */
    public void getOrganizationList(String jwtToken,int limit, int offset) {
        final Call<OrganizationList> organizationListCall = api.getOrganization(jwtToken,limit, offset);
        organizationListCall.enqueue(new Callback<com.example.saiapi.api.model.OrganizationList>() {
            @Override
            public void onResponse(Call<com.example.saiapi.api.model.OrganizationList> call, Response<com.example.saiapi.api.model.OrganizationList> response) {
                Log.d(TAG, "onResponse: " + response.code());
                OrganizationList organizationList = response.body();
                if (organizationList!=null)
                    Log.v(TAG, "getOrganizarionData: " + organizationList.toString());

            }

            @Override
            public void onFailure(Call<com.example.saiapi.api.model.OrganizationList> call, Throwable t) {
                Log.d(TAG, "onFailure: fetch error");
                organizationListCall.cancel();
            }
        });

    }


    // set jwt token
    private void setJwtToken(String jwtToken) {
        Prefs.init(getApplicationContext());
        Prefs.putStrPref(Keys.JWT_TOKEN, Defaults.JWT_TOKEN);

    }

    private String getJwtToken(){
        Prefs.init(getApplicationContext());
       return Prefs.getStrPref(Keys.JWT_TOKEN, Defaults.JWT_TOKEN);
    }
}
