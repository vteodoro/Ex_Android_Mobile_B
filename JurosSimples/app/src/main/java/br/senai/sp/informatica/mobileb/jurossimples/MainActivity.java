package br.senai.sp.informatica.mobileb.jurossimples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{
    private EditText edCapital;
    private EditText edJuros;
    private EditText edMeses;
    private EditText edCapResultante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edCapital = (EditText)findViewById(R.id.edCapital);
        edJuros = (EditText)findViewById(R.id.edJuros);
        edMeses = (EditText)findViewById(R.id.edMeses);
        edCapResultante = (EditText)findViewById(R.id.edCapResultante);
    }

    public void calcular(View v) {

        double capital = Double.parseDouble(edCapital.getText().toString());
        double juros = Double.parseDouble(edJuros.getText().toString());
        double meses = Double.parseDouble(edMeses.getText().toString());
        double capResult;

        capResult = capital * juros / 100 * meses + capital;


        edCapResultante.setText(String.valueOf(capResult));
    }

    public void limpar(View v){
        edCapital.setText(null);
        edJuros.setText(null);
        edMeses.setText(null);
        
    }
}
