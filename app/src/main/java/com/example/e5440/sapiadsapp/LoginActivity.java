package com.example.e5440.sapiadsapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth mAuth;
    private Button loginButton;
    private Button registerButton;
    private ProgressBar loginProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        inputEmail = findViewById(R.id.login_mail);
        inputPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_button);
        loginProgress = findViewById(R.id.login_progress);
        loginProgress.setVisibility(View.INVISIBLE);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();
                loginProgress.setVisibility(v.VISIBLE);
                loginButton.setVisibility(v.INVISIBLE);
                try {
                    if (password.length() > 0 && email.length() > 0){
                        mAuth.signInWithEmailAndPassword(email,password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            Toast.makeText(LoginActivity.this,
                                                    "Login successful",
                                                    Toast.LENGTH_LONG).show();
                                            loginProgress.setVisibility(v.INVISIBLE);
                                            loginButton.setVisibility(v.VISIBLE);
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this,
                                                    "Invalid email or password",
                                                    Toast.LENGTH_LONG).show();
                                            loginProgress.setVisibility(v.INVISIBLE);
                                            loginButton.setVisibility(v.VISIBLE);
                                        }
                                    }
                                });
                    }
                    else {
                        Toast.makeText(LoginActivity.this,
                                "Fill All Fields",
                                Toast.LENGTH_LONG).show();
                        loginProgress.setVisibility(v.INVISIBLE);
                        loginButton.setVisibility(v.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
