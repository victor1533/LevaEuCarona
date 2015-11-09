package com.orochi.utfpr.levaeu.ConexaoUTFPR.UTFPR;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;


import com.orochi.utfpr.levaeu.Callback;
import com.orochi.utfpr.levaeu.Campus;
import com.orochi.utfpr.levaeu.Pessoa;

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
			builder1.setMessage(e.getMessage());
			e.printStackTrace();
			return null;
		}catch(Exception e){
			builder1.setMessage("Erro desconhecido.");
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
		