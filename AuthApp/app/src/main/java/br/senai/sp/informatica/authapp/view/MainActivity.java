package br.senai.sp.informatica.authapp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import br.senai.sp.informatica.authapp.R;

public class MainActivity extends BaseActivity {

    public EditText edEmail;
    public EditText edSenha;
    public EditText edAuth;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edEmail = findViewById(R.id.etEmail);
        edSenha = findViewById(R.id.etSenha);
        edAuth = findViewById(R.id.etToken);

        mAuth = FirebaseAuth.getInstance();
    }

    public void updateUI(FirebaseUser user){
        if(user != null){
            edAuth.setText(user.getUid());
        }
    }

    public void novoLoginClick(View view){
        String email = edEmail.getText().toString();
        String senha = edSenha.getText().toString();

        showProgressDialog();

        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else{
                            Toast.makeText(MainActivity.this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressDialog();
                    }
                });
    }

    public void loginClick(View view){
        String email = edEmail.getText().toString();
        String senha = edSenha.getText().toString();

        showProgressDialog();

        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else{
                            Toast.makeText(MainActivity.this, "Falha na autenticação", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        hideProgressDialog();
                    }
                });
    }

    public void logoutClick(View view){
        mAuth.signOut();
        updateUI(null);
    }

}

//https://console.firebase.google.com/project/authapp-e7594/authentication/users
//https://developer.android.com/studio/login.html?success=true#
