package com.orochi.utfpr.levaeu.Activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Activitys.AdaptersListView.AdapterCaronaListView;
import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Retrofit.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RetrofitUtils;
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

public class VerCaronasQueEstouActivity extends AppCompatActivity {

    @Bind(R.id.listView2)
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_caronas_que_estou);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
        final Call<List<Carona>> r = p.getCaronasQueEstou(Sessao.getInstance().getPessoaLogada().getCodPessoa());
        r.enqueue(new Callback<List<Carona>>() {
            @Override
            public void onResponse(Response<List<Carona>> response, Retrofit retrofit) {
                if (response.body() != null) {
                    listView2.setAdapter(new AdapterCaronaListView(VerCaronasQueEstouActivity.this, response.body()));
                    Toast.makeText(VerCaronasQueEstouActivity.this, "" + response.body().size(), Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        Toast.makeText(VerCaronasQueEstouActivity.this, "Erro ao carregar caronas: " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pila viado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
