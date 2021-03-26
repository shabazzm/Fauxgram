package com.example.fauxgram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity  extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if(ParseUser.getCurrentUser() != null ){
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister=findViewById(R.id.btnRegister);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
              public void onClick(View view){
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                registerUser(username,password);
            }
        });

    }

    private void registerUser(String username, String password) {
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    Log.d(TAG, "Registration Successfull");
                    Toast.makeText(LoginActivity.this, "You are registered!", Toast.LENGTH_SHORT).show();

                    goMainActivity();
                } else {
                    Log.e(TAG, "Registration not processed");
                    return;
                }
            }
        });

    }


    private void loginUser(String username, String password){
        Log.i(TAG, "Attempting to login user" + username);

        ParseUser.logInInBackground(username, password, new LogInCallback(){
            @Override
            public void done(ParseUser user , ParseException e){
                if ( e != null){
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                    return;

                }
                // TODO: navvigate to the main actibity if the user has signed in properly.
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
        finish();

    }

}
