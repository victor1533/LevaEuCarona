package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.orochi.utfpr.levaeu.Activitys.AdaptersListView.AdapterHistoricoListView;
import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Escopo.Historico;
import com.orochi.utfpr.levaeu.Retrofit.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class VerHistoricoActivity extends AppCompatActivity {
    private Historico historico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_historico);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final ListView lista = (ListView) findViewById(R.id.listHistorico);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VerHistoricoActivity.this, VerCaronaActiivty.class);
                intent.putExtra("carona", (Carona) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });

        PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
        Call<Historico> historicoCall = p.pegarHistorico(Sessao.getInstance().getPessoaLogada().getCodPessoa());
        historicoCall.enqueue(new Callback<Historico>() {
            @Override
            public void onResponse(Response<Historico> response, Retrofit retrofit) {
                if(response != null){
                    historico = response.body();
                    PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);


                    AdapterHistoricoListView adapter = new AdapterHistoricoListView(VerHistoricoActivity.this, historico);
                    lista.setAdapter(adapter);
                    /*
                    *
                    * */
                }
            }
            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
