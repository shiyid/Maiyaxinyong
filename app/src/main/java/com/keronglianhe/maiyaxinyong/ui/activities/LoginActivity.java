package com.keronglianhe.maiyaxinyong.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.keronglianhe.maiyaxinyong.R;
import com.keronglianhe.maiyaxinyong.ui.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //全透明状态栏设置
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setTransparentStatusBar(this);

        inView();
    }

    /**
     * 设置沉浸式状态栏
     *
     * @param activity
     */
    public static void setTransparentStatusBar(Activity activity) {
        //5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            //4.4到5.0
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    public void inView() {
        Button login_btn = findViewById(R.id.login_btn);
        TextView login_register = findViewById(R.id.login_register);
        TextView forget_password = findViewById(R.id.forget_password);

        login_btn.setOnClickListener(this);
        login_register.setOnClickListener(this);
        forget_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.login_register:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                break;
            case R.id.forget_password:
                Intent intent2 = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
