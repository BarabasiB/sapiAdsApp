package com.example.e5440.sapiadsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StarterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView changeLoginMode;

    Boolean loginModeActive = true;

    public void showHeroes(){
        Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {


        if(view.getId() == R.id.changeLoginModeTextView){
            Log.i("AppInfo" , "Change Signup Mode");

            Button loginButton = (Button) findViewById(R.id.login_button);

            if(loginModeActive){
                loginModeActive = false;
                loginButton.setText("Register");
                changeLoginMode.setText("or, Login");
            }else{
                loginModeActive = true;
                loginButton.setText("Login");
                changeLoginMode.setText("or, Register");
            }

        }
    }

    public void login(View view){
        mAuth = FirebaseAuth.getInstance();

        EditText emailEditText  = (EditText) findViewById(R.id.login_mail);
        EditText passwordEditText = (EditText) findViewById(R.id.login_password);

        if(emailEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("") ){
            Toast.makeText(this, "Email adress and password is required", Toast.LENGTH_LONG).show();
        }else{

            final String email = emailEditText.getText().toString();
            final String password = passwordEditText.getText().toString();

            if(loginModeActive){
                try{
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("signInWithEmail:", "success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        showHeroes();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("signInWithEmail:", "failure", task.getException());
                                        Toast.makeText(StarterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }

                                    // ...
                                }
                            });
                }catch (Exception e) {
                    e.getMessage();
                }
            }else{
                try{
                    mAuth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("createUserWithEmail:", "success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        showHeroes();
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("createUserWithEmail:", "failure", task.getException());
                                        Toast.makeText(StarterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                    }
                                }
                            });
                }catch (Exception e) {
                    e.getMessage();
                }
            }


        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    /*private void updateUI(FirebaseUser user) {
        if (user != null) {
            showUserList();
        } else {
            Log.i("UpdateUI" , "Else branch");
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        mAuth = FirebaseAuth.getInstance();

        changeLoginMode = (TextView) findViewById(R.id.changeLoginModeTextView);
        changeLoginMode.setOnClickListener(this);

        if(mAuth.getCurrentUser() != null){
            showHeroes();
        }

    }
}
