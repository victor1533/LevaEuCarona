package com.orochi.utfpr.levaeu.ConexaoUTFPR.Utilidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
 
public class WebHelper {
 
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    private Context contexto;
    private static int TIMEOUT = 10 * 1000;
    // constructor
    public WebHelper(Context contexto) {
 this.contexto = contexto;
    }
 

    // function get json from url
    // by making HTTP POST or GET mehtod
    
public String pegarHTML(String url) throws IllegalStateException{	
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpParams params = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, TIMEOUT);
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            return WebUtils.responseToString(httpResponse);        
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}

public String pegarHTML(String url, Header header) throws IllegalStateException{	
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpParams params = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(params, TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, TIMEOUT);
            HttpGet httpGet = new HttpGet(url);
            if(header != null){
            	httpGet.addHeader(header);
            }
            HttpResponse httpResponse = httpClient.execute(httpGet);
            return WebUtils.responseToString(httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}

    
    public String pegarHTML(String url, String method,
        List<NameValuePair> params, Header header) throws IllegalStateException{	
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpParams param = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(param, TIMEOUT);
            HttpConnectionParams.setSoTimeout(param, TIMEOUT);
            if(method == "POST"){
                HttpPost httpPost = new HttpPost(url);
                if(params != null){
                	httpPost.setEntity(new UrlEncodedFormEntity(params));
                }
                if(header != null){
                	httpPost.addHeader(header);
                }
                
//        		httpPost.addHeader("Referer", "http://biblioteca.utfpr.edu.br/pergamum/biblioteca_s/php/login_usu.php?flag=index.php");
//                httpPost.addHeader("Origin", "http://biblioteca.utfpr.edu.br");
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
 
            }else if(method == "GET"){
                // request method is GET
                if(params != null){
	                String paramString = URLEncodedUtils.format(params, "utf-8");
	                url += "?" + paramString;
                }
                
                HttpGet httpGet = new HttpGet(url);
                if(header != null){
                	httpGet.addHeader(header);
                }
                HttpResponse httpResponse = httpClient.execute(httpGet);
                return WebUtils.responseToString(httpResponse);

            }          
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
}
    
    public String pegarHTML(String url, String method,
            List<NameValuePair> params, List<NameValuePair> headers) {
        // Making HTTP request
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            HttpParams param = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(param, TIMEOUT);
            HttpConnectionParams.setSoTimeout(param, TIMEOUT);
            // check for request method
            if(method == "POST"){
                // request method is POST
                // defaultHttpClient
                HttpPost httpPost = new HttpPost(url);
                if(params != null){
                	httpPost.setEntity(new UrlEncodedFormEntity(params));
                }
                if(headers != null){
                	for(int i = 0;i < headers.size();i++){
                		httpPost.addHeader(headers.get(i).getName(), headers.get(i).getValue());
                	}
                }
//        		httpPost.addHeader("Referer", "http://biblioteca.utfpr.edu.br/pergamum/biblioteca_s/php/login_usu.php?flag=index.php");
//
//                httpPost.addHeader("Origin", "http://biblioteca.utfpr.edu.br");
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
 
            }else if(method == "GET"){
                // request method is GET
                if(params != null){
	                String paramString = URLEncodedUtils.format(params, "utf-8");
	                url += "?" + paramString;
                }
                
                HttpGet httpGet = new HttpGet(url);
                if(headers != null){
                	for(int i = 0;i < headers.size();i++){
                		httpGet.addHeader(headers.get(i).getName(), headers.get(i).getValue());
                	}
                }
                HttpResponse httpResponse = httpClient.execute(httpGet);
                return WebUtils.responseToString(httpResponse);

            }          
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
}
    
   
    

 
    public JSONObject pegarJSON(String url, String method,
            List<NameValuePair> params) {
 
        // Making HTTP request
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpParams param = httpClient.getParams();
            HttpConnectionParams.setConnectionTimeout(param, TIMEOUT);
            HttpConnectionParams.setSoTimeout(param, TIMEOUT);
            // check for request method
            if(method == "POST"){
                // request method is POST
                HttpPost httpPost = new HttpPost(url);
                if(params != null){
                	httpPost.setEntity(new UrlEncodedFormEntity(params));
                }
                HttpResponse httpResponse = httpClient.execute(httpPost);
                json = WebUtils.responseToString(httpResponse);
            }else if(method == "GET"){
                // request method is GET
                if(params != null){
	                String paramString = URLEncodedUtils.format(params, "utf-8");
	                url += "?" + paramString;
                }
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                json = WebUtils.responseToString(httpResponse);
            }          
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
    

}