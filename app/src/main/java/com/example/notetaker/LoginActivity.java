package com.example.notetaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notetaker.interfaces.LoginInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;

    private Button loginBtn;

    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        loginBtn = (Button) findViewById(R.id.login);

        register = (TextView) findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        final String uname = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginInterface.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginInterface api = retrofit.create(LoginInterface.class);

        Call<String> call = api.getUserLogin(uname, pass);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("ResponseString", response.body().toString());

                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonResponse = response.body().toString();

                        parseLoginData(jsonResponse);
                    } else {
                        Log.i("onEmptyResponse", "Returned empty");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void parseLoginData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.getString("status").equals("true")) {

                Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, Home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}