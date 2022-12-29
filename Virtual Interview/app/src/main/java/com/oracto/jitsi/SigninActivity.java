package com.oracto.jitsi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.oracto.jitsi.data.MyDbHandler;

public class SigninActivity extends AppCompatActivity {
    TextInputEditText username,password;
    TextView registerlink,forgotlink;
    Button login;
    MyDbHandler myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        myDB  = new MyDbHandler(this);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")){
                    Toast.makeText(SigninActivity.this, "Please fill the Username", Toast.LENGTH_SHORT).show();
                }
                else if(pass.equals("")){
                    Toast.makeText(SigninActivity.this, "Please fill the Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean result = myDB.checkusernamepassword(user, pass);
                    if (result==true) {
                        Toast.makeText(SigninActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(SigninActivity.this, "Login failed...Retry...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        registerlink = (TextView) findViewById(R.id.registerlink);
        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(intent);

            }
        });
    }
}