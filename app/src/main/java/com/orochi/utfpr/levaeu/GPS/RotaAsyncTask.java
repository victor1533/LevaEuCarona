package com.orochi.utfpr.levaeu.GPS;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.orochi.utfpr.levaeu.Escopo.Local;

import java.util.Locale;

/**
 * Created by Poisson on 20/11/2015.
 */
public class RotaAsyncTask extends
        AsyncTask<Local, Void, Void> {

    private ProgressDialog dialog;
    private GoogleMap mapView;
    private Context context;
    private Route rota;

    public RotaAsyncTask(Context ctx, GoogleMap mapa) {
        mapView = mapa;
        context = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(
                context, "Aguarde",
                "Calculando rota");
    }

    @Override
    protected Void doInBackground(Local... params) {

        rota = directions(
                new LatLng(params[0].getCoordenada().getLatitude(), params[0].getCoordenada().getLongitude()),
                new LatLng(params[1].getCoordenada().getLatitude(), params[1].getCoordenada().getLongitude()));
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        PolylineOptions options = new PolylineOptions()
                .width(5)
                .color(Color.RED)
                .visible(true);

        for (LatLng latlng : rota.getPoints()) {
            options.add(latlng);
        }

        mapView.addPolyline(options);
        dialog.dismiss();
    }

    private Route directions(
            final LatLng start, final LatLng dest) {

        // Formatando a URL com a latitude e longitude
        // de origem e destino.
        String urlRota = String.format(Locale.US,
                "http://maps.googleapis.com/maps/api/"+
                        "directions/json?origin=%f,%f&"+
                        "destination=%f,%f&" +
                        "sensor=true&mode=driving",
                start.latitude,
                start.longitude,
                dest.latitude,
                dest.longitude);

        GoogleParser parser;
        parser = new GoogleParser(urlRota);
        return parser.parse();
    }
}