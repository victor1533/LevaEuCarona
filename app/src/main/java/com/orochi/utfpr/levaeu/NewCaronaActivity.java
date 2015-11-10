package com.orochi.utfpr.levaeu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.*;
import retrofit.Callback;

public class NewCaronaActivity extends AppCompatActivity {

    @Bind(R.id.numVagasCampo)
    EditText numVagasCampo;
    @Bind(R.id.dataHoraPartidaCampo)
    EditText dataHoraPartidaCampo;
    @Bind(R.id.origemCampo)
    EditText origemCampo;
    @Bind(R.id.DestinoCampo)
    EditText DestinoCampo;
    @Bind(R.id.criaCarona)
    Button criaCarona;
    Pessoa pessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_carona);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        numVagasCampo.setText("5");
        dataHoraPartidaCampo.setText("10/05/2015 10:34");

                origemCampo.setText("ASDASD");
                DestinoCampo.setText("BLALBA");

        pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");

    }
    @OnClick(R.id.criaCarona)
    public void criaCarona(){
       // Toast.makeText(getApplicationContext(), "Carona TESTE!", Toast.LENGTH_LONG).show();

        Carona carona = new Carona();
        Local origem = new Local();
        Local destino = new Local();
        origem.setEndereco(origemCampo.getText().toString());
        destino.setEndereco(DestinoCampo.getText().toString());
        carona.setOrigem(origem);
        carona.setDestino(destino);
        carona.setDataHoraPartida(Datas.ddMMyyyyHHmmToDate(dataHoraPartidaCampo.getText().toString()));
        carona.setNumVagas(Integer.parseInt(numVagasCampo.getText().toString()));
        carona.setMotorista((Pessoa) pessoa);
        PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
        Log.i("GSON", new Gson().toJson(carona));

        Call<RespostaWS> p2 = p.criaCarona(carona);
                p2.enqueue(new Callback<RespostaWS>() {
                    @Override
                    public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            if (response.body().isSucesso()) {
                                Toast.makeText(getApplicationContext(), "Carona cadastrada!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), response.body().getResultado(), Toast.LENGTH_LONG).show();

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro!", Toast.LENGTH_LONG).show();
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
