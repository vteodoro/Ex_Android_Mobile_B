package br.senai.sp.informatica.mobileb.recyclerapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import br.senai.sp.informatica.mobileb.recyclerapp.R;
import br.senai.sp.informatica.mobileb.recyclerapp.model.Musica;
import br.senai.sp.informatica.mobileb.recyclerapp.view.holder.MusicaViewHolder;

public class MusicaAdapter extends RecyclerView.Adapter{

    private List<Musica> musicas;
    private Context context;

    public MusicaAdapter(List<Musica> musicas, Context context){
        this.musicas = musicas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.musica_item_lista, parent, false);
        MusicaViewHolder holder = new MusicaViewHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MusicaViewHolder viewHolder = (MusicaViewHolder) holder;

        Musica musica = musicas.get(position);
        ((MusicaViewHolder)holder).preencher(musica);
    }

    @Override
    public int getItemCount() {
        return musicas.size();
    }
}
