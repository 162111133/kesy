package com.example.kesy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        EditText username = (EditText)findViewById(R.id.login_edt_username);
        EditText password = (EditText)findViewById(R.id.login_edt_password);
        ImageView go = (ImageView)findViewById(R.id.login_img_go);
        TextView findpassword = (TextView) findViewById(R.id.login_tv_findpassword);
        TextView login = (TextView)findViewById(R.id.login_tv_newuser);
        TextView agreement = (TextView)findViewById(R.id.login_tv_agreement);

        username.setOnClickListener(this);
        password.setOnClickListener(this);
        go.setOnClickListener(this);
        findpassword.setOnClickListener(this);
        login.setOnClickListener(this);
        agreement.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_img_go:
                Intent intent1 = new Intent(MainActivity.this,Home_Bottombar.class);
                startActivity(intent1);
                break;
            case R.id.login_tv_findpassword:
                break;
            case R.id.login_tv_newuser:
                break;
            case R.id.login_tv_agreement:
                Intent intent4 = new Intent(MainActivity.this,Agreement.class);
                startActivity(intent4);
                break;

        }
    }
}
