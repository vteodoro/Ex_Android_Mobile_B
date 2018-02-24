package br.senai.sp.informatica.authapp.model;

import android.support.annotation.NonNull;
import com.firebase.ui.database.FirebaseArray;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import static br.senai.sp.informatica.authapp.model.UsuarioDAO.dao;

public class UsuarioChatArray extends FirebaseArray<Usuario>{

    private String chavePreviaAdicionada;

    public UsuarioChatArray() {
        super(dao.getReference(), new SnapshotParser<Usuario>() {
            @NonNull
            @Override
            public Usuario parseSnapshot(@NonNull DataSnapshot snapshot) {
                return snapshot.getValue(Usuario.class);
            }
        });
    }

    @Override
    public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
        if(previousChildKey == null)
            chavePreviaAdicionada = null;

        if(!snapshot.getKey().equals(dao.getUserId())){
            super.onChildAdded(snapshot, chavePreviaAdicionada);
            chavePreviaAdicionada = snapshot.getKey();
        }
    }

    @Override
    public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {
        if(previousChildKey == null)
            chavePreviaAdicionada = null;

        if(!snapshot.getKey().equals(dao.getUserId())){
            super.onChildChanged(snapshot, chavePreviaAdicionada);
            chavePreviaAdicionada = snapshot.getKey();
        }
    }

    @Override
    public void onChildMoved(DataSnapshot snapshot, String previousChildKey) {
        if(previousChildKey == null)
            chavePreviaAdicionada = null;

        if(!snapshot.getKey().equals(dao.getUserId())){
            super.onChildMoved(snapshot, chavePreviaAdicionada);
            chavePreviaAdicionada = snapshot.getKey();
        }
    }


}
