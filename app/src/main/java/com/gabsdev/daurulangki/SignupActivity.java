package com.gabsdev.daurulangki;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity  {
    private Button btnRegister;
    private EditText edtPassword, edtEmail;
    private EditText edtNumber, edtName, edtUsername;
    private TextView tvSignIn;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    ProgressDialog pd;
//    String email_txt, password_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnRegister = findViewById(R.id.btnRegister);
        edtName = findViewById(R.id.edtName);
        edtUsername = findViewById(R.id.edtUsername);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        tvSignIn = findViewById(R.id.tvSignIn);
        mAuth = FirebaseAuth.getInstance();

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, SigninActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(SignupActivity.this);
                pd.setMessage("Please wait...");
                pd.show();

                String str_name = edtName.getText().toString();
                String str_username = edtUsername.getText().toString();
                String str_number = edtNumber.getText().toString();
                String str_email = edtEmail.getText().toString();
                String str_password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_number) || TextUtils.isEmpty(str_password)){
                    Toast.makeText(SignupActivity.this, "Semua Inputan tidak boleh Kosong!", Toast.LENGTH_SHORT).show();
                }else if (str_password.length() < 6){
                    Toast.makeText(SignupActivity.this, "Password Harus Lebih dari 6 Karakter", Toast.LENGTH_SHORT).show();
                }else {
                    register(str_username, str_name, str_number, str_email, str_password);
                }
            }
        });

    }

    private void register(final String username, final String name, final String number, final String email, final String password){
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String userid = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("id", userid);
                    hashMap.put("username", username);
                    hashMap.put("name", name);
                    hashMap.put("number", number);
                    hashMap.put("bio", "");
                    hashMap.put("imageurl", "https://firebasestorage.googleapis.com/v0/b/daur-ulangki.appspot.com/o/placeholder.png?alt=media&token=ceb7735e-939b-4b12-83c8-cf032e399306");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                pd.dismiss();
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }
                    });
                }else {
                    pd.dismiss();
                    Toast.makeText(SignupActivity.this, " Email dan Password tidak Valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}