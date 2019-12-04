package BO;

public class Conta {
	private String login;
	private String senha;
	private String email;
	private String nome;
	private int idConta;
	
	public Conta(String login,String senha,String email,String nome,int idConta){
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
		this.idConta = idConta;
	}
	
	

	public Conta(String login, String senha, String email, String nome) {
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.nome = nome;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdConta() {
		return idConta;
	}

	public void setIdConta(int idConta) {
		this.idConta = idConta;
	}
	

}
