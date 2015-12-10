package com.orochi.utfpr.levaeu.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;
import com.orochi.utfpr.levaeu.Escopo.Carona;
import com.orochi.utfpr.levaeu.Utils.Datas;
import com.orochi.utfpr.levaeu.Retrofit.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Escopo.Local;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;
import com.orochi.utfpr.levaeu.R;
import com.orochi.utfpr.levaeu.Utils.Sessao;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class NewCaronaActivity extends AppCompatActivity {

    @Bind(R.id.numeroVagas)
    NumberPicker numVagasCampo;
    @Bind(R.id.dataHoraPartidaCampo)
    EditText dataHoraPartidaCampo;
    @Bind(R.id.origemCampo)
    EditText origemCampo;
    @Bind(R.id.DestinoCampo)
    EditText DestinoCampo;
    @Bind(R.id.criaCarona)
    Button criaCarona;
    Pessoa pessoa;
    @Bind(R.id.selecOrigem)
    Button selecOrigem;
    @Bind(R.id.selecDestino)
    Button selecDestino;
    private int PLACE_PICKER_REQUEST_ORIGEM = 1;
    private int PLACE_PICKER_REQUEST_DESTINO = 2;
    private Carona carona = new Carona();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_carona);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        numVagasCampo.setMinValue(1);
        numVagasCampo.setMaxValue(20);
        numVagasCampo.setWrapSelectorWheel(false);
        numVagasCampo.setValue(4);
        dataHoraPartidaCampo.setText("10/05/2015 10:34");

        pessoa = Sessao.getInstance().getPessoaLogada();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST_ORIGEM) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                this.origemCampo.setText(place.getName() == null? place.getAddress(): place.getName());
              carona.setOrigem(Local.PlaceToLocal(place));
                Toast.makeText(this, ""+place.getLatLng().latitude, Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == PLACE_PICKER_REQUEST_DESTINO){
            Place place = PlacePicker.getPlace(data, this);
            this.DestinoCampo.setText(place.getName() == null? place.getAddress(): place.getName());
            carona.setDestino(Local.PlaceToLocal(place));
        }
    }
    @OnClick(R.id.criaCarona)
    public void criaCarona() {
        // Toast.makeText(getApplicationContext(), "Carona TESTE!", Toast.LENGTH_LONG).show();

        carona.setDataHoraPartida(Datas.ddMMyyyyHHmmToDate(dataHoraPartidaCampo.getText().toString()));
        carona.setNumVagas(numVagasCampo.getValue());
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
                    try {
                        Toast.makeText(getApplicationContext(), "Erro!" + response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(getApplicationContext(), "Err22o!", Toast.LENGTH_LONG).show();

            }
        });

    }

    @OnClick(R.id.selecOrigem)
    public void selecionaOrigem(){
        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(NewCaronaActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.selecDestino)
    public void selecionaDestino(){
        int PLACE_PICKER_REQUEST = 2;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(NewCaronaActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }


}
