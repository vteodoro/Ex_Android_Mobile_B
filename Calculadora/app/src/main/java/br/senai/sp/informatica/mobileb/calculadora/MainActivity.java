package br.senai.sp.informatica.mobileb.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private EditText campo;
    private Button botao, op;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       campo = (EditText) findViewById(R.id.edResultado);
   }
   public void clear(View view){ campo.setText(null); }
   public void numeros(View view){
       botao = (Button)view;
       String num = botao.getText().toString();
       if(num.equals(",") && campo.getText().toString().isEmpty()){
           campo.setText(campo.getText() + "0" + num);
       }
       else if(campo.getText().toString().contains(",") && num.equals(",")){
           num = "";
           campo.setText(campo.getText() + num);
       }
       else if(campo.getText().toString().startsWith("0") && !campo.getText().toString().startsWith("0,") && !num.equals(",")){
           campo.setText(num);
       }
       else { campo.setText(campo.getText() + num); }
   }
   public void operacoes(View view){
	   String campoAuxiliar;
	   op = (Button)view;
	   String operacao = op.getText().toString();
	   while(!operacao.equals("=")){
			if(campo.getText().toString().isEmpty()){
				Toast.makeText(this, "Digite um numero", LENGHT.SHORT).show();
			}
			else{
				Toast.makeText(this, "campo não está vazio", LENGHT.SHORT).show();
				// campoAuxiliar = campo.getText();
				// campo.setText(campo.getText() + " " + operacao);
			}
		   
		   
	   }
	   
	   if(operacao.equals("=")){
		   Toast.makeText(this, "Digitou = ", LENGHT.SHORT).show();
	   }
	   
   }
}