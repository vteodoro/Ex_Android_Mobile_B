package br.senai.sp.informatica.apptoken.config;

import br.senai.sp.informatica.apptoken.servico.CepService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://api.postmon.com.br/v1/cep/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CepService getCepService() {
        return this.retrofit.create(CepService.class);
    }

}
