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
import com.orochi.utfpr.levaeu.Carona;
import com.orochi.utfpr.levaeu.Datas;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Local;
import com.orochi.utfpr.levaeu.Pessoa;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Sessao;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Poisson on 11/11/2015.
 */

public class AdapterAprovarSapoListView extends BaseAdapter implements Serializable {
    private LayoutInflater mInflater;
    private List<Pessoa> itens;
    private Context contexto;
    private Carona carona;
    public AdapterAprovarSapoListView(Context contexto, Carona advs) {
        this.carona = advs;
        this.itens = advs.getSaposNaEspera();
        this.contexto = contexto;
        mInflater = LayoutInflater.from(contexto);
    }


    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Pessoa getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ItemSuporte itemHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.item_list_sapo_aprovar, null);
            ButterKnife.bind(this, view);

            itemHolder = new ItemSuporte();
            itemHolder.aprovarCarona = (ImageView) view.findViewById(R.id.joinha);
            itemHolder.caronaDesc = (TextView) view.findViewById(R.id.caronaPraAdd);
            itemHolder.nomeSapo = (TextView) view.findViewById(R.id.nomeDoSapo);

            view.setTag(itemHolder);
        } else {
            itemHolder = (ItemSuporte) view.getTag();
        }
        final Pessoa sapo = itens.get(position);
        Local origem = carona.getOrigem();
        Local destino = carona.getDestino();
        itemHolder.caronaDesc.setText(origem.getEndereco() + " para " + destino.getEndereco());
        itemHolder.nomeSapo.setText(sapo.getDados().getNome());

        if (carona.isCheia()) {
            itemHolder.aprovarCarona.setImageResource(R.drawable.joinha_cinza);
        }
        itemHolder.aprovarCarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carona.isCheia()) return;

                PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
                Call<RespostaWS> r = p.confirmarCarona(carona, sapo.getCodPessoa());
                Log.i("GSON", new Gson().toJson(carona));
                r.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            if (response.body().isSucesso()) {
                                Toast.makeText(contexto, "Carona aprovada.", Toast.LENGTH_LONG).show();
                                itemHolder.aprovarCarona.setImageResource(R.drawable.joinha_cinza);


                            } else {
                                Toast.makeText(contexto, response.body().getResultado(), Toast.LENGTH_LONG).show();
                            }
                        } else {
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
        TextView nomeSapo;
        TextView caronaDesc;
        ImageView aprovarCarona;
    }

}
