package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.projectuas.MainActivity.fromHtml;

public class activity_register extends AppCompatActivity {

    EditText etUsername, etPassword, etConfPassword;
    Button bRegis;
    DatabaseDB dbHelper;
    TextView regis;
    String username, password, confpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseDB(this);

        etUsername = (EditText)findViewById(R.id.regusername);
        etPassword = (EditText)findViewById(R.id.regpass);
        etConfPassword = (EditText)findViewById(R.id.regconfpass);
        bRegis = (Button)findViewById(R.id.btnreg);
        regis = (TextView)findViewById(R.id.tvregis);

        regis.setText(fromHtml("Back to " +
                "</font><font color='#3B5998'>Login</font>"));

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_register.this, MainActivity.class));
            }
        });

        bRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                confpassword = etConfPassword.getText().toString().trim();

                ContentValues values = new ContentValues();
                if(!password.equals(confpassword)){
                    Toast.makeText(activity_register.this, "Password does not match!!!", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals("") || username.equals("")){
                    Toast.makeText(activity_register.this, "Username of Password can not be empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    values.put(DatabaseDB.row_username, username);
                    values.put(DatabaseDB.row_password, password);
                    dbHelper.insertData(values);

                    Toast.makeText(activity_register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
    public static Spanned fromHtml(String html){
        Spanned result;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }
        else{
            result = Html.fromHtml(html);
        }
        return result;
    }
}