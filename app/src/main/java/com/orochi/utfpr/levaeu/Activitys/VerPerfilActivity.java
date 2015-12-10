package com.orochi.utfpr.levaeu.Activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Retrofit.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RetrofitUtils;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class VerPerfilActivity extends AppCompatActivity {

    @Bind(R.id.txtNomePerfil)
    TextView txtNomePerfil;
    @Bind(R.id.txtNumCaronasOferecidas)
    TextView txtNumCaronasOferecidas;
    @Bind(R.id.txtNumLikes)
    TextView txtNumLikes;
    @Bind(R.id.txtNumDislikes)
    TextView txtNumDislikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_perfil);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Pessoa pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");

        txtNomePerfil.setText(pessoa.getDados().getNome());
        PessoaListener pessoaListener = RetrofitUtils.getRetrofit().create(PessoaListener.class);
        Call<List<Carona>> caronasCriadas = pessoaListener.getCaronasCriadas(pessoa.getCodPessoa());
        caronasCriadas.enqueue(new Callback<List<Carona>>() {
            @Override
            public void onResponse(Response<List<Carona>> response, Retrofit retrofit) {
                if (response.body() != null) {
                    txtNumCaronasOferecidas.setText("Caronas oferecidas: "+response.body().size());
                    int numLikes = 0;
                    int numDislikes = 0;
                    for(Carona carona : response.body()) {
                        numLikes += carona.getReputacao().getLikes();
                        numDislikes += carona.getReputacao().getDislikes();
                    }
                    txtNumLikes.setText("Likes: " + numLikes);
                    txtNumDislikes.setText("Dislikes: " + numDislikes);

                }else{
                    try {
                        Log.i("ERRO", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
