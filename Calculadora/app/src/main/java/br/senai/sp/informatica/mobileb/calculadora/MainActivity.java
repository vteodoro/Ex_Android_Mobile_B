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
    private String auxiliar;
    private double valor;
    private int x;
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
       auxiliar = campo.getText().toString();
   }
     
   public void soma(View view){
	   valor = Double.parseDouble(auxiliar);
       clear(view);
       x = 1;
   }
   
   public void subtracao(View view){
       valor = Double.parseDouble(auxiliar);
       clear(view);
       x = 2;
   }
   
   public void multiplicacao(View view){
       valor = Double.parseDouble(auxiliar);
       clear(view);
       x = 3;
   }
   
   public void divisao(View view){
       valor = Double.parseDouble(auxiliar);
       clear(view);
       x = 4;
   }
   
   public void igual(View view){
       if(x == 1) {
           valor += Double.parseDouble(auxiliar);
           campo.setText(String.valueOf(valor));
       }else if(x == 2){
           valor -= Double.parseDouble(auxiliar);
           campo.setText(String.valueOf(valor));
       }else if(x == 3){
           valor *= Double.parseDouble(auxiliar);
           campo.setText(String.valueOf(valor));
       }else if(x == 4){
           if(valor != 0) {
               valor /= Double.parseDouble(auxiliar);
               campo.setText(String.valueOf(valor));
           }else{
               Toast.makeText(this, "Impossível calcular", Toast.LENGTH_SHORT).show();
               clear(view);
           }
       }
   }





}