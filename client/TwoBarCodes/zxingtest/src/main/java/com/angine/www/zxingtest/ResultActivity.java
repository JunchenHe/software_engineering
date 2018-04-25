package com.angine.www.zxingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.engine.www.coloZXing.activity.CaptureActivity;
import com.engine.www.coloZXing.utils.Constant;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ResultActivity extends AppCompatActivity {

    private Button twobar;
    private TextView result;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        twobar = (Button) findViewById(R.id.twobar);
        result = (TextView)findViewById(R.id.result);
        twobar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, CaptureActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SCAN);
                //finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.REQUEST_CODE_SCAN:
                handleRequestCodeScanEvent(resultCode, data);
                break;

            default:
                break;
        }
    }


    private void handleRequestCodeScanEvent(int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            str = data.getStringExtra(Constant.REQUEST_CODE_SCAN_RESULT);
            sendRequestWithOkHttp();
            /*Intent intent = getIntent();
            String account = intent.getStringExtra("username");
            String password = intent.getStringExtra("password");
            String url = "http://120.79.73.175:8080/login?username=" +account+
                    "&password="+password;
            String url2 = "http://120.79.73.175:8080/message?time="+str;
            */
        }
    }

    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient client = new OkHttpClient();
                    String url = "http://120.79.73.175:8080/message?time="+str;


                    Request request = new Request.Builder()
                            .url(url)
                            .addHeader("cookie",HttpUtil.getCookiePreference(ResultActivity.this))
                            .build();
                    Response response = client.newCall(request).execute();
                    client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(responseData);


                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                result.setText(response);
            }
        });
    }

}
