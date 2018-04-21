package com.angine.www.zxingtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.engine.www.coloZXing.activity.CaptureActivity;
import com.engine.www.coloZXing.utils.Constant;

public class ResultActivity extends AppCompatActivity {

    private Button twobar;
    private TextView result;

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
            String str = data.getStringExtra(Constant.REQUEST_CODE_SCAN_RESULT);
            result.setText(str);
        }
    }
}
