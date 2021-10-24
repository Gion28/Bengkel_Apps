package com.example.projectuas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.text.Html.fromHtml;

public class MainActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button bLogin;
    DatabaseDB dbHelper;
    TextView cAcc;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText)findViewById(R.id.username);
        etPassword = (EditText)findViewById(R.id.password);
        bLogin = (Button)findViewById(R.id.btnlogin);
        dbHelper = new DatabaseDB(this);
        cAcc = (TextView)findViewById(R.id.tvcreate);

        cAcc.setText(fromHtml("I don't have account yet. " +
                "</font><font color='#3B5998'>create one</font>"));
        cAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, activity_register.class);
                startActivity(intent);
            }
        });
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUsername.getText().toString().trim();
                password = etPassword.getText().toString().trim();

                Boolean chck = dbHelper.check(username,password);
                if(chck == true){
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent i;
                    i = new Intent(MainActivity.this, MainMenu.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this, "Login Failed!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }
        else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}