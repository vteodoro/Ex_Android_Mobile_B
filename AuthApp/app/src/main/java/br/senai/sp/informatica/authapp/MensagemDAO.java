package br.senai.sp.informatica.authapp;

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

    }

    public void remover(String string, CallBackMessage call){}

    public void verificarMensagens(){}

    public void getId(){}

    public void getMensagem(){}
}
