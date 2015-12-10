package com.orochi.utfpr.levaeu.Activitys.AdaptersListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Escopo.Historico;
import com.orochi.utfpr.levaeu.Retrofit.Listener.CaronaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.R;
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
    public static List<Carona> caronasAvaliadas = null;
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
        final ItemSuporte itemHolder;


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
        if(caronasAvaliadas == null) {

            RetrofitUtils.getRetrofit().create(PessoaListener.class)
                    .getCaronasAvaliadas(Sessao.getInstance().getPessoaLogada().getCodPessoa()).enqueue(new Callback<List<Carona>>() {
                @Override
                public void onResponse(Response<List<Carona>> response, Retrofit retrofit) {
                    if (response != null) {
                        caronasAvaliadas = response.body();
                        if (caronasAvaliadas.contains(carona)) {
                            itemHolder.like.setVisibility(View.GONE);
                            itemHolder.dislike.setVisibility(View.GONE);
                        }

                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    t.printStackTrace();
                }
            });
        }else{
            if (caronasAvaliadas.contains(carona)) {
                itemHolder.like.setVisibility(View.GONE);
                itemHolder.dislike.setVisibility(View.GONE);
            }
        }
        itemHolder.origemDestino.setText(carona.getOrigem().getNomeLocal() + " para " + carona.getDestino().getNomeLocal() );
        itemHolder.diaHora.setText("Dia: " + Datas.DateToddMMyyyy(carona.getDataHoraPartida()) +
                " às " + Datas.DateToHHmm(carona.getDataHoraPartida()));
        itemHolder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* VAI DAR LIKE */

                CaronaListener c = RetrofitUtils.getRetrofit().create(CaronaListener.class);
                Call<RespostaWS> c2 = c.darLike(Sessao.getInstance().getPessoaLogada().getCodPessoa(), carona.getCodCarona());
                c2.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if(response != null){
                            if(response.body().isSucesso()){
                                Toast.makeText(contexto, "Like com sucesso!", Toast.LENGTH_SHORT).show();
                                itemHolder.like.setVisibility(View.GONE);
                                itemHolder.dislike.setVisibility(View.GONE);

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

                CaronaListener c = RetrofitUtils.getRetrofit().create(CaronaListener.class);
                Call<RespostaWS> c2 = c.darDislike(Sessao.getInstance().getPessoaLogada().getCodPessoa(), carona.getCodCarona());
                c2.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if (response != null) {
                            if (response.body().isSucesso()) {
                                Toast.makeText(contexto, "Dislike com sucesso!", Toast.LENGTH_SHORT).show();
                                itemHolder.like.setVisibility(View.GONE);
                                itemHolder.dislike.setVisibility(View.GONE);
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
