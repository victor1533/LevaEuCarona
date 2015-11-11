package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Carona;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Pessoa;
import com.orochi.utfpr.levaeu.R;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private TextView nomeMenu;
        private TextView emailMenu;
   private DrawerLayout drawer;
    private ListView lista;
    private Pessoa pessoa;
    private CaronaListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         pessoa = (Pessoa) getIntent().getSerializableExtra("pessoa");
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
    }

    private void preencheLista(final ArrayList<Carona> car){
        this.lista = (ListView) findViewById(R.id.listView);
        this.adapter = new CaronaListView(MainActivity.this, car);

        this.lista.setAdapter(this.adapter);
        this.lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(MainActivity.this, RecebeAdvActivty.class);
                intent.putExtra("carona", car.get(arg2));
                /*PessoaListener  e = new RetrofitPessoa().getListener();
                Log.i("TESTE","Rola");
                e.lerAdvertencia(adver.get(arg2).getCodAdvertencia());*/
                startActivity(intent);
            }

        });

    @Override
    protected void onResume() {
        super.onResume();
        inicializaDialog();
        preencheAdvertencias();
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


    private void preencheAdvertencias() {
        PessoaListener p = new  RetrofitPessoa().getListener();
        final Call<ArrayList<Carona>> p2 = p.getAdvertencias(SessaoSingleton.me.getCodPessoa());
        p2.enqueue(new Callback<ArrayList<Advertencia>>() {
            @Override
            public void onResponse(Response<ArrayList<Caronas>> response) {
                Log.i("TTT", response.raw().body().toString());
                if (RetrofitUtils.checkRetrofitError(response, MainActivity.this)) {
                    MainActivity.this.fechaDialog();
                    return;
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
        } else if(id == R.id.sairItem) {
        finish();
        }else {
            Toast.makeText(getApplicationContext(), "Função não implementada", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
