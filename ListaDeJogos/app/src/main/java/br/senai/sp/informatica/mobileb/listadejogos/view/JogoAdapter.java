package br.senai.sp.informatica.mobileb.listadejogos.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.senai.sp.informatica.mobileb.listadejogos.R;
import br.senai.sp.informatica.mobileb.listadejogos.model.Jogo;
import br.senai.sp.informatica.mobileb.listadejogos.model.JogoDAO;

class JogoAdapter extends BaseAdapter implements View.OnClickListener{
    private JogoDAO dao = JogoDAO.manager;
    private Map<Integer, Long> mapa;
    private EditarJogos delegate;

    public JogoAdapter(EditarJogos edJ){
        criaMapa();
        delegate = edJ;
    }

    @Override
    public void notifyDataSetChanged(){
        criaMapa();
        super.notifyDataSetChanged();
    }

    private void criaMapa(){
        mapa = new HashMap<>();
        List<Jogo> lista = dao.getLista();

        for(int linha = 0; linha < lista.size(); linha ++){
            Jogo jogo = lista.get(linha);
            mapa.put(linha, jogo.getId());
        }
    }

    @Override
    public int getCount() {
        return mapa.size();
    }

    @Override
    public Object getItem(int id) {
        return dao.getJogo((long)id);
    }

    @Override
    public long getItemId(int linha) {
        return mapa.get(linha);
    }

    @Override
    public View getView(int linha, View view, ViewGroup viewGroup) {

        ConstraintLayout layout;
        if(view == null){
            Context ctx = viewGroup.getContext();
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = new ConstraintLayout(ctx);
            inflater.inflate(R.layout.detalhe_layout, layout);
        }else{
            layout = (ConstraintLayout)view;
        }

        TextView tvJogo = layout.findViewById(R.id.tvJogo);
        TextView tvGenero = layout.findViewById(R.id.tvGenero);
        Long id = mapa.get(linha);
        Jogo jogo = dao.getJogo(id);
        tvJogo.setText(jogo.getNome());
        tvGenero.setText(jogo.getGenero());

        SwipeLayout swipeLayout = layout.findViewById(R.id.swipe_delete);
        swipeLayout.close();
        ImageView apagar = layout.findViewById(R.id.trash);
        ImageView editar = layout.findViewById(R.id.edit);
        editar.setOnClickListener(this);
        editar.setTag(jogo.getId());
        apagar.setOnClickListener(this);
        apagar.setTag(jogo.getId());

        return layout;
    }

    @Override
    public void onClick(final View view) {
        final Long id = (Long) view.getTag();
        int idView = view.getId();
        if(idView == R.id.trash){
            AlertDialog.Builder alerta = new AlertDialog.Builder(view.getContext());
            alerta.setMessage("Deseja excluir o jogo?");
            alerta.setNegativeButton("Não", null);
            alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dao.remover(id);
                    Toast.makeText(view.getContext(), "Jogo excluído", Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
            alerta.create();
            alerta.show();
        }else {
            delegate.executa(id);
        }
    }
}