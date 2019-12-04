package BO;

public class Uf {
	private String nomeUf;
	private String siglaUf;
	private int idUf;
	private Pais pais;
	
	
	public Uf(String nomeUf, String siglaUf, int idUf) {
		this.nomeUf = nomeUf;
		this.siglaUf = siglaUf;
		this.idUf = idUf;
		this.pais = new Pais();
	}
	
	public Uf(){
		this.pais = new Pais();
	}
	
	public Pais getPais() {
		return pais;
	}



	public void setPais(Pais pais) {
		this.pais = pais;
	}



	public String getNomeUf() {
		return nomeUf;
	}
	public void setNomeUf(String nomeUf) {
		this.nomeUf = nomeUf;
	}
	public String getSiglaUf() {
		return siglaUf;
	}
	public void setSiglaUf(String siglaUf) {
		this.siglaUf = siglaUf;
	}
	public int getIdUf() {
		return idUf;
	}
	public void setIdUf(int idUf) {
		this.idUf = idUf;
	}
	
}
