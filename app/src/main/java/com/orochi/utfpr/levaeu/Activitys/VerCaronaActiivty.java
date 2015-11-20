package com.orochi.utfpr.levaeu.Activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;
import com.orochi.utfpr.levaeu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.*;

public class VerCaronaActiivty extends AppCompatActivity {

    @Bind(R.id.mot_vercarona)
    TextView motVercarona;
    @Bind(R.id.origemDestino)
    TextView origemDestino;
    @Bind(R.id.destinoOrigem)
    TextView destinoOrigem;
    @Bind(R.id.horarioSaida)
    TextView horarioSaida;
    @Bind(R.id.vagasdis)
    TextView vagasdis;
    @Bind(R.id.pessoaEsp)
    TextView pessoaEsp;
    @Bind(R.id.listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carona_actiivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final Carona carona = (Carona) getIntent().getSerializableExtra("carona");
        motVercarona.setText("Motorista: " + carona.getMotorista().getDados().getNome());
        origemDestino.setText("Origem: " + carona.getOrigem().getEndereco());
        destinoOrigem.setText("Destino: " + carona.getDestino().getEndereco());
        horarioSaida.setText("Saida as " + Datas.DateToHHmm(carona.getDataHoraPartida()) + " do dia " + Datas.DateToddMM(carona.getDataHoraPartida()));
        vagasdis.setText("Numero de vagas : " + (carona.getNumVagas() - carona.getSaposNaCarona().size()));
        pessoaEsp.setText("Pessoas em espera: " + carona.getSaposNaEspera().size());

        ArrayAdapter<Pessoa> arrayAdapter = new ArrayAdapter<Pessoa>(
                this,
                android.R.layout.simple_list_item_1,
                carona.getSaposNaCarona());

        listView.setAdapter(arrayAdapter);

        FloatingActionButton fecha = (FloatingActionButton) findViewById(R.id.fecha);
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
                Call<RespostaWS> r = p.fecharCarona(carona.getCodCarona());
                r.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            if(response.body().isSucesso())
                            Toast.makeText(VerCaronaActiivty.this, "Carona Fechada com sucesso", Toast.LENGTH_SHORT).show();

                        else {
                            Toast.makeText(VerCaronaActiivty.this, "Erro ao fechar carona", Toast.LENGTH_SHORT).show();
                        }}
                        else {
                            Toast.makeText(VerCaronaActiivty.this, "Erro ao fechar carona", Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(VerCaronaActiivty.this, "EROOOO2.", Toast.LENGTH_LONG).show();

                    }

                });
            }
        });


    }

}
