package br.senai.sp.informatica.mobileb.listademusicas.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.senai.sp.informatica.mobileb.listademusicas.R;
import br.senai.sp.informatica.mobileb.listademusicas.model.Musica;
import br.senai.sp.informatica.mobileb.listademusicas.model.MusicaDAO;

public class MusicaAdapter extends BaseAdapter{
    private MusicaDAO dao = MusicaDAO.manager;
    private Map<Integer, Long> mapa;

    public MusicaAdapter(){ criaMapa();}

    @Override
    public void notifyDataSetChanged() {
        criaMapa();
        super.notifyDataSetChanged();
    }

    private void criaMapa(){
        mapa = new HashMap<>();
        List<Musica> lista = dao.getLista();

        for(int linha = 0; linha < lista.size(); linha ++){
            Musica musica = lista.get(linha);
            mapa.put(linha, musica.getId());
        }
    }

    @Override
    public int getCount() {
        return mapa.size();
    }

    @Override
    public Object getItem(int id) {
        return dao.getMusica((long)id);
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
            inflater.inflate(R.layout.aux_layout, layout);
        }else{
            layout = (ConstraintLayout)view;
        }

        TextView tvTitulo = layout.findViewById(R.id.tvTitulo);
        TextView tvArtista = layout.findViewById(R.id.tvArtista);
        TextView tvAlbum = layout.findViewById(R.id.tvAlbum);
        TextView tvDataInc = layout.findViewById(R.id.tvDataInc);

        Long id = mapa.get(linha);
        Musica musica = dao.getMusica(id);

        tvTitulo.setText(musica.getTitulo());
        tvArtista.setText(musica.getArtista());
        tvAlbum.setText(musica.getAlbum());
        tvDataInc.setText(musica.getDtInclusao());

        return layout;
    }
}
