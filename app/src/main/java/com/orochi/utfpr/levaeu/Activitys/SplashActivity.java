package com.orochi.utfpr.levaeu.Activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.orochi.utfpr.levaeu.Utils.Callback;
import com.orochi.utfpr.levaeu.Escopo.Campus;
import com.orochi.utfpr.levaeu.ConexaoUTFPR.UTFPR.PuxarDadosAluno;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;
import com.orochi.utfpr.levaeu.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PuxarDadosAluno pDados = new PuxarDadosAluno(SplashActivity.this, new Callback(){
            public void run(Object aluno){
                Toast.makeText(SplashActivity.this, ((Pessoa)aluno).getDados().getNome(), Toast.LENGTH_SHORT).show();

            }}, Campus.getCampoMourao());

        pDados.execute("1602632", "purolestat123");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
}
