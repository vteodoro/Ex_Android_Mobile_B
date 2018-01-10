package br.senai.sp.informatica.mobileb.recyclerapp.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import br.senai.sp.informatica.mobileb.recyclerapp.R;
import br.senai.sp.informatica.mobileb.recyclerapp.model.Musica;
import br.senai.sp.informatica.mobileb.recyclerapp.view.adapter.MusicaAdapter;

public class MusicaViewHolder extends RecyclerView.ViewHolder{

    public final TextView titulo;
    public final TextView artista;
    private Long musicaId;
    public final MusicaAdapter adapter;

    public MusicaViewHolder(final View view, final MusicaAdapter adapter){
        super(view);
        this.adapter = adapter;
        titulo = view.findViewById(R.id.tvTitulo);
        artista = view.findViewById(R.id.tvArtista);
    }

    public void preencher(Musica musica){}
}
