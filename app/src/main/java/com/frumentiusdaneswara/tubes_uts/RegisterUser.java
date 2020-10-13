package com.frumentiusdaneswara.tubes_uts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

    TextInputLayout nameText, phoneNumber, emailText, passwordText;
    MaterialButton btnSignUp, btnSignIn;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
//    User user;
    private String CHANNEL_ID = "Channel 1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        Window window = RegisterUser.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(RegisterUser.this, R.color.colorPrimary));

        nameText        = (TextInputLayout) findViewById(R.id.inputNama);
        phoneNumber     = (TextInputLayout) findViewById(R.id.inputPhone);
        emailText       = (TextInputLayout) findViewById(R.id.inputEmail);
        passwordText    = (TextInputLayout) findViewById(R.id.inputPassword);
        btnSignUp       = (MaterialButton)findViewById(R.id.btnSignUp);
        btnSignIn       = (MaterialButton)findViewById(R.id.btnSignIn);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),LoginUser.class));
            finish();
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getEditText().getText().toString().trim();
                String password = passwordText.getEditText().getText().toString().trim();
                String name = nameText.getEditText().getText().toString().trim();
                String phone = phoneNumber.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    nameText.setError("Full name is empty");
                    return;
                }

                if(TextUtils.isEmpty(phone)){
                    phoneNumber.setError("Phone number is empty");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    emailText.setError("Email is empty.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    passwordText.setError("Password is empty.");
                    return;
                }

                if(password.length() < 6){
                    passwordText.setError("Password must be >= 6 Characters");
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterUser.this, "User Registered", Toast.LENGTH_SHORT).show();
//                            addUser();
                            savePreferences();
                            startActivity(new Intent(getApplicationContext(),LoginUser.class));
                            finish();
                        }
                        else{
                            Toast.makeText(RegisterUser.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginUser.class));
                finish();
            }
        });

    }

    private void savePreferences() {
        nameText        = (TextInputLayout) findViewById(R.id.inputNama);
        phoneNumber     = (TextInputLayout) findViewById(R.id.inputPhone);

        SharedPreferences sharedPref = getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("fullName", nameText.getEditText().getText().toString());
        editor.putString("phoneNumber", phoneNumber.getEditText().getText().toString());
        editor.apply();
    }

//    private void addUser() {
//        class AddUser extends AsyncTask<Void,Void,Void>{
//            @Override
//            protected Void doInBackground(Void... voids) {
//                String phone = passwordText.getEditText().getText().toString().trim();
//                String name = nameText.getEditText().getText().toString().trim();
//                final String address = "-";
//                User user = new User();
//                user.setFullName(name);
//                user.setPhoneNumber(phone);
//                user.setAddress(address);
//
//                DatabaseClient.getInstance(RegisterUser.this.getApplicationContext()).getDatabase()
//                        .userDao()
//                        .insert(user);
//                return null;
//            }
//        }
//        AddUser add = new AddUser();
//        add.execute();
//    }
}