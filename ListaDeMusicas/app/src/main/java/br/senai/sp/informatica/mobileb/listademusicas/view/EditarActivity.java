package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.senai.sp.informatica.mobileb.listademusicas.R;
import br.senai.sp.informatica.mobileb.listademusicas.model.Musica;
import br.senai.sp.informatica.mobileb.listademusicas.model.MusicaDAO;

public class EditarActivity extends AppCompatActivity{
    private MusicaDAO dao = MusicaDAO.manager;
    private TextView edTitulo;
    private TextView edArtista;
    private TextView edDataInc;
    private Musica musica;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.detalhe_layout));

        edTitulo = (TextView) findViewById(R.id.tvTituloDet);
        edArtista = (TextView) findViewById(R.id.tvArtistaDet);
        edDataInc = (TextView) findViewById(R.id.tvDataIncDet);

        Intent intent = getIntent();
        if(intent != null){
            Bundle dados = intent.getExtras();
            if(dados != null){
                long id =  dados.getLong("id");
                musica = dao.getMusica(id);
                if(musica != null){
                    edTitulo.setText(musica.getTitulo());
                    edArtista.setText(musica.getArtista());
                    edDataInc.setText(musica.getDtInclusao());
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem = menu.findItem(R.id.edit);
        menuItem.setVisible(false);
        return true;
    }

    public void salvarMusica(View view){
        if(musica == null){
            musica = new Musica();
        }
        musica.setTitulo(edTitulo.getText().toString());
        musica.setArtista(edArtista.getText().toString());
        dao.salvar(musica);
        setResult(Activity.RESULT_OK);

    }

}
