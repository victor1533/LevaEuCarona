package com.orochi.utfpr.levaeu.Activitys.AdaptersListView;

/**
 * Created by Poisson on 11/11/2015.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.Escopo.Local;
import com.orochi.utfpr.levaeu.R;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;


public class AdapterCaronaListView extends BaseAdapter implements Serializable {
    private LayoutInflater mInflater;
    private List<Carona> itens;
    private Context contexto;

    public AdapterCaronaListView(Context contexto, List<Carona> advs) {
        this.itens = advs;
        this.contexto = contexto;
        mInflater = LayoutInflater.from(contexto);
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Carona getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_list_carona, null);
            ButterKnife.bind(this, view);

            itemHolder = new ItemSuporte();
            itemHolder.txtDestino = (EditText) view.findViewById(R.id.txtDestino);
            itemHolder.txtOrigem = (EditText) view.findViewById(R.id.txtOrigem);
            itemHolder.txtNumVagas = (TextView) view.findViewById(R.id.txtVagasDisp);
            itemHolder.txtHoraSaida = (TextView) view.findViewById(R.id.txtHoraSaida);
            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        final Carona carona = itens.get(position);
        Local origem = carona.getOrigem();
        Local destino = carona.getDestino();

        itemHolder.txtOrigem.setText(origem.getNomeLocal());
        itemHolder.txtDestino.setText(destino.getNomeLocal());
        itemHolder.txtNumVagas.setText("" + carona.getNumVagasDisponiveis());
        itemHolder.txtHoraSaida.setText(Datas.DateToHHmm(carona.getDataHoraPartida()) + "h");
        return view;
    }

    private class ItemSuporte {
        EditText txtOrigem;
        EditText txtDestino;
        TextView txtHoraSaida;
        TextView txtNumVagas;
    }


}
