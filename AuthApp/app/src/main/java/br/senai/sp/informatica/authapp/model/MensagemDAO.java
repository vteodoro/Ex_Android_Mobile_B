package br.senai.sp.informatica.authapp.model;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class MensagemDAO {

    public MensagemDAO(){}

    public void salvar(MensagemDAO obj, DatabaseReference.CompletionListener callback){
        if(obj.getId() == null){
            obj.setId(reference.push().getKey());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", obj.getId());
        map.put("mensagem", obj.getMensagem());

        Map<String, Object> updates = new HashMap<>();
        updates.put("/mensagens/" + user.getUid() + "/" + obj.getId(), map);

        base.updateChildren(updates, callback);

    }

    public void remover(String string, DatabaseReference.CompletionListener callback){
        Map<String, Object> updates = new HashMap<>();
        updates.put("/mensagens/" + user.getUid() + "/" + id, null);

        base.updateChildren(updates, callback);
    }

    public void verificarMensagens(){}

    public void getId(){}

    public void getMensagem(){}
}
