package com.example.myfood;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
EditText first_name,last_name,email,password,conf;
ConstraintLayout b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
first_name=findViewById(R.id.first_name);
last_name=findViewById(R.id.last_name);
email=findViewById(R.id.email);
password=findViewById(R.id.password);
conf=findViewById(R.id.conf);
b=findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFormFailed();
            }
        });
        }
    public void goToHome(View view){
        Intent intent=new Intent(SignUpActivity.this,IntroActivity.class);
        startActivity(intent);
        finish();
    }
    public void goToSignIn(View view){
        Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
        startActivity(intent);
        finish();
    }
    public void processFormFailed(){
        if(!validateFirstname() || !validateLastname() || !validateEmail() || !validatePandC() ){
            return;
        }
        RequestQueue queue= Volley.newRequestQueue(SignUpActivity.this);
        String url="http://142.93.14.153:8079/api/v1/user/register";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equalsIgnoreCase("success")) {
                            first_name.setText(null);
                            last_name.setText(null);
                            email.setText(null);
                            password.setText(null);
                            conf.setText(null);
                           // Log.d(TAG,"hounaida"+response);
                            Toast.makeText(SignUpActivity.this, "Register Succefull", Toast.LENGTH_SHORT).show();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this, "Register Un-Succefull"+error.toString(), Toast.LENGTH_SHORT).show();
             //   Log.d(TAG,"Register Un-Succefull"+error.toString());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name",first_name.getText().toString());
                params.put("last_name",last_name.getText().toString());
                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());
                Intent intent =new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
                return params;
            }
        };
        queue.add(stringRequest);

// Add the request to the RequestQueue.


    }
    public boolean validateFirstname(){
        String firstname=first_name.getText().toString();
        if(firstname.isEmpty()){
            first_name.setError("First name connot Empty");
            return false;
        }else{
            first_name.setError(null);
            return true;
        }
    }
    public boolean validateLastname(){
        String lastname=last_name.getText().toString();
        if(lastname.isEmpty()){
            last_name.setError("Last name connot Empty");
            return false;
        }else{
            last_name.setError(null);
            return true;
        }
    }
    public boolean validateEmail(){
        String Email=email.getText().toString();
        if(Email.isEmpty()){
            email.setError("Email name connot Empty");
            return false;
        }else if(!StringHelper.regexEmailVPattern(Email)){
            email.setError("Please Entrer a valid Email");
            return false;
        }else {
            email.setError(null);
            return true;
        }
    }
    public boolean validatePandC(){
        String Password=password.getText().toString();
        String Conf=conf.getText().toString();
        if(Password.isEmpty()){
            password.setError("Password connot Empty");

            return false;
        }else if(!Password.equals(Conf)){
            password.setError("Password do not match");
            return false;
        }else if(Conf.isEmpty()){
            conf.setError("Confirm connot Empty");
            return false;
        }else{
            password.setError(null);
            conf.setError(null);
            return true;
        }
    }
    }
