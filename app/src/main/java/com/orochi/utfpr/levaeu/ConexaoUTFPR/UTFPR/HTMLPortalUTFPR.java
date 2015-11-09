package com.orochi.utfpr.levaeu.ConexaoUTFPR.UTFPR;

import java.util.ArrayList;

import android.util.Log;

import com.orochi.utfpr.levaeu.Curso;
import com.orochi.utfpr.levaeu.Pessoa;

public class HTMLPortalUTFPR {
	private String htmlHome;
	private String htmlAttCadastral;
	private String htmlDiscpMatriculadas;
	private String htmlDadosBoletim;
	public String getHtmlHome() {
		return htmlHome;
	}
	public void setHtmlHome(String htmlHome) {
		this.htmlHome = htmlHome;
	}
	public String getHtmlAttCadastral() {
		return htmlAttCadastral;
	}
	public void setHtmlAttCadastral(String htmlAttCadastral) {
		this.htmlAttCadastral = htmlAttCadastral;
	}
	public String getHtmlDiscpMatriculadas() {
		return htmlDiscpMatriculadas;
	}
	public void setHtmlDiscpMatriculadas(String htmlDiscpMatriculadas) {
		this.htmlDiscpMatriculadas = htmlDiscpMatriculadas;
	}
	public String getHtmlDadosBoletim() {
		return htmlDadosBoletim;
	}
	public void setHtmlDadosBoletim(String htmlDadosBoletim) {
		this.htmlDadosBoletim = htmlDadosBoletim;
	}
	
	public boolean populado(){
		
	return (htmlHome != null && htmlDiscpMatriculadas != null && htmlDadosBoletim != null && htmlAttCadastral != null);
	}
	
	public boolean commitHome(Pessoa aluno) {
	/*Vai setar: Nome, codCurso, tpcurcodnr e alcuordemnr
	 * Vai TENTAR setar: Nome Curso */		
	String html = this.htmlHome;
	String html_tabela = "";
	Curso curso = new Curso();
	try{
		html_tabela = html.substring(html.indexOf("Aluno:"));
		html_tabela = html_tabela.substring(html_tabela.indexOf("</b>"),
		html_tabela.indexOf("</font>"));
		html_tabela = html_tabela.replace("</b>", "");
		String[] dados = html_tabela.split("-");
		aluno.getDados().setNome(dados[1].substring(1));
		if(html.indexOf("Curso:</strong>") != -1){
			html_tabela = html.substring(html.indexOf("Curso:</strong>")).replace("Curso:</strong>", "");
			html_tabela = html_tabela.substring(html_tabela.indexOf(" "),
			html_tabela.indexOf("-")-1).replaceFirst(" ", "");
			curso.setNome(html_tabela);

			if(html.indexOf("Situa") != -1){
				html_tabela = html.substring(html.indexOf("Situa"));
				html_tabela = html_tabela.substring(html_tabela.indexOf("</strong>"),
				html_tabela.indexOf("</font>"));
				html_tabela = html_tabela.replace("</strong>", "");
				html_tabela = html_tabela.replaceFirst(" ", "");
			}
			
		}
		else if(html.indexOf("Selecione o Curso:") != -1){
			html_tabela = html.substring(html.indexOf("Selecione o Curso:"));
			html_tabela = html_tabela.substring(html_tabela.indexOf("<option value=\"1\">"));
			String htmlSave = html_tabela.substring(html_tabela.indexOf(">") + 1);
			html_tabela = htmlSave.substring(0, htmlSave.indexOf("-"));
			curso.setNome(html_tabela);
			
			htmlSave = htmlSave.substring(htmlSave.indexOf("Situa��o :") + "Situa��o :".length());
			htmlSave = htmlSave.substring(0, htmlSave.indexOf("<")).trim();

		}


		if(html.indexOf("curscodnr:") != -1){
			html_tabela = html.substring(html.indexOf("curscodnr:"));
			html_tabela = html_tabela.substring(html_tabela.indexOf(" "), html_tabela.indexOf(","));
			html_tabela = html_tabela.replaceFirst(" ", "");
			curso.setCodCurso(Integer.parseInt(html_tabela));
		}
		
		if(html.indexOf("tpcurcodnr:") != -1){
			html_tabela = html.substring(html.indexOf("tpcurcodnr:"));
			html_tabela = html_tabela.substring(html_tabela.indexOf(" "), html_tabela.indexOf(","));
			html_tabela = html_tabela.replaceFirst(" ", "");
			aluno.setTpcurcodnr(html_tabela);
		}
		
		if(html.indexOf("alcuordemnr:") != -1){
			html_tabela = html.substring(html.indexOf("alcuordemnr:"));
			html_tabela = html_tabela.substring(html_tabela.indexOf(" "), html_tabela.indexOf("}"));
			html_tabela = html_tabela.replaceFirst(" ", "");
			aluno.setAlcuordemnr(html_tabela);		
		}
		
		aluno.setCurso(curso);
	}catch(Exception e){
		e.printStackTrace();
		throw new IllegalStateException("Erro ao setar os dados do aluno.");
	}
	return true;
}
	
	public boolean commitAttCadastral(Pessoa aluno) {
		String aux;
		String html = this.htmlAttCadastral;
		try{
		if(html.indexOf("<form") != -1){
			html = html.substring(html.indexOf("<form"), html.indexOf("</form>"));
			aux = html.substring(html.indexOf("Sexo:", html.indexOf(">")));
			aux = aux.substring(aux.indexOf(">")+1);
			aux = aux.substring(aux.indexOf("<"));
			aux = aux.substring(aux.indexOf(">")+1);
			aux = aux.substring(0,aux.indexOf("<"));
			aluno.getDados().setSexo(aux.charAt(0));
		
			aux = html.substring(html.indexOf("\"", html.indexOf("name=\"p_email\"")));
			
			aux = aux.substring(aux.indexOf("="));
			aux = aux.substring(aux.indexOf("\"")+1,aux.indexOf("\"", aux.indexOf("\"")+1));
			aluno.getDados().setEmail(aux);
		}}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
}

	public boolean commitDiscpMatric(Pessoa aluno){
		String html = this.htmlDiscpMatriculadas;
		String html_tabela;
		String aux;
		try{
		if(html.indexOf("table class=\"cabealuno\"") > -1){
			html_tabela = html.substring(html.indexOf("table class=\"cabealuno\""));
			html_tabela = html_tabela.substring(0, html_tabela.indexOf("</table>"));
			
			if(aluno.getCurso().getNome() == null){
				Curso curso = aluno.getCurso();
				aux = html_tabela.substring(html_tabela.indexOf("Curso :"));
				aux = aux.substring(aux.indexOf(">")+1);
				aux = aux.substring(0, aux.indexOf("<"));
				aux = aux.replace(" ", "");
				curso.setNome(aux.split("-")[1]);
			}

	}


		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean commitBoletim(Pessoa aluno){
		String html = this.htmlDadosBoletim;
	String html_tabela;
	try{
		if(html.indexOf("<table class=\"tbl") != -1){
		html_tabela = html.substring(html.indexOf("<table class=\"tbl\" width=\"80%\" >"));
		html_tabela = html_tabela.substring(html_tabela.indexOf("<tr>",html_tabela.indexOf("<tr>")+1));
		html_tabela = html_tabela.substring(html_tabela.indexOf("<tr>",html_tabela.indexOf("<tr>")+1));
		html_tabela = html_tabela.substring(0, html_tabela.indexOf("</table>"));
		String linhas[] = html_tabela.split("(</tr>)");
		
		for(int i=0; i < linhas.length-1;i++){
			linhas[i] = linhas[i].replace("<tr>", "");
			linhas[i] = linhas[i].replace("</tr>", "");
			linhas[i] = linhas[i].replace("<b>", "");
			linhas[i] = linhas[i].replace("</b>", "");
			if(linhas[i].indexOf("<td") > 0){
			linhas[i] = linhas[i].substring(linhas[i].indexOf("<td"));
		
			}
		}
		
		for(int i=0; i < linhas.length-1 ;i++){
			String[] colunas = linhas[i].split("(</td>)");
	
	
			for(int j=3; j < colunas.length-2 ;j++){
				colunas[j] = colunas[j].substring(colunas[j].indexOf(">")+1);
				colunas[j] = colunas[j].substring(colunas[j].indexOf(">")+1);
				if(colunas[j].indexOf("<") > 0){
		      colunas[j] = colunas[j].substring(0, colunas[j].indexOf("<"));
				}
				
				if(colunas[j].equals(" * ")) colunas[j] = "0";
				
			colunas[j] = colunas[j].replace(" ", "");
			if(colunas[j].contains(",")) colunas[j] = colunas[j].replace(",", ".");
			}
	
	
			colunas[8] = colunas[8].replace("%", "");

		}
		
		}
		else{
			return false;
		}
		return true;}
	catch(Exception e){
		throw new IllegalStateException(e.toString());
	}
}		
	
}
