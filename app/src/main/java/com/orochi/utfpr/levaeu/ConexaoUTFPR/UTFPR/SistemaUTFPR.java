package com.orochi.utfpr.levaeu.ConexaoUTFPR.UTFPR;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.orochi.utfpr.levaeu.Campus;
import com.orochi.utfpr.levaeu.ConexaoUTFPR.Utilidades.WebHelper;
import com.orochi.utfpr.levaeu.ConexaoUTFPR.Utilidades.WebUtils;
import com.orochi.utfpr.levaeu.Pessoa;


public final class SistemaUTFPR {
	/* N�O MEXA NESTA CLASSE SE VOC� N�O FOR O OROCHI */
	private int RA;
	private String senha;	
	private Campus campus;
	private boolean flagConectado = false;
	private String URL_BASE = "http://utfws.utfpr.edu.br/aluno";
	private String URL_INICIO = "/sistema/mpmenu.inicio";
	private String URL_ALTERACAOALUNO = "/sistema/mpalteracadaluno.inicio";
	private Header header;
	//private String URL_INICIO = "/sistema/mpmenu.inicio";

	private HTMLPortalUTFPR htmlPortal;
	public SistemaUTFPR(int RA, String senha, Campus campus){
		this.RA = RA;
		this.senha = senha;
		this.campus = campus;
		this.htmlPortal = new HTMLPortalUTFPR();
		URL_BASE += campus.getStringCodCampus();
	}
	public void setRA(int RA) {
		this.RA = RA;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setCampus(Campus campus) {
		URL_BASE = "http://utfws.utfpr.edu.br/aluno" + campus.getStringCodCampus();
	}
	
	//METODOS QUE INTERAGEM COM O SISTEMA DA UTFPR
	
	public boolean conectar(Context contexto) throws IllegalStateException{
		if(!WebUtils.verificaConexao(contexto)) throw new IllegalStateException("Sem conex�o!");
		WebHelper helper = new WebHelper(contexto);
		Header autenticacao = BasicScheme.authenticate(
				 new UsernamePasswordCredentials("" + RA, senha),
				 "ISO-8859-1", false);
		String htmlHome = "";
		this.conectaRedeUTFPR(contexto); //TENTA CONECTAR NA REDE DA UTFPR
		try {
			 htmlHome = helper.pegarHTML(URL_BASE + URL_INICIO, autenticacao);
		}catch (Exception e){
			throw new IllegalStateException("Login ou senha incorretos!");
		}
		if(htmlHome.contains("Authorization Required")) return false;
		else{
			flagConectado = true;
			this.header = autenticacao;
			htmlPortal.setHtmlHome(htmlHome);
			String htmlAtt = null;

//			htmlPortal.setHtmlAttCadastral(htmlAtt);
		}
		return true;
	}
	
	public Pessoa getAluno(Context contexto) throws IllegalStateException{

		if(!flagConectado || !WebUtils.verificaConexao(contexto)){
			throw new IllegalStateException("Voc� n�o est� conectado na rede da UTFPR!");
		}
		Pessoa aluno = new Pessoa(this.RA, this.senha, this.campus);
		String ra = aluno.getStringRA().substring(0, aluno.getStringRA().length()-1);
		
		if(!htmlPortal.commitHome(aluno)) return null;
//		if(!htmlPortal.commitAttCadastral(aluno)) return null;
		String URL_DISCPMATRICULADAS = "/sistema/" +
				"mpconfirmacaomatricula.pcTelaAluno?p_pesscodnr=" + ra + "&p_curscodnr="+ aluno.getCurso().getCodCurso() + 
				"&p_alcuordemnr=" + aluno.getAlcuordemnr();
		
		String URL_BOLETIM = "/sistema/" +
						"mpboletim.inicioAluno?p_pesscodnr=" + RA + "&p_curscodnr="+  aluno.getCurso().getCodCurso()  + "" +
								"&p_alcuordemnr=" + aluno.getAlcuordemnr();
		WebHelper helper = new WebHelper(contexto);

		String htmlDISCP = "";
		String htmlBoletim ="";
		try {
			htmlDISCP = helper.pegarHTML(URL_BASE + URL_DISCPMATRICULADAS, header);
			htmlBoletim = helper.pegarHTML(URL_BASE + URL_BOLETIM, header);
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		}

		htmlPortal.setHtmlDiscpMatriculadas(htmlDISCP);
		htmlPortal.setHtmlDadosBoletim(htmlBoletim);
//		if(!htmlPortal.commitDiscpMatric(aluno)) return null;
		if(!htmlPortal.commitBoletim(aluno)) return null;
		
		aluno.setCampus(this.campus);
		
		return aluno;
		
	}
	
	

public boolean conectaRedeUTFPR(Pessoa aluno, Context contexto) throws IllegalStateException{
	if(!WebUtils.verificaConexao(contexto))	throw new IllegalStateException("Sem conex�o!");

	WifiInfo info = ((WifiManager) contexto.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
	if(info.getSSID().equals("UTFPRWEB")){
		try{
			String link = "https://1.1.1.1/login.html"; // LINK DA REDE UTFPR-CM
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("username", "a" + aluno.getAutenticacao().getRA()));
			nameValuePairs.add(new BasicNameValuePair("password", aluno.getAutenticacao().getSenha()));
			nameValuePairs.add(new BasicNameValuePair("buttonClicked", "4"));
			nameValuePairs.add(new BasicNameValuePair("redirect_url", link));
			
			HttpClient httpclient = new DefaultHttpClient();
			httpclient = WebUtils.wrapClient(httpclient);
	        HttpPost httppost = new HttpPost(link);
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        httpclient.execute(httppost);
		}catch(Exception e){
			Log.e("ERRO:", e.toString());
			return false;
		}
        
	}else{
		return false;
	}
   return true;

}


public boolean conectaRedeUTFPR(Context contexto) throws IllegalStateException{
	if(!WebUtils.verificaConexao(contexto))	throw new IllegalStateException("Sem conex�o!");
	Pessoa aluno = new Pessoa(this.RA,this.senha,this.campus);
	return this.conectaRedeUTFPR(aluno, contexto);
}


}
