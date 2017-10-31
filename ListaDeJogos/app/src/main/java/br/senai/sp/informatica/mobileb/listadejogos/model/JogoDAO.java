package br.senai.sp.informatica.mobileb.listadejogos.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by vivi on 31/10/2017.
 */

public class JogoDAO {

    public static JogoDAO manager = new JogoDAO();
    private List<Jogo> lista;
    private long id = 0;

    private JogoDAO(){
        lista = new ArrayList<>();
    }

    public List<Jogo> getLista(){
        return Collections.unmodifiableList(lista);
    }

    public Jogo getJogo(Long id){
        Jogo objJogo = null;
        for(Jogo obj : lista){
            if(obj.getId() == id){
                objJogo = obj;
                break;
            }
        }

//        Jogo jogoLocalizado = lista.get(lista.indexOf(new Jogo(id)));

//        Jogo outroJogo = lista.stream()
//                .filter(obj -> obj.getId() == id)
//                .findAny().orElse(null);

        return objJogo;
    }

    public void salvar (Jogo obj){
        if(obj.getId() != null){
            obj.setId(id++);
            lista.add(obj);
        }else{
            int posicao = lista.indexOf(new Jogo(obj.getId()));
            lista.set(posicao, obj);
        }

    }

    public void remover(Long id){
        lista.remove(new Jogo(id));
    }

}
