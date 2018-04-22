package com.angine.www.zxingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText querenpassword;
    private Button queren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        querenpassword=(EditText)findViewById(R.id.querenpassword);
        queren=(Button)findViewById(R.id.queren);
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!querenpassword.equals(password)) {
                    Toast.makeText(Register.this,"两次输入的密码不相同",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
