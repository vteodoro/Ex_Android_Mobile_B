package br.senai.sp.informatica.authapp.view;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import br.senai.sp.informatica.authapp.R;
import br.senai.sp.informatica.authapp.model.Mensagem;

public class MensagemAdapter extends FirebaseListAdapter<Mensagem>{
    public MensagemAdapter(@NonNull FirebaseListOptions<Mensagem> options){
        super(options);
        dao.verificarMensagens();
    }

    @Override
    protected void populateView(View view, Mensagem model, int position){
        //hideProgressDialog();

        TextView tvMsg = view.findViewById(R.id.tvMsg);
        ImageView imgDel = view.findViewById(R.id.imgDel);

        tvMsg.setText(model.getMensagem());
        imgDel.setTag(model.getId());

    }
}
