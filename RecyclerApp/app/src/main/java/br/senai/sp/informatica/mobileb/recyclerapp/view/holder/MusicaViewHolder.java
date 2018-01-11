package br.senai.sp.informatica.mobileb.recyclerapp.view.holder;

import android.app.Activity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import br.senai.sp.informatica.mobileb.recyclerapp.R;
import br.senai.sp.informatica.mobileb.recyclerapp.model.Musica;
import br.senai.sp.informatica.mobileb.recyclerapp.view.adapter.MusicaAdapter;

public class MusicaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public final TextView titulo;
    public final TextView artista;
    private Long musicaId;
    public final MusicaAdapter adapter;

    public MusicaViewHolder(final View view, final MusicaAdapter adapter){
        super(view);
        this.adapter = adapter;

        view.setOnClickListener(this);
        view.setOnClickListener(this);

        titulo = view.findViewById(R.id.tvTitulo);
        artista = view.findViewById(R.id.tvArtista);
    }

    public void preencher(Musica musica){
        musicaId = musica.getId();
        titulo.setText(musica.getTitulo());
        artista.setText(musica.getArtista());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), musicaId.toString(), Toast.LENGTH_SHORT).show();
        Log.d("Musica selecionada", musicaId.toString());
    }

    @Override
    public boolean onLongClick(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.musica_options, popupMenu.getMenu());

        final Activity context = (Activity)view.getContext();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            public boolean onMenuItemClick(MenuItem item){
                switch(item.getItemId()){

                    case R.id.menuMusEditar:
                        break;

                    case R.id.menuMusDeletar:
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
        return false;

    }
}
