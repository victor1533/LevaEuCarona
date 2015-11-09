package com.orochi.utfpr.levaeu.ConexaoUTFPR.Utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import android.content.Context;
import android.net.ConnectivityManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class WebUtils {


public static HttpClient wrapClient(HttpClient base) {
    try {
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
 
            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
 
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] chain,
					String authType)
					throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] chain,
					String authType)
					throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub
				
			}
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new MySSLSocketFactory(ctx);
        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = base.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", ssf, 443));
        return new DefaultHttpClient(ccm, base.getParams());
    } catch (Exception ex) {
        return null;
    }
}
    public static String responseToString(HttpResponse response) throws IllegalStateException, IOException{
    	StringBuilder sb = new StringBuilder();
        InputStream is = null;

        HttpEntity httpEntity = response.getEntity();
        is = httpEntity.getContent();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                is, "iso-8859-1"), 8);
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    } 
        

public static boolean verificaConexao(Context contexto) {  
    boolean conectado;  
    ConnectivityManager conectivtyManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);  
    if (conectivtyManager.getActiveNetworkInfo() != null  
            && conectivtyManager.getActiveNetworkInfo().isAvailable()  
            && conectivtyManager.getActiveNetworkInfo().isConnected()) {  
        conectado = true;  
    } else {  
        conectado = false;  
    }  
    return conectado;  
}  

}


//httpPost.addHeader("Referer", "http://biblioteca.utfpr.edu.br/pergamum/biblioteca_s/php/login_usu.php?flag=index.php");
//httpPost.addHeader("Origin", "http://biblioteca.utfpr.edu.br");

