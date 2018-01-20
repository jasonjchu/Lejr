package com.lejr.lejr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RequestActivity extends AppCompatActivity {
    boolean invalid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
    }

    public void submitInformation(View view) {

        EditText amountEditText = (EditText) findViewById(R.id.sending_amount);
        EditText emailEditText = (EditText) findViewById(R.id.sender_email);

        if(Integer.parseInt(amountEditText.getText().toString()) < 0){
            TextView amount_err = (TextView)findViewById(R.id.req_err_1);
            amount_err.setText("Receiving amount must be at least $0.01.");
            invalid = true;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(((EditText) findViewById(R.id.sender_email)).getText().toString()).matches()){
            TextView email_err = (TextView)findViewById(R.id.req_err_2);
            email_err.setText("Invalid email.");
            invalid = true;
        }
        if(!invalid){

        }
    }
}
