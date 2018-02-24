package br.senai.sp.informatica.authapp.model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.senai.sp.informatica.authapp.lib.DataCalback;

public class UsuarioDAO {
    public static UsuarioDAO dao = new UsuarioDAO();
    private DatabaseReference base;

    private UsuarioDAO(){
        base = FirebaseDatabase.getInstance().getReference();
    }

    public String getUserId() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public DatabaseReference getReference() {
        return base.child("usuarios");
    }

    public void salvar(Usuario obj, DatabaseReference.CompletionListener callback){
        DatabaseReference ref = getReference().child(getUserId());
        ref.child("id").setValue(obj.getId());
        ref.child("email").setValue(obj.getEmail());
        ref.child("logado").setValue(obj.isLogado());
        ref.child("token").setValue(obj.getToken());
    }

    public void verificarMensagens(){
        getReference().addListenerForSingleValueEvent(new DataCalback());
    }

}
