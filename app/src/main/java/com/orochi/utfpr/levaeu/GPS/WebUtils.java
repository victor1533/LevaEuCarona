package com.orochi.utfpr.levaeu.GPS;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * Created by Poisson on 16/08/2015.
 */
public class WebUtils {

    public static boolean verificaConexao(Context contexto) {
        ConnectivityManager conectivtyManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static TipoConexao getTipoConexao(Context context){
        if(!verificaConexao(context)) return TipoConexao.SEM_CONEXAO;

        NetworkInfo info = WebUtils.getNetworkInfo(context);
        if(info != null && info.isConnected()) {
            if(info.getType() == ConnectivityManager.TYPE_MOBILE) return TipoConexao._3G;
            if(info.getType() == ConnectivityManager.TYPE_WIFI) return TipoConexao.WIFI;
        }
        return TipoConexao.SEM_CONEXAO;
    }

    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

}
