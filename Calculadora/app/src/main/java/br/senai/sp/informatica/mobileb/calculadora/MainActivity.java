package br.senai.sp.informatica.mobileb.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private EditText campo;
    private Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campo = (EditText) findViewById(R.id.edResultado);
    }

    public void clear(View view){
        campo.setText(null);
        if(!botao.findViewById(R.id.btnVirgula).isEnabled()) {
            botao.findViewById(R.id.btnVirgula).setEnabled(false);
        }
    }

    public void numeros(View view){
        botao = (Button)view;
        String num = botao.getText().toString();

        if(num.equals(",") && campo.getText().toString().isEmpty()){
            campo.setText(campo.getText() + "0" + num);
            botao.findViewById(R.id.btnVirgula).setEnabled(false);
        }
        else if(num.equals(",")){
            campo.setText(campo.getText() + num);
            botao.findViewById(R.id.btnVirgula).setEnabled(false);
        }
        else if(campo.getText().toString().startsWith("0") && !campo.getText().toString().startsWith("0,")){
            campo.setText(num);
        }
        else {
            campo.setText(campo.getText() + num);
        }
    }

    public void operacoes(View view){}

}