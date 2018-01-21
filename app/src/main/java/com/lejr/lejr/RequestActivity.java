package com.lejr.lejr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
    }


    // putRequest() sends a receive money request to the specified email address with
    //  a specified amount of money
    // NEED TO FIX: currently does get request to obtain encrypted secret key
    public void putRequest(String sender, String recipient, float amount) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("sender",sender);
        json.put("recipient",recipient);
        json.put("amount",amount);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://gateway-web.beta.interac.ca/publicapi/api/v1/test/encrypted-key";

        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println("GOOD:" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.getCause());
                    }
                }) {//here before semicolon ; and use { }.
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("thirdPartyAccessId", "CA1TA3saqsQ5jNyv");
                headers.put("salt", "jason");
                headers.put("secretKey", "70a4b45c-6348-43ad-84a6-543e2b516b9b");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return super.getBodyContentType();
            }
        };

        queue.add(request);
    }

    public void submitInformation(View view) {
        boolean valid = true;

        EditText amountEditText = (EditText) findViewById(R.id.sending_amount);
        EditText emailEditText = (EditText) findViewById(R.id.sender_email);

        if(!Patterns.EMAIL_ADDRESS.matcher(((EditText) findViewById(R.id.sender_email)).getText().toString()).matches()){
            TextView email_err = (TextView)findViewById(R.id.req_err_2);
            email_err.setText("Invalid email.");
            valid = false;
        }
        else{
            TextView email_err = (TextView)findViewById(R.id.req_err_2);
            email_err.setText("");
        }

        if(valid){
            //putRequest();
        }
    }
}
