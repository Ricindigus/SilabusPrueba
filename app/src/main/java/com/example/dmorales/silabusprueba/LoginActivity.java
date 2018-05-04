package com.example.dmorales.silabusprueba;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private Button btnIngresar;
    private EditText edtUsuario;
    private EditText edtPassword;
    private FirebaseAuth firebaseAuth;
    private final String TAG = "FIREBASE AUTENTICACION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIngresar = (Button) findViewById(R.id.login_btnIngresar);
        edtPassword = (EditText) findViewById(R.id.login_edtPassword);
        edtUsuario = (EditText) findViewById(R.id.login_edtUsuario);

        firebaseAuth = FirebaseAuth.getInstance();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion(edtUsuario.getText().toString(),edtPassword.getText().toString());
            }
        });

    }

    public void iniciarSesion(String usuario, String password){
        firebaseAuth.signInWithEmailAndPassword(usuario+"@sistemas.edu.pe",password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            sesionActiva(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            sesionActiva(null);
                        }

                        // ...
                    }
                });
    }


    public void sesionActiva(FirebaseUser user){
        if(user != null){
            Intent intent =  new Intent(LoginActivity.this, CursosActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        sesionActiva(currentUser);
    }
}
