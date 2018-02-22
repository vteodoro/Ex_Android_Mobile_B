package br.senai.sp.informatica.authapp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.senai.sp.informatica.authapp.R;
import br.senai.sp.informatica.authapp.model.MensagemDAO;

public class MainActivity extends AppCompatActivity {
    MensagemDAO dao;
    MensagemAdapter adapter;

    public EditText edEmail;
    public EditText edSenha;
    public EditText edAuth;
    public ListView listView;

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

        //showProgressDialog();

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
                        //hideProgressDialog();
                    }
                });
    }

    public void loginClick(View view){
        String email = edEmail.getText().toString();
        String senha = edSenha.getText().toString();

        //showProgressDialog();

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
                        //hideProgressDialog();
                    }
                });
    }

    public void logoutClick(View view){
        mAuth.signOut();
        updateUI(null);
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(adapter == null){
            FirebaseListOptions<MensagemDAO> options = new FirebaseListOptions.Builder<MensagemDAO>()
                    .setQuery(dao.getReference(), MensagemDAO.class)
                    .setLayout(R.layout.layout_mensagem)
                    .setLifecycleOwner(this)
                    .build();

            adapter = new MensagemAdapter(options);

            listView.setAdapter(adapter);
        }

    }

}

//https://console.firebase.google.com/project/authapp-e7594/authentication/users
//https://developer.android.com/studio/login.html?success=true#
