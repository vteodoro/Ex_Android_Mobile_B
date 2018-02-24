package br.senai.sp.informatica.authapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;

import br.senai.sp.informatica.authapp.R;
import br.senai.sp.informatica.authapp.model.Usuario;
import br.senai.sp.informatica.authapp.model.UsuarioChatArray;
import br.senai.sp.informatica.authapp.model.UsuarioDAO;

public class UsuarioActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private UsuarioDAO dao = UsuarioDAO.dao;
    private ListView listView;
    private UsuarioAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_usuario);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(adapter == null){
            FirebaseListOptions<Usuario> options = new FirebaseListOptions.Builder<Usuario>()
                    .setSnapshotArray(new UsuarioChatArray())
                    .setLayout(R.layout.aux_usuario)
                    .setLifecycleOwner(this)
                    .build();

            adapter = new UsuarioAdapter(options);
            listView.setAdapter(adapter);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String destinatarioID = adapter.getItem(position).getId();
        Intent i = new Intent(this, MensagemActivity.class);
        i.putExtra("id", destinatarioID);
        startActivity(i);
    }

    public class UsuarioAdapter extends FirebaseListAdapter<Usuario> {
        public UsuarioAdapter(@NonNull FirebaseListOptions<Usuario> options){
            super(options);
            dao.verificarMensagens();
        }

        @Override
        protected void populateView(View view, Usuario model, int position){
            hideProgressDialog();
            TextView tvMsg = view.findViewById(R.id.tvMsg);
            tvMsg.setText(model.getEmail());

        }
    }

}
