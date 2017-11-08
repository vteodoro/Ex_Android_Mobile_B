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
    private final int EDITA_JOGO = 0;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemLista = new JogoAdapter();

        listView = (ListView) findViewById(R.id.lvLista);
        listView.setAdapter(itemLista);
        listView.setOnItemClickListener(this);
        i = new Intent(getBaseContext(), EditarActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.insert){
            i.removeExtra("id");
            startActivityForResult(i, EDITA_JOGO);
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int linha, long id){
        i.putExtra("id", id);
        startActivityForResult(i, EDITA_JOGO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            itemLista.notifyDataSetChanged();
        }
    }

}
