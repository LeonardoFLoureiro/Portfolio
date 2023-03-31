package model;

public class Usuario {
private String cpf;
private String PrimeiroNome;
private String SegundoNome;
private String Senha;
private String DataNascimento;
private int Confiabilidade;

//CONSTRUTORES
public Usuario() {
		
}
public Usuario(String cpf,String PrimeiroNome,String SegundoNome,String Senha, String DataNascimento,int Confiabilidade) {
	this.cpf = cpf;
	this.PrimeiroNome = PrimeiroNome;
	this.SegundoNome = SegundoNome;
	this.Senha = Senha;
	this.DataNascimento = DataNascimento;
	this.Confiabilidade = Confiabilidade;
}

//GETS & SETS

public String getCpf() {
	return cpf;
}
public void setCpf(String cpf) {
	
	this.cpf = cpf;
}


public String getPrimeiroNome() {
	return PrimeiroNome;
}
public void setPrimeiroNome(String PrimeiroNome) {
	
	this.PrimeiroNome = PrimeiroNome;
}



public String getSegundoNome() {
	return SegundoNome;
}
public void setSegundoNome(String SegundoNome) {
	
	this.SegundoNome = SegundoNome;
}



public String getSenha() {
	return Senha;
}
public void setSenha(String Senha) {
	
	this.Senha = Senha;
}


public String getDataNascimento() {
	return DataNascimento;
}
public void setDataNascimento(String DataNascimento) {
	
	this.DataNascimento = DataNascimento;
}


public int getConfiabilidade() {
	return Confiabilidade;
}
public void setConfiabilidade(int Confiabilidade) {
	
	this.Confiabilidade = Confiabilidade;
}

//toString

public String toString() {
	return "Usuario [Primeiro Nome=" + PrimeiroNome + ", Segundo Nome=" + SegundoNome + ", Senha=" + Senha + ", Data Nascimento=" + DataNascimento + ", Confiabilidade"+ Confiabilidade+ "]";
}	


}


