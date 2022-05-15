package com.example.notetaker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notetaker.database.RetrofitClientInstance;
import com.example.notetaker.database.api.ApiEndpointInterface;
import com.example.notetaker.database.models.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        if (uname.equals("") || pass.equals("")) {
            // TODO: add error message so you can't try logging in w/out username or password
        }

        ApiEndpointInterface api = RetrofitClientInstance.getRetrofitInstance().create(ApiEndpointInterface.class);
        Call<LoginResponse> call = api.getUser(uname, pass);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse response1 = response.body();
                if (response1 == null) {
                    // TODO: add error message to log in screen
                    return;
                }

                if (response1.success) {
                    Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    Log.d("Data_response", "onResponse: login successful");
                    Log.d("Data_response", "onResponse userId: " + response1.userId);

                    // save userId in shared preferences
                    getSharedPreferences("noteTaker", MODE_PRIVATE).edit().putInt("userId", response1.userId).apply();

                    Intent intent = new Intent(LoginActivity.this, Home.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Log.d("Data_response", "onResponse: login failed");
                    /// TODO: Display error message on login screen
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("Data_response", "onFailure: " + call);
                Log.d("Data_response", "onFailure: failed to get a response");
            }
        });
    }
}