package br.senai.sp.informatica.mobileb.listadejogos.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.informatica.mobileb.listadejogos.R;
import br.senai.sp.informatica.mobileb.listadejogos.model.Jogo;
import br.senai.sp.informatica.mobileb.listadejogos.model.JogoDAO;

public class EditarActivity extends AppCompatActivity {
        private JogoDAO dao = JogoDAO.manager;
        private EditText edJogo;
        private EditText edGenero;
        private Jogo jogo;
        private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        edJogo = (EditText) findViewById(R.id.edJogoEditar);
        edGenero = (EditText) findViewById(R.id.edGeneroEditar);

        Intent intent = getIntent();
        if(intent != null) {
            Bundle dados = intent.getExtras();
            if(dados != null) {
                long id = dados.getLong("id");
                jogo = dao.getJogo(id);
                if(jogo != null) {
                    edJogo.setText(jogo.getNome());
                    edGenero.setText(jogo.getGenero());
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.insert);
        menuItem.setVisible(false);
        menuItem = menu.findItem(R.id.save);
        menuItem.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save) {
            salvarJogo();
        }
        return true;
    }

    public void salvarJogo(){
        if(jogo == null) {
            jogo = new Jogo();
        }
        jogo.setNome(edJogo.getText().toString());
        jogo.setGenero(edGenero.getText().toString());
        if(edJogo.getText().toString().isEmpty() || edGenero.getText().toString().isEmpty()){
            Toast.makeText(this, "Cadastro incompleto", Toast.LENGTH_SHORT).show();
        }else {
            dao.salvar(jogo);
            setResult(Activity.RESULT_OK);
            finish();
        }
    }


}
