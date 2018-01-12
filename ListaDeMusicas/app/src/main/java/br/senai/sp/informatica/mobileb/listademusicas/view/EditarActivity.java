package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import br.senai.sp.informatica.mobileb.listademusicas.R;
import br.senai.sp.informatica.mobileb.listademusicas.lib.Utilitarios;
import br.senai.sp.informatica.mobileb.listademusicas.model.Musica;
import br.senai.sp.informatica.mobileb.listademusicas.model.MusicaDAO;
import br.senai.sp.informatica.mobileb.listademusicas.view.fragment.DateDialog;

public class EditarActivity extends AppCompatActivity {
    private MusicaDAO dao = MusicaDAO.manager;
    private EditText edTitulo;
    private EditText edArtista;
    private EditText edDataInc;
    private EditText edAlbum;
    private Musica musica;
    private MenuItem menuItem;
    private ImageView ivFoto;
    private Calendar calendar = Calendar.getInstance();
    private static final int REQUEST_IMAGE_GALERY = 0;
    private static final int REQUEST_GALERY_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.detalhe_layout));

        edTitulo = (EditText) findViewById(R.id.edTituloDet);
        edArtista = (EditText) findViewById(R.id.edArtistaDet);
        edDataInc = (EditText) findViewById(R.id.edDataIncDet);
        edAlbum = (EditText) findViewById(R.id.edAlbumDet);
        ivFoto = (ImageView) findViewById(R.id.ivAlbum);

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
                    if(musica.getCapa() != null)
                        ivFoto.setImageBitmap(Utilitarios.bitmapFromBase64(musica.getCapa()));
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

        Bitmap bitmap = Utilitarios.bitmapFromImageView(ivFoto);
        if(bitmap != null) {
            byte[] bytes = Utilitarios.bitmapToBase64(bitmap);
            ivFoto.setImageBitmap(Utilitarios.bitmapFromBase64(bytes));
            musica.setCapa(bytes);
        }

        if (edTitulo.getText().toString().isEmpty() || edArtista.getText().toString().isEmpty() ||
                edAlbum.getText().toString().isEmpty() || edDataInc.getText().toString().isEmpty()){
            Toast.makeText(this, "Cadastro incompleto", Toast.LENGTH_SHORT).show();
        } else {
            dao.salvar(musica);
            setResult(Activity.RESULT_OK);
            finish();
        }
    }

    public void calendario(View view) {
        DateDialog.makeDialog(calendar, R.id.edDataIncDet)
                .show(getSupportFragmentManager(), "Data de inclusão");
    }

    public void definirImg(View view){
        abrirGaleria();
    }

    private void abrirGaleria(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        if(intent.resolveActivity(getPackageManager()) != null) {
            if ((ContextCompat.checkSelfPermission(getBaseContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALERY_PERMISSION);
            }else {
                startActivityForResult(Intent.createChooser(intent, "Selecione a foto"), REQUEST_IMAGE_GALERY);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        Bitmap bitmap = Utilitarios.bitmapFromImageView(ivFoto);
        if(bitmap != null){
            outState.putByteArray("foto", Utilitarios.bitmapToBase64(bitmap));
        }else{
            outState.putByteArray("foto", null);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){
        byte[] bytes = saveInstanceState.getByteArray("foto");
        if(bytes != null){
            ivFoto.setImageBitmap(Utilitarios.bitmapFromBase64(bytes));
        }
        super.onRestoreInstanceState(saveInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        boolean autorizado = true;

        for(int resultado : grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                autorizado = false;
                break;
            }
        }

        switch (requestCode){
            case REQUEST_GALERY_PERMISSION:
                if(autorizado)
                    abrirGaleria();
                else
                    Toast.makeText(this, "Acesso à galeria foi negado", Toast.LENGTH_SHORT).show();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_IMAGE_GALERY){
            if(data != null){
                try{
                    Uri imageURI = data.getData();
                    Bitmap bitmap = Utilitarios.setPic(ivFoto.getWidth(), ivFoto.getHeight(), imageURI, this);
                    ivFoto.setImageBitmap(bitmap);
                    ivFoto.invalidate();
                }catch(IOException ex){
                    Toast.makeText(this, "Falha ao abrir foto", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}
















