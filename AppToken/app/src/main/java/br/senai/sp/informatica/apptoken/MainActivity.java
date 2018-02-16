package br.senai.sp.informatica.apptoken;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.senai.sp.informatica.apptoken.config.RetrofitConfig;
import br.senai.sp.informatica.apptoken.model.CEP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvJson;
    private EditText etCep;
    private Button btnPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJson = findViewById(R.id.tvJson);
        etCep = findViewById(R.id.etCEP);
        btnPesquisar = findViewById(R.id.btnPesquisar);

        btnPesquisar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){}
        });

        Call<CEP> cepCall = new RetrofitConfig().getCepService().buscarCep(etCep.getText().toString());
        cepCall.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if (response.isSuccessful()) {
                    CEP cep = response.body();
                    tvJson.setText(cep.toString());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"erro", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

// POST /login
// http://codexpauth.herokuapp.com/login
// cabecalho?? - Authorization
// String token = response.headers().get("Authorization");
// okhttpclient0
//http://codexpauth.herokuapp.com/home