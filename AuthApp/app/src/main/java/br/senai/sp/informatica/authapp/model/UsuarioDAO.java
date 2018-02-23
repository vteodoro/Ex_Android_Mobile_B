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
    private DatabaseReference base;
    private FirebaseUser user;
    private String destinatarioId;

    public UsuarioDAO(String destinatarioId){
        user = FirebaseAuth.getInstance().getCurrentUser();
        base = FirebaseDatabase.getInstance().getReference();
        this.destinatarioId = destinatarioId;
    }

    public DatabaseReference getReference() {
        return base.child("mensagens").child(user.getUid()).child(destinatarioId);
    }

    private String makeRefOrigem(String id) {
        return "/mensagens/" + user.getUid() + "/" + destinatarioId + "/" + id;
    }

    private String makeRefDestino(String id) {
        return "/mensagens/" + destinatarioId + "/" + user.getUid() + "/" + id;
    }

    public void salvar(Mensagem obj, DatabaseReference.CompletionListener callback){
        if(obj.getId() == null){
            obj.setId(getReference().push().getKey());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", obj.getId());
        map.put("mensagem", obj.getMensagem());
        map.put("origem", user.getUid());
        map.put("data", new Date().getTime());

        Map<String, Object> updates = new HashMap<>();
        updates.put(makeRefOrigem(obj.getId()), map);
        updates.put(makeRefDestino(obj.getId()), map);

        base.updateChildren(updates, callback);
    }

    public void verificarMensagens(){
        getReference().addListenerForSingleValueEvent(new DataCalback());
    }

}
