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

public class SignupActivity extends AppCompatActivity {

    TextInputEditText name,gender,number, username, password, repassword;
    TextView loginlink;
    Button btnsignup;
    MyDbHandler myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (TextInputEditText) findViewById(R.id.name);
        username = (TextInputEditText) findViewById(R.id.username);
        number = (TextInputEditText) findViewById((R.id.number));
        gender = (TextInputEditText) findViewById(R.id.gender);
        password = (TextInputEditText) findViewById(R.id.password);
        repassword = (TextInputEditText) findViewById(R.id.repassword);

        btnsignup = (Button) findViewById(R.id.btnsignup);
        myDB = new MyDbHandler(this);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = name.getText().toString();
                String gen = gender.getText().toString();
                String num = number.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(uname.equals("")){
                    Toast.makeText(SignupActivity.this, "Plaese fill the User name", Toast.LENGTH_SHORT).show();
                }
                else if(gen.equals("")){
                    Toast.makeText(SignupActivity.this, "please fill the Gender Value", Toast.LENGTH_SHORT).show();
                }
                else if(num.equals("")){
                    Toast.makeText(SignupActivity.this, "Please fill the Contact Number", Toast.LENGTH_SHORT).show();
                }
                else if(user.equals("")){
                    Toast.makeText(SignupActivity.this, "Please fill the Email Name", Toast.LENGTH_SHORT).show();
                }
                else if(pass.equals("")){
                    Toast.makeText(SignupActivity.this, "Please fill the Password", Toast.LENGTH_SHORT).show();
                }
                else if(repass.equals("")){
                    Toast.makeText(SignupActivity.this, "Please fill the confirm Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass))
                    {
                        Boolean usercheckResult = myDB.checkusername(user);
                        if(usercheckResult == false){
                            Boolean regResult = myDB.insertData(uname,gen,num,user,pass);
                            if(regResult == true){
                                Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),SigninActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(SignupActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignupActivity.this, "User ALready exits. \n user please Sign IN", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        loginlink = (TextView) findViewById(R.id.loginlink);
        loginlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SigninActivity.class);
                startActivity(i);
            }
        });
    }
}