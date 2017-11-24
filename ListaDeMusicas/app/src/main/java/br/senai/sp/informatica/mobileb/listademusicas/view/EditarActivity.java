package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import br.senai.sp.informatica.mobileb.listademusicas.R;
import br.senai.sp.informatica.mobileb.listademusicas.model.Musica;
import br.senai.sp.informatica.mobileb.listademusicas.model.MusicaDAO;

public class EditarActivity extends AppCompatActivity {
    private MusicaDAO dao = MusicaDAO.manager;
    private EditText edTitulo;
    private EditText edArtista;
    private EditText edDataInc;
    private EditText edAlbum;
    private Musica musica;
    private MenuItem menuItem;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.detalhe_layout));

        edTitulo = (EditText) findViewById(R.id.edTituloDet);
        edArtista = (EditText) findViewById(R.id.edArtistaDet);
        edDataInc = (EditText) findViewById(R.id.edDataIncDet);
        edAlbum = (EditText) findViewById(R.id.edAlbumDet);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle dados = intent.getExtras();
            if (dados != null) {
                long id = dados.getLong("id");
                musica = dao.getMusica(id);
                if (musica != null) {
                    edTitulo.setText(musica.getTitulo());
                    edArtista.setText(musica.getArtista());
                    edDataInc.setText(musica.getDtInclusao());
                    edAlbum.setText(musica.getAlbum());
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        menuItem = menu.findItem(R.id.add);
        menuItem.setVisible(false);
        menuItem = menu.findItem(R.id.save);
        menuItem.setVisible(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save) {
            salvarMusica();
        }
        return true;
    }

    public void salvarMusica() {
        if (musica == null) {
            musica = new Musica();
        }
        musica.setTitulo(edTitulo.getText().toString());
        musica.setArtista(edArtista.getText().toString());
        musica.setAlbum(edAlbum.getText().toString());
        musica.setDtInclusao(edDataInc.getText().toString());

        if (edTitulo.getText().toString().isEmpty() || edArtista.getText().toString().isEmpty() || edAlbum.getText().toString().isEmpty() || edDataInc.getText().toString().isEmpty()) {
            Toast.makeText(this, "Cadastro incompleto", Toast.LENGTH_SHORT).show();
        } else {
            dao.salvar(musica);
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    public void calendario(View view) {
        DateDialog.makeDialog(calendar, edDataInc)
                .show(getSupportFragmentManager(), "Data de inclusão");
    }
}