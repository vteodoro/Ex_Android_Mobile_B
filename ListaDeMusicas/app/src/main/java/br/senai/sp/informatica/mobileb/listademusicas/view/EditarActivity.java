package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import br.senai.sp.informatica.mobileb.listademusicas.R;
import br.senai.sp.informatica.mobileb.listademusicas.model.Musica;
import br.senai.sp.informatica.mobileb.listademusicas.model.MusicaDAO;

public class EditarActivity extends AppCompatActivity {
    private MusicaDAO dao = MusicaDAO.manager;
    private TextView edTitulo;
    private TextView edArtista;
    private TextView edDataInc;
    private TextView edAlbum;
    private Musica musica;
    private MenuItem menuItem;
    private ImageButton btnData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.detalhe_layout));

        edTitulo = (TextView) findViewById(R.id.edTituloDet);
        edArtista = (TextView) findViewById(R.id.edArtistaDet);
        edDataInc = (TextView) findViewById(R.id.edDataIncDet);
        edAlbum = (TextView) findViewById(R.id.edAlbumDet);

       // btnData = (ImageButton) findViewById(R.id.dtPicker);

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

        if(edTitulo.getText().toString().isEmpty() || edArtista.getText().toString().isEmpty() || edAlbum.getText().toString().isEmpty() || edDataInc.getText().toString().isEmpty()){
            Toast.makeText(this, "Cadastro incompleto", Toast.LENGTH_SHORT).show();
        }else {
            dao.salvar(musica);
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

//    public void showDatePickerDialog(View v) {
//        DialogFragment newFragment = new DatePickerFragment();
//        newFragment.show(getSupportFragmentManager(), "datePicker");
//    }

}