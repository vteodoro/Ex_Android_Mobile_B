package br.senai.sp.informatica.authapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;

public class MensagemAdapter extends FirebaseListAdapter<MensagemDAO>{
    MensagemDAO dao;

    public MensagemAdapter(@NonNull FirebaseListOptions<MensagemDAO> options){
        super(options);
        dao.verificarMensagens();
    }

    @Override
    protected void populateView(View view, MensagemDAO model, int position){
        //hideProgressDialog();

        TextView tvMsg = view.findViewById(R.id.tvMsg);
        ImageView imgDel = view.findViewById(R.id.imgDel);

        tvMsg.setText(model.getMensagem());
        imgDel.setTag(model.getId());
        imgDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view){
                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                alerta.setMessage("Deseja apagar mensagem?");
                alerta.setNegativeButton("Não", null);
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.remover((String)view.getTag(), new CallBackMessage("Falha ao apagar.");
                    }
                });
                alerta.create();
                alerta.show();
            }

        });

    }
}