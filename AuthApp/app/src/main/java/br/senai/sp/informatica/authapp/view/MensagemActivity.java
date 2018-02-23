package br.senai.sp.informatica.authapp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import br.senai.sp.informatica.authapp.R;
import br.senai.sp.informatica.authapp.model.Mensagem;
import br.senai.sp.informatica.authapp.model.MensagemDAO;


public class MensagemActivity extends BaseActivity {
    private MensagemDAO dao;
    private ListView listView;
    private BaseAdapter adapter;
    private String destinatarioId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: Falta montar o layout para a tela de mensagens
        // TODO: Falta criar o layout para as mensagens recebidas no Chat
        // TODO: Falta finalizar a construção desta classe
        // TODO: Falta criar a activity que mostra os usuarios
        //TODO: ......

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            destinatarioId = extras.getString("id");
            dao = new MensagemDAO(destinatarioId);
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(adapter == null){
            FirebaseListOptions<Mensagem> options = new FirebaseListOptions.Builder<Mensagem>()
                    .setQuery(dao.getReference(), Mensagem.class)
                    .setLayout(R.layout.layout_mensagem)
                    .setLifecycleOwner(this)
                    .build();

            adapter = new MensagemAdapter(options);

            listView.setAdapter(adapter);
        }

    }

    public class MensagemAdapter extends FirebaseListAdapter<Mensagem> {
        public MensagemAdapter(@NonNull FirebaseListOptions<Mensagem> options){
            super(options);
            dao.verificarMensagens();
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            return super.getView(position, view, viewGroup);
        }

        @Override
        protected void populateView(View view, Mensagem model, int position){
            hideProgressDialog();

            TextView tvMsg = view.findViewById(R.id.tvMsg);
            ImageView imgDel = view.findViewById(R.id.imgDel);

            tvMsg.setText(model.getMensagem());
            imgDel.setTag(model.getId());

        }
    }

}
