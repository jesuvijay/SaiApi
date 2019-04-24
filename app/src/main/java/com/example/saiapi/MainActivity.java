package com.example.saiapi;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUserName,etPassword;
    Button btnSubmit;
    private static final String TAG = "MainActivity";
    Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPasswd);
        btnSubmit=findViewById(R.id.btnSubmit);
        api=ApiClient.getClient().create(Api.class);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etPassword.getText())&&!TextUtils.isEmpty(etUserName.getText())){
                    LoginRequest loginRequest = new LoginRequest(etUserName.getText().toString(), etPassword.getText().toString());
                    final Call<Login> loginCall=api.getJwt(loginRequest);
                    loginCall.enqueue(new Callback<Login>() {
                        @Override
                        public void onResponse(Call<Login> call, Response<Login> response) {
                            Log.d(TAG, "onResponse: "+response.code());
                            Login login=response.body();
                            if (login!=null)
                            Toast.makeText(MainActivity.this, login.getJswttoken(), Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Login> call, Throwable t) {

                            Log.d(TAG, "onFailure: "+t.getMessage());
                            call.cancel();
                            Toast.makeText(MainActivity.this, getString(R.string.error_failure), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });




    }
}
