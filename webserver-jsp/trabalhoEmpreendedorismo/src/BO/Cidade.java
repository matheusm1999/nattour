package BO;

public class Cidade {
	private String nome;
	private int idCidade;
	private Uf uf;
	
	public Cidade(int idCidade){
		this.idCidade = idCidade;
	}
	
	public Cidade(String nome){
		this.nome = nome;
		this.uf = new Uf();
	}
	
	public Cidade(){
		
	}
	
	public int getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(int idCidade) {
		this.idCidade = idCidade;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}
