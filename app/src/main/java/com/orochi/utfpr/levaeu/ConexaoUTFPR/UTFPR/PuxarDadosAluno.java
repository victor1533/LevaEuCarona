package com.orochi.utfpr.levaeu.ConexaoUTFPR.UTFPR;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orochi.utfpr.levaeu.Utils.Callback;
import com.orochi.utfpr.levaeu.Escopo.Campus;
import com.orochi.utfpr.levaeu.Retrofit.Listener.PessoaListener;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RespostaWS;
import com.orochi.utfpr.levaeu.Retrofit.Listener.RetrofitUtils;
import com.orochi.utfpr.levaeu.Escopo.Pessoa;

import java.io.IOException;

import retrofit.Response;

public class PuxarDadosAluno extends AsyncTask<String, String, Pessoa> {

	ProgressDialog dialog;
	AlertDialog.Builder builder1;
	Context contexto;
	Callback callback;
	Campus campus;
	public PuxarDadosAluno(Activity test, Callback callback, Campus campus){
		this.contexto = test;
		this.callback = callback;
		this.campus = campus;
	}
	protected void onPreExecute() {
		super.onPreExecute();	
		 dialog = ProgressDialog.show(this.contexto, "Aguarde",
				"Logando...", false, true);
		builder1 = new AlertDialog.Builder(contexto);

	}

	 protected void onProgressUpdate(String... progress) {
	     super.onProgressUpdate(progress[0]);
	     dialog.setMessage(progress[0]);
	 }
	 
	protected Pessoa doInBackground(String... params) {

		int RA = Integer.parseInt(params[0]);
		String senha = params[1];
		SistemaUTFPR utfpr = new SistemaUTFPR(RA, senha, this.campus);
		Pessoa aluno = null;
		try {
			if(utfpr.conectar(this.contexto)){
				publishProgress("Puxando dados...");
				aluno = utfpr.getAluno(this.contexto);
			}else{
				builder1.setMessage("RA ou senha incorretos!");
				return null;
			}
		}catch(IllegalStateException e){
			builder1.setMessage("Login ou senha incorretos");
			e.printStackTrace();
			return null;
		}catch(Exception e){
			builder1.setMessage("Login ou senha incorretos");
			e.printStackTrace();
			return null;
		}

		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();

		Log.i("GSON", new Gson().toJson((Pessoa) aluno));
		PessoaListener p = RetrofitUtils.getRetrofit().create(PessoaListener.class);
		Response<RespostaWS> r = null;
		try {
			r = p.cadastrar((Pessoa) aluno).execute();
			if(r.body() != null && r.body().isSucesso()){
//				Toast.makeText(contexto, r.body().getResultado(), Toast.LENGTH_LONG).show();
				aluno.setCodPessoa(r.body().getCod());
			}
		} catch (IOException e) {
			try {
				Log.i("ERRO", r.errorBody().string());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
		return aluno;
	}			
	protected void onPostExecute(Pessoa aluno) {
		dialog.dismiss();
		if(aluno == null){
			builder1.setTitle("Ocorreu um erro!");
			builder1.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface diag,
								int whichButton) {
						}
					});			
			builder1.create().show();			
		}else{
			callback.run(aluno);
		}

		}
}
		