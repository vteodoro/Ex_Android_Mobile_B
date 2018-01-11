package br.senai.sp.informatica.mobileb.recyclerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.senai.sp.informatica.mobileb.recyclerapp.dao.MusicaDao;
import br.senai.sp.informatica.mobileb.recyclerapp.model.Musica;
import br.senai.sp.informatica.mobileb.recyclerapp.view.adapter.MusicaAdapter;

public class MainActivity extends AppCompatActivity {

    private MusicaDao dao = MusicaDao.manager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Musica> musicas = dao.getLista();

        recyclerView = findViewById(R.id.rvMusicas);

        recyclerView.setAdapter(new MusicaAdapter(musicas, this));

        RecyclerView.LayoutManager layout = new GridLayoutManager(this, 3);
        //RecyclerView.LayoutManager layout2 = new GridLayoutManager(this, 5);

        recyclerView.setLayoutManager(layout);
    }
}
