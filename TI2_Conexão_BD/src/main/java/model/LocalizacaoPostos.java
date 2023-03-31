package model;

public class LocalizacaoPostos {
private int posto;
private int id_localizacao;
private String cidade;
private String estado;
private String bairro;
private String rua;
private int numero;
//CONSTRUTORES
public LocalizacaoPostos() {
	
}
public LocalizacaoPostos(int posto, int id_localizacao, String cidade,String estado,String bairro, String rua,int numero) {
	this.posto = posto;
	this.id_localizacao = id_localizacao;
	this.cidade = cidade;
	this.estado = estado;
	this.bairro = bairro;
	this.rua = rua;
	this.numero = numero;
}
//GETS e SETS
public int getposto(){
	return posto;
}
public void setposto(int posto) {
	this.posto = posto;
}
public int getid_localizacao() {
	return id_localizacao;
}
public void setid_localizacao(int id_localizacao) {
	this.id_localizacao = id_localizacao;
}
public String getcidade() {
	return cidade;
}
public void setcidade(String cidade) {
	this.cidade = cidade;
}
public String getestado() {
	return estado;
}
public void setestado(String estado) {
	this.estado = estado;
}
public String getbairro() {
	return bairro;
}
public void setbairro(String bairro) {
	this.bairro = bairro;
}
public String getrua() {
	return rua;
}
public void setrua(String rua) {
	this.rua = rua;
}
public int getnumero() {
	return numero;
}
public void setnumero(int numero) {
	this.numero = numero;
}
//toString

public String toString() {
	return "Localizacao Postos [posto=" + posto + ", id_localizacao=" + id_localizacao + ",  cidade=" + cidade + ", estado=" + estado + ", bairro"+ bairro+ ", rua"+ rua+", numero" + numero+
			"]";
}
}
