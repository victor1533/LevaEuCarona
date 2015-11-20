package com.orochi.utfpr.levaeu.Activitys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Escopo.Carona;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Joo on 10/09/2015.
 */
public class CaronaListView extends BaseAdapter implements Serializable
{

        private LayoutInflater mInflater;
        private ArrayList<Carona> itens;
        private Context contexto;

        public CaronaListView(Context contexto, ArrayList<Carona> carona){
        this.itens = carona;
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
        if(view == null){
            view = mInflater.inflate(R.layout.item_list_carona,null);
            itemHolder = new ItemSuporte();
            itemHolder.orgDest = (TextView) view.findViewById(R.id.origemDestino);
            itemHolder.horario = (TextView) view.findViewById(R.id.horarioSaida);
            view.setTag(itemHolder);
        }else{
            itemHolder = (ItemSuporte) view.getTag();
        }
        Carona carona = itens.get(position);
        itemHolder.orgDest.setText(carona.getOrigem().getEndereco() + " para " + carona.getDestino().getEndereco());
        itemHolder.horario.setText("Saida as "+ Datas.DateToHHmm(carona.getDataHoraPartida())+" do dia "+Datas.DateToddMM(carona.getDataHoraPartida()));

        return view;
    }

        private class ItemSuporte {
            TextView orgDest;
            TextView horario;
        }
}
