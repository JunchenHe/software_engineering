package com.angine.www.zxingtest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;

    private SharedPreferences.Editor editor;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button loginbtn;

    private Button registerbtn;

    private CheckBox rememberPass;

    String account;

    String password;



    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.account);
        passwordEdit = (EditText) findViewById(R.id.password);
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        loginbtn = (Button) findViewById(R.id.login_btn);
        registerbtn = (Button) findViewById(R.id.register);
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                account = accountEdit.getText().toString();
                password = passwordEdit.getText().toString();

                sendRequestWithOkHttp();

            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client = new OkHttpClient();
                    String url = "http://120.79.73.175:8080/login?username=" +account+
                            "&password="+password;

                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    String cookie = response.header("Set-Cookie");
                    HttpUtil.saveCookiePreference(MainActivity.this,cookie);


                    if (!responseData.equals("-1")) {
                        editor = pref.edit();
                        if (rememberPass.isChecked()) { // 检查复选框是否被选中
                            editor.putBoolean("remember_password", true);
                            editor.putString("account", account);
                            editor.putString("password", password);
                        } else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        startActivity(intent);

                        finish();
                    }
                    else {
                        Looper.prepare();
                        Toast.makeText(MainActivity.this, "帐号或密码错误", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
