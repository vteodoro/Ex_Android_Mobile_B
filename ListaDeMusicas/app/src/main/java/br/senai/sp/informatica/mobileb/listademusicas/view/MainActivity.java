package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import br.senai.sp.informatica.mobileb.listademusicas.R;

public class MainActivity extends AppCompatActivity implements  EditarMusicas, AdapterView.OnItemClickListener, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener{
    private ListView listView;
    private BaseAdapter itemLista;
    private Intent i;
    private final int EDITA_MUSICA = 0;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemLista = new MusicaAdapter(this);
        listView = (ListView) findViewById(R.id.lvLista);
        listView.setAdapter(itemLista);
        i = new Intent(getBaseContext(), EditarActivity.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, "Open navigation drawer", "Close navigation drawer");
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}