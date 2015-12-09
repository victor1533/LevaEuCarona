package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.orochi.utfpr.levaeu.Escopo.Pessoa;
import com.orochi.utfpr.levaeu.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.util.Date;

/**
 * Created by João on 09/12/2015.
 */
public class DetalhesActivity extends AppCompatActivity {

    private TextView like, deslike, nome, sexo, idade, endereco, curso, email, participacao;
    private Button botao;
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        like = (TextView) findViewById(R.id.likedetail);
        deslike = (TextView) findViewById(R.id.deslikedetail);
        participacao = (TextView) findViewById(R.id.Caronssas);
        nome = (TextView) findViewById(R.id.nome_detail);
        sexo = (TextView) findViewById(R.id.sexov_detail);
        idade = (TextView) findViewById(R.id.idade_detail);
        email = (TextView) findViewById(R.id.email_detail);
        curso = (TextView) findViewById(R.id.txt_curso);
        endereco = (TextView)findViewById(R.id.endereco_detail);
        pessoa = Sessao.getInstance().getPessoaLogada();

        like.setText("12");

        deslike.setText("12");

        participacao.setText(pessoa.getHistorico().getCaronas().size());
        nome.setText(pessoa.getDados().getNome());
        sexo.setText(pessoa.getDados().getSexo()=='m'?"Masculino":"Feminino");
        idade.setText(((pessoa.getDados().getDataNascimento().getYear()) - (new Date().getYear())));
        curso.setText(pessoa.getCurso().getNome());
        email.setText(pessoa.getDados().getEmail());
        endereco.setText(pessoa.getDados().getEnderecoResidencia().toString());
        Button botao = (Button) findViewById(R.id.btn_hist);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetalhesActivity.this, VerHistoricoActivity.class));
            }
        });

    }


}
