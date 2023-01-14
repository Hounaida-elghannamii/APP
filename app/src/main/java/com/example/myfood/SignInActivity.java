package com.example.myfood;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
ConstraintLayout b1,b2;
EditText emaill,passwordd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        emaill=findViewById(R.id.emaill);
        passwordd=findViewById(R.id.passwordd);
b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
authent();
    }
});
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void authent(){
        if(!validateEmail() || !validateP() ){
            return;
        }
        RequestQueue queue= Volley.newRequestQueue(SignInActivity.this);
        String url="http://142.93.14.153:8079/api/v1/user/login";
        HashMap<String,String> params=new HashMap<String,String>();
        params.put("email",emaill.getText().toString());
        params.put("password",passwordd.getText().toString());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String first_name=(String) response.get("first_name");
                    String last_name=(String) response.get("last_name");
                    String email=(String) response.get("email");
                    Intent intent =new Intent(SignInActivity.this,MainActivity.class);
                    intent.putExtra("first_name",first_name);
                    intent.putExtra("last_name",last_name);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG,"Login Failed"+error.toString());
            }
        }

        );
        queue.add(jsonObjectRequest);
    }
    public boolean validateEmail(){
        String Email=emaill.getText().toString();
        if(Email.isEmpty()){
            emaill.setError("Email name connot Empty");
            return false;
        }else if(!StringHelper.regexEmailVPattern(Email)){
            emaill.setError("Please Entrer a valid Email");
            return false;
        }else {
            emaill.setError(null);
            return true;
        }
    }
    public boolean validateP(){
        String Password=passwordd.getText().toString();

        if(Password.isEmpty()){
            passwordd.setError("Password connot Empty");

            return false;
        }else{
            passwordd.setError(null);
            return true;
        }
    }
}
