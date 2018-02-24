package br.senai.sp.informatica.authapp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import br.senai.sp.informatica.authapp.R;
import br.senai.sp.informatica.authapp.lib.CallBackMessage;
import br.senai.sp.informatica.authapp.model.Mensagem;
import br.senai.sp.informatica.authapp.model.MensagemDAO;


public class MensagemActivity extends BaseActivity {
    private MensagemDAO dao;
    private ListView listView;
    private BaseAdapter adapter;
    private String destinatarioId;
    private EditText etDigitar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mensagem);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            destinatarioId = extras.getString("id");
            dao = new MensagemDAO(destinatarioId);
        }

        listView = findViewById(R.id.listView);
        etDigitar = findViewById(R.id.txtMsg);
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(adapter == null){
            FirebaseListOptions<Mensagem> options = new FirebaseListOptions.Builder<Mensagem>()
                    .setQuery(dao.getReference(), Mensagem.class)
                    .setLayout(R.layout.aux_mensagem)
                    .setLifecycleOwner(this)
                    .build();

            adapter = new MensagemAdapter(options);
            listView.setAdapter(adapter);
        }

    }

    public void enviarMsg(View view){
        showProgressDialog();

        Mensagem msg = new Mensagem();
        msg.setMensagem(etDigitar.getText().toString());

        dao.salvar(msg, new CallBackMessage("Falha no envio", this));

        etDigitar.setText("");
        etDigitar.requestFocus();
    }

    public class MensagemAdapter extends FirebaseListAdapter<Mensagem> {
        public MensagemAdapter(@NonNull FirebaseListOptions<Mensagem> options){
            super(options);
            dao.verificarMensagens();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            Mensagem model = getItem(position);
            if(destinatarioId.equals(model.getOrigem())){
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.aux_mensagem2, viewGroup, false);
            }else{
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.aux_mensagem, viewGroup, false);
            }

            return super.getView(position, view, viewGroup);

        }

        @Override
        protected void populateView(View view, Mensagem model, int position){
            hideProgressDialog();
            TextView tvMsg = view.findViewById(R.id.tvMsg);
            tvMsg.setText(model.getMensagem());

        }
    }

}
