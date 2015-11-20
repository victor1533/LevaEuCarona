package com.orochi.utfpr.levaeu.Activitys.AdaptersListView;

/**
 * Created by Poisson on 11/11/2015.
 */


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Escopo.Local;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


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
            itemHolder.origemDestino = (TextView) view.findViewById(R.id.origemDestino);
            itemHolder.dataHoraSaida = (TextView) view.findViewById(R.id.horarioSaida);
            itemHolder.verMapa = (ImageView) view.findViewById(R.id.mapinha);
            itemHolder.requisitarCarona = (ImageView) view.findViewById(R.id.joinha);

            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        final Carona carona = itens.get(position);
        Local origem = carona.getOrigem();
        Local destino = carona.getDestino();
        itemHolder.origemDestino.setText(origem.getEndereco() + " para " + destino.getEndereco());
        itemHolder.dataHoraSaida.setText("Saída às " + Datas.DateToHHmm(carona.getDataHoraPartida()));

        if(carona.isCheia()){
            itemHolder.requisitarCarona.setImageResource(R.drawable.joinha_cinza);
        }
        itemHolder.requisitarCarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carona.isCheia()) return;
                PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
                Call<RespostaWS> r = p.requisitarCarona(carona, Sessao.getInstance().getPessoaLogada().getCodPessoa());
                Log.i("GSON", new Gson().toJson(carona));
                r.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if(response.body() != null){
                            if(response.body().isSucesso()){
                                Toast.makeText(contexto, "Carona requisitada.", Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(contexto, response.body().getResultado(), Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(contexto, "EROOOO.", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(contexto, "EROOOO2.", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        return view;
    }

    private class ItemSuporte {
        TextView origemDestino;
        TextView dataHoraSaida;
        ImageView verMapa;
        ImageView requisitarCarona;
    }


}
