package com.orochi.utfpr.levaeu.Activitys.AdaptersListView;

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
import com.orochi.utfpr.levaeu.Escopo.Historico;
import com.orochi.utfpr.levaeu.Escopo.Local;
import com.orochi.utfpr.levaeu.Listener.CaronaListener;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Reputacao.Dislike;
import com.orochi.utfpr.levaeu.Reputacao.Like;
import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.util.List;

import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by a1602632 on 25/11/15.
 */
public class AdapterHistoricoListView extends BaseAdapter{

    private LayoutInflater mInflater;
    private Historico itens;
    private Context contexto;

    public AdapterHistoricoListView(Context contexto, Historico advs) {
        this.itens = advs;
        this.contexto = contexto;
        mInflater = LayoutInflater.from(contexto);
    }


    @Override
    public int getCount() {
        return itens.getCaronas().size();
    }

    @Override
    public Carona getItem(int position) {
        return itens.getCaronas().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemSuporte itemHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_list_historico, null);
            ButterKnife.bind(this, view);

            itemHolder = new ItemSuporte();
            itemHolder.origemDestino = (TextView) view.findViewById(R.id.origemDestino);
            itemHolder.diaHora = (TextView) view.findViewById(R.id.diaHora);
            itemHolder.like = (ImageView) view.findViewById(R.id.like);
            itemHolder.dislike = (ImageView) view.findViewById(R.id.dislike);

            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        final Carona carona = itens.getCaronas().get(position);
        itemHolder.origemDestino.setText(carona.getOrigem().getNomeLocal() + " para " + carona.getDestino().getNomeLocal() );
        itemHolder.diaHora.setText("Dia: " + Datas.DateToddMMyyyy(carona.getDataHoraPartida()) +
                " às " + Datas.DateToHHmm(carona.getDataHoraPartida()));
        itemHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* VAI DAR LIKE */
                Like like = new Like();
                like.setAvaliador(Sessao.getInstance().getPessoaLogada());
                CaronaListener c = RetrofitUtils.getRetrofit().create(CaronaListener.class);
                Call<RespostaWS> c2 = c.darLike(like);
                c2.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if(response != null){
                            if(response.body().isSucesso()){
                                Toast.makeText(contexto, "Like com sucesso!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(contexto, response.body().getResultado(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });

        itemHolder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* VAI DAR DISLIKE */
                Dislike dislike = new Dislike();
                dislike.setAvaliador(Sessao.getInstance().getPessoaLogada());
                CaronaListener c = RetrofitUtils.getRetrofit().create(CaronaListener.class);
                Call<RespostaWS> c2 = c.darDislike(dislike);
                c2.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if (response != null) {
                            if (response.body().isSucesso()) {
                                Toast.makeText(contexto, "Dislike com sucesso!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(contexto, response.body().getResultado(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

            }
        });
        return view;
    }

    private class ItemSuporte {
        TextView origemDestino;
        TextView diaHora;
        ImageView like;
        ImageView dislike;
    }


}
