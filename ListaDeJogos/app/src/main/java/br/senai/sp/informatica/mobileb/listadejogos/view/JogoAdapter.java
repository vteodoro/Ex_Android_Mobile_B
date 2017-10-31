package br.senai.sp.informatica.mobileb.listadejogos.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import br.senai.sp.informatica.mobileb.listadejogos.model.JogoDAO;

/**
 * Created by 45948375811 on 31/10/2017.
 */

public class JogoAdapter extends BaseAdapter{
    private JogoDAO dao = JogoDAO.manager;

    @Override
    public int getCount() {
        return dao.getLista().size();
    }

    @Override
    public Object getItem(int id) {
        return dao.getLista();
    }

    @Override
    public long getItemId(int pos) {
        return dao.getLista().get(pos).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
