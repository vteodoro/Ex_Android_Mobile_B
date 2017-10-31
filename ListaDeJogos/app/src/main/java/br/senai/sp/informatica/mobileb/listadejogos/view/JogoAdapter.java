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
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
