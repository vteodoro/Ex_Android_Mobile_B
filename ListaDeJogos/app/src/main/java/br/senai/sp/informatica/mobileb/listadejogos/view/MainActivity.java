package br.senai.sp.informatica.mobileb.listadejogos.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.senai.sp.informatica.mobileb.listadejogos.R;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private BaseAdapter itemLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemLista = new JogoAdapter();

        listView = (ListView) findViewById(R.id.lvLista);
        listView.setAdapter(new JogoAdapter());
    }
}
