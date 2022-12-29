package com.oracto.jitsi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextInputEditText username;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpToolbar();
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case  R.id.home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                        startActivity(intent);
                        break;

//Paste your privacy policy link

//                    case  R.id.nav_Policy:{
//
//                        Intent browserIntent  = new Intent(Intent.ACTION_VIEW , Uri.parse(""));
//                        startActivity(browserIntent);
//
//                    }
                    //       break;
                    case  R.id.nav_share:{
                        Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();
//
//                        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
//                        sharingIntent.setType("text/plain");
//                        String shareBody =  "http://play.google.com/store/apps/detail?id=" + getPackageName();
//                        String shareSub = "Try now";
//                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
//                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
//                        startActivity(Intent.createChooser(sharingIntent, "Share using"));

                    }
                    break;
                }
                return false;
            }
        });
        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    .setWelcomePageEnabled(false)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void onButtonClick(View v) {
        EditText editText = findViewById(R.id.conferenceName);
        String text = editText.getText().toString();
        if (text.length() > 0) {
            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(text)
                    .build();
            JitsiMeetActivity.launch(this, options);
        }
    }
    public void onBtnClick(View view){
        Intent i = new Intent(getApplicationContext(),SigninActivity.class);
        startActivity(i);
        finish();
    }
    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

}