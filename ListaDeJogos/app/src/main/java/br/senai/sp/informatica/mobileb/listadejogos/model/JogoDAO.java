package br.senai.sp.informatica.mobileb.listadejogos.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vivi on 31/10/2017.
 */

public class JogoDAO {
    public static JogoDAO manager = new JogoDAO();
    private List<Jogo> lista;
    private long id = 0;

    private JogoDAO() {
        lista = new ArrayList<>();
        lista.add(new Jogo(id++, "Mortal Kombat", "Luta"));
        lista.add(new Jogo(id++, "Campo Minado", "Puzzle"));
        lista.add(new Jogo(id++, "Left 4 Dead", "Survivor Horror"));
        lista.add(new Jogo(id++, "GTA V", "TPS"));
        lista.add(new Jogo(id++, "Forza MotorSport 7", "Corrida"));
        lista.add(new Jogo(id++, "Obscure 2 - The Aftermath", "Survivor Horror"));
        lista.add(new Jogo(id++, "Devil May Cry", "Hack and Slash"));

    }

    public List<Jogo> getLista() {
        return Collections.synchronizedList(lista);
    }

    public Jogo getJogo(final Long id) {
        Jogo oJogo = null;
        for(Jogo obj : lista) {
            if(obj.getId() == id) {
                oJogo = obj;
                break;
            }
        }

        Jogo jogoLocalizado = lista.get(lista.indexOf(new Jogo(id)));
        return oJogo;
    }

    public void salvar(Jogo obj) {
        if(obj.getId() != null) {
            obj.setId(id++);
            lista.add(obj);
        } else {
            int posicao = lista.indexOf(new Jogo(obj.getId()));
            lista.set(posicao, obj);
        }
    }

    public void remover(Long id) {
        lista.remove(new Jogo(id));
    }

}