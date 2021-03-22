package com.oveon.sabnoter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText userETlogin, passETlogin;
    Button loginBtn, registerBtn;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userETlogin = findViewById(R.id.loginInputMenu);
        passETlogin = findViewById(R.id.passwordInputMenu);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.doItBtn);

        auth = FirebaseAuth.getInstance();

        // Register Auth
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intT = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intT);
            }
        });


        // login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = userETlogin.getText().toString();
                String passText = passETlogin.getText().toString();

                if(TextUtils.isEmpty(emailText) || TextUtils.isEmpty(passText)){
                    Toast.makeText(LoginActivity.this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
                }else {
                    auth.signInWithEmailAndPassword(emailText, passText)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent intT = new Intent(LoginActivity.this, MainActivity.class);
                                        intT.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intT);
                                        finish();
                                    }else {
                                        Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}