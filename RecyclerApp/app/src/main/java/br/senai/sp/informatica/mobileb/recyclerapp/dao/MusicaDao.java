package br.senai.sp.informatica.mobileb.recyclerapp.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.senai.sp.informatica.mobileb.recyclerapp.model.Musica;

public class MusicaDao {
    public static MusicaDao manager = new MusicaDao();

    private List<Musica> lista;

    private long id = 0;

    private MusicaDao(){
        lista = new ArrayList<>();
        lista.add(new Musica(id++, "Run", "Foo Fighters"));
        lista.add(new Musica(id++, "Revenge", "Papa Roach"));
        lista.add(new Musica(id++, "Exist", "A7x"));
    }

    public List<Musica> getLista(){
        Collections.sort(lista);
        return Collections.unmodifiableList(lista);
    }
}
