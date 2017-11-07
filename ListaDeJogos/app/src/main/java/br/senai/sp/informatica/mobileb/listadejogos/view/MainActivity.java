package br.senai.sp.informatica.mobileb.listadejogos.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import br.senai.sp.informatica.mobileb.listadejogos.R;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private BaseAdapter itemLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemLista = new JogoAdapter();

        listView = (ListView) findViewById(R.id.lvLista);
        listView.setAdapter(new JogoAdapter());
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.insert:
                startActivity(new Intent(this, CriarActivity.class));
                break;
            case R.id.delete:
                startActivity(new Intent(this, DeletarActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int linha, long id){
        Intent i = new Intent(getBaseContext(), EditarActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}
