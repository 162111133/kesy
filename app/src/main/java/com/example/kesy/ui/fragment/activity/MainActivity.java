package com.example.kesy.ui.fragment.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kesy.R;
import com.example.kesy.utils.CountDownTimerUtils;
import com.example.kesy.utils.StatusBarUtil;
import com.example.kesy.utils.UserService;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button getphonecore;
    private EditText edit_phone;
    private EditText edit_cord;
    private String phone_number;
    private String cord_number;
    private EventHandler eh;
    private ArrayList<String> usernamelList;
    private UserService uService = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtil.StatusBarLightMode(this);
        init1();


    }
    protected void onDestroy() {//销毁
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eh);

    }
    protected void onResume() {
        super.onResume();
        usernamelList.clear();      //从注册返回时清除usernamelList
        usernamelList = uService.getAll(); //更新注册的内容
    }
        private void init1() {
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功 //跳转成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        //注册短信回调
        SMSSDK.registerEventHandler(eh);
    }

    //动态权限申请
    private void requsetPermission() {
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //先判断有没有权限 ，没有就在这里进行权限的申请
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA}, 1);
            } else {
            }
        } else {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //这里已经获取到了摄像头的权限，想干嘛干嘛了可以
                } else {
                    //这里是拒绝给APP摄像头权限，给个提示什么的说明一下都可以。
                    Toast.makeText(MainActivity.this, "请手动打开相机权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    private void init() {
        edit_phone = (EditText) findViewById(R.id.login_edt_username);
        edit_cord = (EditText) findViewById(R.id.login_edt_password);
        ImageView go = (ImageView) findViewById(R.id.login_img_go);
        TextView agreement = (TextView) findViewById(R.id.login_tv_agreement);
        getphonecore = (Button) findViewById(R.id.btn_login);

        go.setOnClickListener(this);
        agreement.setOnClickListener(this);
        getphonecore.setOnClickListener(this);

        uService = new UserService(MainActivity.this);

        usernamelList = uService.getAll();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_img_go:
                Intent intent = new Intent(MainActivity.this,Bottom1.class);
                startActivity(intent);
//                Mob验证码登陆
//                String mobileNums = edit_phone.getText().toString().trim();
//                String infintyNumbs = edit_cord.getText().toString().trim();
//                if (!mobileNums.equals("")) {
//                    if (isNomobile(mobileNums)) {
//                        if (!infintyNumbs.equals("")) {
//                            SMSSDK.submitVerificationCode("+86", edit_phone.getText().toString().trim(), edit_cord.getText().toString().trim());
//                            Intent intent = new Intent(MainActivity.this,Bottom1.class);
//                            startActivity(intent);
//                        } else {
//                            Toast.makeText(MainActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(MainActivity.this, "手机号输入错误", Toast.LENGTH_SHORT).show();
//                    }
//                }else {
//                        Toast.makeText(MainActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.login_tv_agreement:
                Intent intent4 = new Intent(MainActivity.this, Agreement.class);
                startActivity(intent4);
                break;
            case R.id.btn_login:
                 String mobileNums1 = edit_phone.getText().toString().trim();
                if (!mobileNums1.equals("")) {
                    if (isNomobile(mobileNums1)) {
                        SMSSDK.getVerificationCode("+86", edit_phone.getText().toString());
                        CountDownTimer countDownTimer = new CountDownTimerUtils(getphonecore, 60000, 1000);
                        countDownTimer.start();
//                        secondCount.start();
                    } else {
                        Toast.makeText(MainActivity.this, "手机号输入错误", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public boolean isNomobile(String mobileCode) {
        Pattern pattern = Pattern.compile("^(13[0-9]|15([0-3]|[5-9])|14[5,7,9]|17[1,3,5,6,7,8]|18[0-9])\\d{8}$");
        Matcher matcher = pattern.matcher(mobileCode);
        if (matcher.matches()) {
            return true;
        } else return false;
    }



}
