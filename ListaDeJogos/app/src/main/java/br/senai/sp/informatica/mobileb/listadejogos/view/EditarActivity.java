package br.senai.sp.informatica.mobileb.listadejogos.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import br.senai.sp.informatica.mobileb.listadejogos.R;
import br.senai.sp.informatica.mobileb.listadejogos.model.JogoDAO;

public class EditarActivity extends AppCompatActivity {
    private JogoDAO dao = JogoDAO.manager;
//    private EditText edJogo =

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);



    }


}
