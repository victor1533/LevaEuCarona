package com.orochi.utfpr.levaeu.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Activitys.AdaptersListView.AdapterAprovarSapoListView;
import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class AprovarSaposActivity extends AppCompatActivity {
    private AdapterAprovarSapoListView adapter;

    @Bind(R.id.listaSaposPraAprovar)
    ListView listaSaposPraAprovar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprovar_sapos);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);

        Call<List<Carona>> p2 = p.getCaronasCriadas(Sessao.getInstance().getPessoaLogada().getCodPessoa());
        p2.enqueue(new Callback<List<Carona>>() {
            @Override
            public void onResponse(Response<List<Carona>> response, Retrofit retrofit) {
                if (response.body() != null) {
                    if(response.body().get(0) != null) {
                        adapter = new AdapterAprovarSapoListView(AprovarSaposActivity.this, response.body().get(0));
                        listaSaposPraAprovar.setAdapter(adapter);
                    }else{
                        Toast.makeText(AprovarSaposActivity.this, "nenhum sapo!", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    try {
                        Toast.makeText(AprovarSaposActivity.this, "Erro ao carregar sapos: " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Err22o!", Toast.LENGTH_LONG).show();

            }
        });

    }


}


