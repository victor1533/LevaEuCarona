package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class VerCaronaActiivty extends AppCompatActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.mot_vercarasasda)
    TextView motVercarasasda;
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
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.listVie2)
    ListView listVie2;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.button1)
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carona_actiivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final Carona carona = (Carona) getIntent().getSerializableExtra("carona");
        if (Sessao.getInstance().getPessoaLogada().getCodPessoa() != carona.getMotorista().getCodPessoa()) {
            motVercarona.setText("Motorista: " + carona.getMotorista().getDados().getNome());
            origemDestino.setText("Origem: " + carona.getOrigem().getEndereco());
            destinoOrigem.setText("Destino: " + carona.getDestino().getEndereco());
            horarioSaida.setText("Saida as " + Datas.DateToHHmm(carona.getDataHoraPartida()) + " do dia " + Datas.DateToddMM(carona.getDataHoraPartida()));
            vagasdis.setText("Numero de vagas : " + (carona.getNumVagas() - carona.getSaposNaCarona().size()));
            listView.setVisibility(View.GONE);
            textView4.setVisibility(View.GONE);
            ArrayAdapter<Pessoa> arrayAdapter = new ArrayAdapter<Pessoa>(
                    this,
                    android.R.layout.simple_list_item_1,
                    carona.getSaposNaCarona());

            listVie2.setAdapter(arrayAdapter);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
                    Call<RespostaWS> r = p.requisitarCarona(carona, Sessao.getInstance().getPessoaLogada().getCodPessoa());
                    r.enqueue(new Callback<RespostaWS>() {
                        @Override
                        public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                            if (response.body() != null) {
                                if (response.body().isSucesso())
                                    Toast.makeText(VerCaronaActiivty.this, "Carona Solicitada", Toast.LENGTH_SHORT).show();

                                else {
                                    Toast.makeText(VerCaronaActiivty.this, "Erro ao solicitar carona", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(VerCaronaActiivty.this, "Erro ao solicitar carona", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onFailure(Throwable t) {
                            Toast.makeText(VerCaronaActiivty.this, "EROOOO2.", Toast.LENGTH_LONG).show();

                        }

                    });
                }
            });


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(VerCaronaActiivty.this, MapaActivity.class);
                    ArrayList<Carona> car = new ArrayList<Carona>();
                    car.add(carona);
                    in.putExtra("caronas", car);

                }
            });


        } else {
            motVercarona.setText("Motorista: " + carona.getMotorista().getDados().getNome());
            origemDestino.setText("Origem: " + carona.getOrigem().getEndereco());
            destinoOrigem.setText("Destino: " + carona.getDestino().getEndereco());
            horarioSaida.setText("Saida as " + Datas.DateToHHmm(carona.getDataHoraPartida()) + " do dia " + Datas.DateToddMM(carona.getDataHoraPartida()));
            vagasdis.setText("Numero de vagas : " + (carona.getNumVagas() - carona.getSaposNaCarona().size()));
            ArrayAdapter<Pessoa> arrayAdapter = new ArrayAdapter<Pessoa>(
                    this,
                    android.R.layout.simple_list_item_1,
                    carona.getSaposNaCarona());

            listVie2.setAdapter(arrayAdapter);

            ArrayAdapter<Pessoa> arrayAdapter2 = new ArrayAdapter<Pessoa>(
                    this,
                    android.R.layout.simple_list_item_1,
                    carona.getSaposNaEspera());

            listView.setAdapter(arrayAdapter2);

            button1.setText("fechar carona");
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
                    Call<RespostaWS> r = p.fecharCarona(carona.getCodCarona());
                    r.enqueue(new Callback<RespostaWS>() {
                        @Override
                        public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                            if (response.body() != null) {
                                if (response.body().isSucesso())
                                    Toast.makeText(VerCaronaActiivty.this, "Carona Fechada com sucesso", Toast.LENGTH_SHORT).show();

                                else {
                                    Toast.makeText(VerCaronaActiivty.this, "Erro ao fechar carona", Toast.LENGTH_SHORT).show();
                                }
                            } else {
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

            button.setText("Cancelar Carona");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
                    Call<RespostaWS> r = p.cancelarCarona(carona.getCodCarona());
                    r.enqueue(new Callback<RespostaWS>() {
                        @Override
                        public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                            if (response.body() != null) {
                                if (response.body().isSucesso())
                                    Toast.makeText(VerCaronaActiivty.this, "Carona Cancelada com sucesso", Toast.LENGTH_SHORT).show();

                                else {
                                    Toast.makeText(VerCaronaActiivty.this, "Erro ao cancelar carona", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(VerCaronaActiivty.this, "Erro ao cancelar carona", Toast.LENGTH_SHORT).show();
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
}