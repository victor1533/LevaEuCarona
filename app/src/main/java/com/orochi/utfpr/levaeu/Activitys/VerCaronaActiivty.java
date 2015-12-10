package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Retrofit.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class VerCaronaActiivty extends AppCompatActivity {


    @Bind(R.id.btnVerMapa)
    Button btnVerMapa;
    @Bind(R.id.btnSolicitar)
    Button btnSolicitar;
    @Bind(R.id.lblMotorista)
    TextView lblMotorista;
    @Bind(R.id.txtNomeMotorista)
    TextView txtNomeMotorista;
    @Bind(R.id.containerMotorista)
    LinearLayout containerMotorista;
    @Bind(R.id.txtOrigem)
    EditText txtOrigem;
    @Bind(R.id.txtDestino)
    EditText txtDestino;
    @Bind(R.id.txtHorario)
    TextView txtHorario;
    @Bind(R.id.txtNumVagas)
    TextView txtNumVagasDisponiveis;
    @Bind(R.id.listaSapos)
    ListView listaSapos;

    Carona carona = null;
    @Bind(R.id.btnFecharCarona)
    Button btnFecharCarona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_carona_actiivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        carona = (Carona) getIntent().getSerializableExtra("carona");
        if (Sessao.getInstance().getPessoaLogada().equals(carona.getMotorista())) {
            containerMotorista.setVisibility(View.GONE);
            lblMotorista.setVisibility(View.GONE);
            btnSolicitar.setVisibility(View.GONE);
            btnVerMapa.setVisibility(View.GONE);
        } else {
            btnFecharCarona.setVisibility(View.GONE);
        }
        txtNomeMotorista.setText("" + carona.getMotorista().getDados().getNome());
        txtOrigem.setText(carona.getOrigem().getNomeLocal());
        txtDestino.setText(carona.getDestino().getNomeLocal());
        txtHorario.setText(Datas.DateToHHmm(carona.getDataHoraPartida()) + "h");
        txtNumVagasDisponiveis.setText("" + carona.getNumVagasDisponiveis());

        ArrayAdapter<Pessoa> arrayAdapter = new ArrayAdapter<Pessoa>(
                this,
                android.R.layout.simple_list_item_1,
                carona.getSaposNaCarona());

        listaSapos.setAdapter(arrayAdapter);
        listaSapos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VerCaronaActiivty.this, VerPerfilActivity.class);
                intent.putExtra("pessoa", (Pessoa) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }
    @OnClick(R.id.btnFecharCarona)
    public void fecharCarona(){
        RetrofitUtils.getRetrofit().create(PessoaListener.class)
                .fecharCarona(carona.getCodCarona()).enqueue(new Callback<RespostaWS>() {
            @Override
            public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                if(response.body()!= null) {
                    if(response.body().isSucesso()) {
                        Toast.makeText(VerCaronaActiivty.this, "Carona fechada com sucesso",
                                Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(VerCaronaActiivty.this, "Erro ao fechar carona",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

    }
    @OnClick(R.id.btnVerMapa)
    public void verCaronaMapa() {
        ArrayList<Carona> caronas = new ArrayList<>();
        caronas.add(carona);
        Intent intent = new Intent(VerCaronaActiivty.this, MapaActivity.class);
        intent.putExtra("caronas", caronas);
        startActivity(intent);
    }

    @OnClick(R.id.btnSolicitar)
    public void solicitarCarona() {
        RetrofitUtils.getRetrofit().create(PessoaListener.class)
                .requisitarCarona(carona, Sessao.getInstance().getPessoaLogada().getCodPessoa()).enqueue(new Callback<RespostaWS>() {
            @Override
            public void onResponse(Response<RespostaWS> response, Retrofit retrofit) {
                if (response != null) {
                    if (response.body().isSucesso()) {
                        Toast.makeText(VerCaronaActiivty.this, "Carona requisitada com sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(VerCaronaActiivty.this, "Erro ao requisitar carona!", Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(VerCaronaActiivty.this, "Erro ao requisitar carona!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(VerCaronaActiivty.this, "Erro ao requisitar carona!", Toast.LENGTH_LONG).show();

            }
        });
    }

    @OnClick(R.id.containerMotorista)
    public void verPerfilMotorista() {
        Intent intent = new Intent(VerCaronaActiivty.this, VerPerfilActivity.class);
        intent.putExtra("pessoa", carona.getMotorista());
        startActivity(intent);
    }

}