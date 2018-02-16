package br.senai.sp.informatica.apptoken.servico;

import br.senai.sp.informatica.apptoken.model.CEP;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepService {

    @GET("{cep}")
    Call<CEP> buscarCep(@Path("cep") String cep);

}
