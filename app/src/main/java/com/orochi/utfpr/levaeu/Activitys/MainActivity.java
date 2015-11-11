package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Activitys.AdaptersListView.AdapterCaronaListView;
import com.orochi.utfpr.levaeu.Carona;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Pessoa;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Sessao;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private TextView nomeMenu;
        private TextView emailMenu;
   private DrawerLayout drawer;
    private Pessoa pessoa;
    private ListView lista;
    private AdapterCaronaListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");
        Sessao.getInstance().setPessoaLogada(pessoa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout =
                navigationView.inflateHeaderView(R.layout.nav_header_main);

        nomeMenu = (TextView) headerLayout.findViewById(R.id.nomeHeader);
        emailMenu = (TextView) headerLayout.findViewById(R.id.emailHeader);
        nomeMenu.setText(pessoa.getDados().getNome());
        emailMenu.setText(pessoa.getDados().getEmail());
        this.lista = (ListView) findViewById(R.id.listaCaronas);

        final PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
        final Call<List<Carona>> r = p.getAllCaronas();
        r.enqueue(new Callback<List<Carona>>() {
            @Override
            public void onResponse(Response<List<Carona>> response, Retrofit retrofit) {
                if (response.body() != null) {
                    adapter = new AdapterCaronaListView(MainActivity.this, response.body());
                    lista.setAdapter(adapter);
                    Toast.makeText(MainActivity.this, "" + response.body().size(), Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        Toast.makeText(MainActivity.this, "Erro ao carregar caronas: " + response.errorBody().string(), Toast.LENGTH_SHORT).show();
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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabio);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
                 Call<List<Carona>> r = p.getAllCaronas();
                r.enqueue(new Callback<List<Carona>>() {
                    @Override
                    public void onResponse(Response<List<Carona>> response, Retrofit retrofit) {
                        if (response.body() != null) {
                            adapter = new AdapterCaronaListView(MainActivity.this, response.body());
                            lista.setAdapter(adapter);
                        } else {
                            Toast.makeText(MainActivity.this, "Erro ao carregar caronas", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }

                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.novaCaronaItem) {
            Intent intent = new Intent(MainActivity.this, NewCaronaActivity.class);
            intent.putExtra("pessoa", pessoa);
            startActivity(intent);
        } else if (id == R.id.verCaronasAprovar){
            Intent intent = new Intent(MainActivity.this, AprovarSaposActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.sairItem) {
        finish();
        }else {
            Toast.makeText(getApplicationContext(), "Função não implementada", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
