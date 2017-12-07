package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;
import br.senai.sp.informatica.mobileb.listademusicas.R;

public class MainActivity extends AppCompatActivity implements  EditarMusicas{
    private ListView listView;
    private BaseAdapter itemLista;
    private Intent i;
    private final int EDITA_MUSICA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemLista = new MusicaAdapter(this);
        listView = (ListView) findViewById(R.id.lvLista);
        listView.setAdapter(itemLista);
        i = new Intent(getBaseContext(), EditarActivity.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add){
            i.removeExtra("id");
            startActivityForResult(i, EDITA_MUSICA);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            itemLista.notifyDataSetChanged();
        }
    }

    @Override
    public void executa(long id) {
        i.putExtra("id", id);
        startActivityForResult(i, EDITA_MUSICA);
    }
}