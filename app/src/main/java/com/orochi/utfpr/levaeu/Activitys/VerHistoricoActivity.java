package com.orochi.utfpr.levaeu.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.orochi.utfpr.levaeu.Activitys.AdaptersListView.AdapterHistoricoListView;
import com.orochi.utfpr.levaeu.Escopo.Historico;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Utils.Sessao;

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
        final ListView lista = (ListView) findViewById(R.id.listHistorico);
        PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
        Call<Historico> historicoCall = p.pegarHistorico(Sessao.getInstance().getPessoaLogada().getCodPessoa());
        historicoCall.enqueue(new Callback<Historico>() {
            @Override
            public void onResponse(Response<Historico> response, Retrofit retrofit) {
                if(response != null){
                    historico = response.body();
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
