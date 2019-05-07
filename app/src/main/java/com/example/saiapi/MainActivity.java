package com.example.saiapi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.saiapi.fragments.JwtFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private JwtFragment jwtFragment;
    public static String BASE_URL = "http://52.14.32.86:8080/";
    private String LocalURL = "http://192.168.0.202:8080/";
    private String ONLINE_URL = "http://52.14.32.86:8080/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();

    }

    private void loadFragment() {
        if (jwtFragment == null) {
            jwtFragment = new JwtFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentFrame, jwtFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enableOnline:
                if (!item.isChecked()) {
                    // true
                    BASE_URL = LocalURL;
                    item.setChecked(true);
                    Toast.makeText(this, "Local Ip enabled", Toast.LENGTH_SHORT).show();
                } else {
                    // false
                    BASE_URL = ONLINE_URL;
                    item.setChecked(false);
                    Toast.makeText(this, "Local Ip disabled", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
